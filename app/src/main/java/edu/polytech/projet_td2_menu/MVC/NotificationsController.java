package edu.polytech.projet_td2_menu.MVC;

import android.util.Log;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

import edu.polytech.projet_td2_menu.models.data.ModelNotifications;

public class NotificationsController implements Observer {
    private final NotificationView view;
    public static final String TAG = "NotificationController";

    public NotificationsController(NotificationView view) {
        this.view = view;
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
}
