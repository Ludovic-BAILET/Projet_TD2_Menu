package edu.polytech.projet_td2_menu;

import edu.polytech.projet_td2_menu.models.data.ModelNotifications;

public class NotificationsController {
    private final NotificationView view;

    public NotificationsController(NotificationView view) {
        this.view = view;
    }
    public void sortNotificationInIncreasingTime() {
        ModelNotifications.getInstance().sortTimeIncrease();
        updatesData();
    }

    public void sortNotificationInDecreasingTime() {
        ModelNotifications.getInstance().sortTimeDecrease();
        updatesData();
    }

    private void updatesData() {
        view.getAdapterBaseNotification().notifyDataSetChanged();
        view.getAdapterPinnedNotification().notifyDataSetChanged();
    }

    public void changeNotificationToPinned(int index) {
        ModelNotifications.getInstance().transferNotificationToPinned(index);
        updatesData();
    }

    public void changeNotificationToUnPinned(int index) {
        ModelNotifications.getInstance().transferNotificationToUnpinned(index);
        updatesData();
    }

    public void removeNotification(int index) {
        ModelNotifications.getInstance().removeNotification(index);
        updatesData();
    }
}
