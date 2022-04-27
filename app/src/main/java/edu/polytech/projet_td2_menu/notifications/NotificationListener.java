package edu.polytech.projet_td2_menu.notifications;

import android.app.Notification;
import android.util.Log;

import edu.polytech.projet_td2_menu.models.data.ModelNotifications;

public class NotificationListener {
    private static String TAG = "Notification";


    public static void onNotificationPosted(Notification notification) {
        Log.d(TAG, "onNotificationPosted");
        ModelNotifications.getInstance().addNotification(notification);
    }
}
