package edu.polytech.projet_td2_menu.models.data;

import android.app.Notification;
import android.content.Context;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.polytech.projet_td2_menu.NotificationsController;
import edu.polytech.projet_td2_menu.notifications.NotificationSender;

public final class ModelNotifications {
    private static ModelNotifications instance;
    private final List<Notification> notificationList = new ArrayList<>();
    private final List<Notification> pinnedNotificationList = new ArrayList<>();

    
    private ModelNotifications(){}

    public static ModelNotifications getInstance(){
        if (instance == null)
            instance = new ModelNotifications();
        return instance;
    }
    
    public void addNotification(Notification notification) {
        notificationList.add(notification);
    }

    public Notification removeNotification(int index) {
        return notificationList.remove(index);
    }

    public Notification removePinnedNotification(int index) {
        return pinnedNotificationList.remove(index);
    }

    public int sizeNotification() {
        return notificationList.size();
    }

    public int sizePinnedNotification() {
        return pinnedNotificationList.size();
    }

    public Notification getNotification(int index) {
        return notificationList.get(index);
    }

    public Notification getPinnedNotification(int index) {
        return pinnedNotificationList.get(index);
    }

    public void transferNotificationToPinned(int index) {
        pinnedNotificationList.add(notificationList.remove(index));
    }

    public void transferNotificationToUnpinned(int index) {
        notificationList.add(pinnedNotificationList.remove(index));
    }

    public void sortTimeIncrease() {
        Comparator<Notification> c = (n1, n2) -> (int) (n1.when - n2.when);
        notificationList.sort(c);
        pinnedNotificationList.sort(c);
    }
    public void sortTimeDecrease() {
        Comparator<Notification> c = (n1, n2) -> (int) (n2.when - n1.when);
        notificationList.sort(c);
        pinnedNotificationList.sort(c);
    }
}
