package edu.polytech.projet_td2_menu.MVC;

import android.app.AlertDialog;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import edu.polytech.projet_td2_menu.R;
import edu.polytech.projet_td2_menu.adapters.ViewAdapterNotification;
import edu.polytech.projet_td2_menu.gestures.OnSwipeTouchListener;
import edu.polytech.projet_td2_menu.models.data.ModelNotifications;

public class NotificationsController {
    public static final String TAG = "NotificationController";
    private final NotificationView view;
    private final ViewAdapterNotification adapterBaseNotification;
    private final ViewAdapterNotification adapterPinnedNotification;
    private final NotificationsCenterActivity activity;
    private final ConstraintLayout layout;
    private boolean sortModelNaturalOrder = true;
    private boolean controllerActOnModel = false;

    public NotificationsController(NotificationView view, NotificationsCenterActivity activity) {
        this.view = view;
        adapterBaseNotification = new ViewAdapterNotification(view, ModelNotifications.getInstance().getNotificationList());
        adapterPinnedNotification = new ViewAdapterNotification(view, ModelNotifications.getInstance().getPinnedNotificationList());
        this.activity = activity;
        this.layout = activity.findViewById(R.id.notification_center);

        view.setAdapterBaseNotification(adapterBaseNotification);
        view.setAdapterPinnedNotification(adapterPinnedNotification);
    }

    private void sortNotificationInIncreasingTime() {
        controllerActOnModel = true;
        ModelNotifications.getInstance().sortTimeIncrease();
        sortModelNaturalOrder = true;
    }

    private void sortNotificationInDecreasingTime() {
        controllerActOnModel = true;
        ModelNotifications.getInstance().sortTimeDecrease();
        sortModelNaturalOrder = false;
    }

    public void setGesturesAdapters(ViewAdapterNotification adapter, ConstraintLayout layout, int i) {
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

    public void update() {
        Log.d(TAG, "Les données du modèle ont changé");

        if (!controllerActOnModel) {
            if (sortModelNaturalOrder) {
                sortNotificationInIncreasingTime();
            } else {
                sortNotificationInDecreasingTime();
            }
        } else {
            controllerActOnModel = false;
        }
    }

    private void createToast(String text) {
        Toast.makeText(activity.getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    public void setListenersView() {
        LinearLayout layoutListView = layout.findViewById(R.id.layout_list_view);
        ((ListView) layoutListView.findViewById(R.id.list_notifications)).setAdapter(adapterBaseNotification);
        ((ListView) layoutListView.findViewById(R.id.list_notifications_pinned)).setAdapter(adapterPinnedNotification);

        LinearLayout layout_buttons = layout.findViewById(R.id.buttons_sort);
        layout_buttons.findViewById(R.id.increase_time_button).setOnClickListener(view -> sortNotificationInIncreasingTime());
        layout_buttons.findViewById(R.id.decrease_time_button).setOnClickListener(view -> sortNotificationInDecreasingTime());
    }
}
