package edu.polytech.projet_td2_menu.MVC;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Observable;
import java.util.Observer;

import edu.polytech.projet_td2_menu.adapters.ViewAdapterNotification;
import edu.polytech.projet_td2_menu.gestures.OnSwipeTouchListener;
import edu.polytech.projet_td2_menu.models.data.ModelNotifications;

public class NotificationsController implements Observer {
    private final NotificationView view;
    public static final String TAG = "NotificationController";
    private final ViewAdapterNotification adapterBaseNotification;
    private final ViewAdapterNotification adapterPinnedNotification;
    private final NotificationsCenterActivity activity;

    public NotificationsController(NotificationView view, NotificationsCenterActivity activity) {
        this.view = view;
        adapterBaseNotification = new ViewAdapterNotification(view, ModelNotifications.getInstance().getNotificationList());
        adapterPinnedNotification = new ViewAdapterNotification(view, ModelNotifications.getInstance().getPinnedNotificationList());
        this.activity = activity;

        view.setAdapterBaseNotification(adapterBaseNotification);
        view.setAdapterPinnedNotification(adapterPinnedNotification);
    }

    private void sortNotificationInIncreasingTime() {
        ModelNotifications.getInstance().sortTimeIncrease();
    }

    private void sortNotificationInDecreasingTime() {
        ModelNotifications.getInstance().sortTimeDecrease();
    }

    public void setOnClickListenerSortNotification(View v) {
        v.setOnClickListener(view -> sortNotificationInIncreasingTime());
    }

    public void setOnClickListenerReverseSortNotification(View v) {
        v.setOnClickListener(view -> sortNotificationInDecreasingTime());
    }

    public void setGestureAdapters(ViewAdapterNotification adapter, ConstraintLayout layout, int i) {
        OnSwipeTouchListener swipeTouchListener;

        if (adapter == adapterPinnedNotification) {
            swipeTouchListener = new OnSwipeTouchListener(layout.getContext()) {
                @Override
                public void onSwipeLeft() {
                    super.onSwipeLeft();
                    changeNotificationToUnPinned(i);
                    createToast("La notification a été désépinglée");
                }
            };
            layout.setOnTouchListener(swipeTouchListener);
        } else if (adapter == adapterBaseNotification) {
            swipeTouchListener = new OnSwipeTouchListener(layout.getContext()) {
                @Override
                public void onSwipeLeft() {
                    super.onSwipeLeft();
                    new AlertDialog.Builder(activity.getActivity())
                            .setTitle("Confirmation")
                            .setMessage("Êtes-vous sûr de vouloir supprimer cette notification ?")
                            .setPositiveButton("Valider", (dialog, which) -> {
                                removeNotification(i);
                                createToast("La notification a bien été supprimée");
                            })
                            .setNegativeButton("Annuler", null)
                            .create()
                            .show();
                }

                @Override
                public void onSwipeRight() {
                    super.onSwipeRight();
                    changeNotificationToPinned(i);
                    createToast("La notification a été épinglée");
                }
            };
            layout.setOnTouchListener(swipeTouchListener);
        }

    }

    public void changeNotificationToPinned(int index) {
        ModelNotifications.getInstance().transferNotificationToPinned(index);
    }

    public void changeNotificationToUnPinned(int index) {
        ModelNotifications.getInstance().transferNotificationToUnpinned(index);
    }

    public void removeNotification(int index) {
        ModelNotifications.getInstance().removeNotification(index);
    }

    @Override
    public void update(Observable o, Object arg) {
        Log.d(TAG, "Les données du modèle ont changé : " + arg);
    }

    private void createToast(String text) {
        Toast.makeText(activity.getActivity(), text, Toast.LENGTH_SHORT).show();
    }
}
