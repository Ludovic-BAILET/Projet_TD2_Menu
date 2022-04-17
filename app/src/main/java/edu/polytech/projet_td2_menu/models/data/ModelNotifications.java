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

    public static int size() {
        return notificationList.size();
    }

    public static Notification getNotification(int index) {
        return notificationList.get(index);
    }

    public static void sortTimeIncrease() {
        notificationList.sort((n1, n2) -> (int) (n1.when - n2.when));
    }
    public static void sortTimeDecrease() {
        notificationList.sort((n1, n2) -> (int) (n2.when - n1.when));
    }

}
