package edu.polytech.projet_td2_menu.notifications;

import android.app.Notification;
import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.polytech.projet_td2_menu.models.data.ModelNotifications;

public class NotificationListener {
    private static String TAG = "Notification";


    public static void onNotificationPosted(Notification notification) {
        Log.d(TAG, "onNotificationPosted");
        ModelNotifications.getInstance().addNotification(notification);
    }
}
