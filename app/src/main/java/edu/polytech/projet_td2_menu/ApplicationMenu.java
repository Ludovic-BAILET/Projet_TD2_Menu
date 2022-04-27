package edu.polytech.projet_td2_menu;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.util.Objects;

import edu.polytech.projet_td2_menu.notifications.NotificationSender;

public class ApplicationMenu extends Application {
    public static final String CHANNEL_suggestion = "channel low";
    public static final String CHANNEL_rappelcourse = "channel default";

    private NotificationSender notificationSender;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel(CHANNEL_suggestion , "low", NotificationManager.IMPORTANCE_LOW);
        createNotificationChannel(CHANNEL_rappelcourse , "default", NotificationManager.IMPORTANCE_DEFAULT);

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
