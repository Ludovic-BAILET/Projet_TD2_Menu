package edu.polytech.projet_td2_menu.models.data;

import static edu.polytech.projet_td2_menu.ApplicationMenu.CHANNEL_1_ID;

import android.app.Notification;
import android.content.Context;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.polytech.projet_td2_menu.notifications.NotificationSender;

public class ModelNotifications {
    private final static List<Notification> notificationList = new ArrayList<>();
    private final static List<Notification> pinnedNotificationList = new ArrayList<>();

    public static void addNotification(Notification notification) {
        notificationList.add(notification);
    }

    public static Notification removeNotification(int index) {
        return notificationList.remove(index);
    }

    public static Notification removePinnedNotification(int index) {
        return pinnedNotificationList.remove(index);
    }

    public static int sizeNotification() {
        return notificationList.size();
    }

    public static int sizePinnedNotification() {
        return pinnedNotificationList.size();
    }

    public static Notification getNotification(int index) {
        return notificationList.get(index);
    }

    public static Notification getPinnedNotification(int index) {
        return pinnedNotificationList.get(index);
    }

    public static void transferNotificationToPinned(int index) {
        pinnedNotificationList.add(notificationList.remove(index));
    }

    public static void transferNotificationToUnpinned(int index) {
        notificationList.add(pinnedNotificationList.remove(index));
    }

    public static void sortTimeIncrease() {
        Comparator<Notification> c = (n1, n2) -> (int) (n1.when - n2.when);
        notificationList.sort(c);
        pinnedNotificationList.sort(c);
    }
    public static void sortTimeDecrease() {
        Comparator<Notification> c = (n1, n2) -> (int) (n2.when - n1.when);
        notificationList.sort(c);
        pinnedNotificationList.sort(c);
    }

}
