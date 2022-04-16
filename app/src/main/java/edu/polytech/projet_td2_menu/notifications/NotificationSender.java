package edu.polytech.projet_td2_menu.notifications;

import android.app.Notification;
import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import edu.polytech.projet_td2_menu.R;
import static edu.polytech.projet_td2_menu.ApplicationMenu.*;
public class NotificationSender {
    private final Context context;
    public static final int notficationId = 0;

    public NotificationSender(Context context) {
        this.context = context;
    }

    public void sendNotification(String title, String message, String channelId, int priority) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, channelId)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(priority);
        switch(channelId) {
            case CHANNEL_1_ID: notification.setSmallIcon(R.drawable.number_1);
            case CHANNEL_2_ID: notification.setSmallIcon(R.drawable.number_2);
            case CHANNEL_3_ID: notification.setSmallIcon(R.drawable.number_3);
        }

        Notification notificationBuilt = notification.build();
        NotificationManagerCompat.from(context).notify(notficationId, notificationBuilt);
        NotificationListener.onNotificationPosted(notificationBuilt);
    }

    public static void mockNotifications(NotificationSender notificationSender) {
        String title = "Mock notification";
        String message = "message ";
        int priority = 1;

        for (int id = 0; id < 10; id++) {
            notificationSender.sendNotification(title, message + id, CHANNEL_1_ID, priority);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
