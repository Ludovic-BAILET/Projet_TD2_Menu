package edu.polytech.projet_td2_menu;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.view.ViewGroup;

import java.util.Objects;

import edu.polytech.projet_td2_menu.notifications.NotificationSender;

public class ApplicationMenu extends Application {
    public static final String CHANNEL_1_ID = "channel low";
    public static final String CHANNEL_2_ID = "channel default";
    public static final String CHANNEL_3_ID = "channel high";

    private NotificationSender notificationSender;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel(CHANNEL_1_ID , "low", NotificationManager.IMPORTANCE_LOW);
        createNotificationChannel(CHANNEL_2_ID , "default", NotificationManager.IMPORTANCE_DEFAULT);
        createNotificationChannel(CHANNEL_3_ID, "high", NotificationManager.IMPORTANCE_HIGH);

        //startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
        notificationSender = new NotificationSender(getApplicationContext());
        NotificationSender.mockNotifications(notificationSender);

    }

    private void createNotificationChannel(String name, String description, int importance) {
        //for API 26+

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(name, name, importance);
            channel.setDescription(description);

            // Cannot be changed after
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }
    }
}
