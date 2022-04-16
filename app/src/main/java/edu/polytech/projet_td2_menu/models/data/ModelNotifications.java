package edu.polytech.projet_td2_menu.models.data;

import static edu.polytech.projet_td2_menu.ApplicationMenu.CHANNEL_1_ID;

import android.app.Notification;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import edu.polytech.projet_td2_menu.notifications.NotificationSender;

public class ModelNotifications {
    private final static List<Notification> notificationList = new ArrayList<>();

    public static void addNotification(Notification notification) {
        notificationList.add(notification);
    }

    public static Notification removeNotification(int index) {
        return notificationList.remove(index);
    }
}
