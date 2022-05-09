package edu.polytech.projet_td2_menu.notifications;

import static edu.polytech.projet_td2_menu.ApplicationMenu.CHANNEL_rappelcourse;
import static edu.polytech.projet_td2_menu.ApplicationMenu.CHANNEL_suggestion;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import edu.polytech.projet_td2_menu.ListRecipeActivity;
import edu.polytech.projet_td2_menu.ListeCourseActivity;
import edu.polytech.projet_td2_menu.R;
public class NotificationSender {
    private final Context context;
    public static int notificationId = 0;

    public NotificationSender(Context context) {
        this.context = context;
    }

    public void sendNotification(String title, String message, String channelId, int priority) {


// Creating a pending intent and wrapping our intent
        final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, ListRecipeActivity.class), 0);
        final PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 1, new Intent(context, ListeCourseActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.poisson);
        Bitmap bitmap2= BitmapFactory.decodeResource(context.getResources(),R.drawable.calendarnotif);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, channelId)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(priority)
                .setShowWhen(true)

                .setAutoCancel(true)
                ;

        switch(channelId) {
            case CHANNEL_suggestion: notification.setSmallIcon(R.drawable.heart_full).setStyle(new NotificationCompat.BigPictureStyle()
                    .bigPicture(bitmap).setSummaryText(message)).setContentIntent( pendingIntent);break;
            case CHANNEL_rappelcourse: notification.setTimeoutAfter(20000).setSmallIcon(R.drawable.bell).setStyle(new NotificationCompat.BigPictureStyle()
                    .bigPicture(bitmap2).setSummaryText(message)).setContentIntent( pendingIntent2);break;

        }
        Notification notificationBuilt = notification.build();
        NotificationManagerCompat.from(context).notify(notificationId, notificationBuilt);
        NotificationListener.onNotificationPosted(notificationBuilt);
    }

    public static void mockNotifications(NotificationSender notificationSender) {
        String title = "suggestion de plat";
        String titlerappel = "rappel de course";
        String message = "message ";
        int priority = 1;
        int priorityrappel = 2;

        for (int id = 0; id < 10; id++) {
            notificationSender.sendNotification(title, "voici une recettes a essayer", CHANNEL_suggestion, priority);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        notificationId+=1;
        for (int id = 0; id < 10; id++) {

            notificationSender.sendNotification(titlerappel, "n'oubliez pas de faire vos course", CHANNEL_rappelcourse, priority);}
    }
}
