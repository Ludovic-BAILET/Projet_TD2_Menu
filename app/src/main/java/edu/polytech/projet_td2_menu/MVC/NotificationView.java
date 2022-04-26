package edu.polytech.projet_td2_menu.MVC;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.constraintlayout.widget.ConstraintLayout;

import edu.polytech.projet_td2_menu.MVC.NotificationsController;
import edu.polytech.projet_td2_menu.R;
import edu.polytech.projet_td2_menu.adapters.ViewAdapterNotification;

public class NotificationView {

    private ViewAdapterNotification adapterBaseNotification;
    private ViewAdapterNotification adapterPinnedNotification;
    private NotificationsController controller;
    private ConstraintLayout layout;

    public NotificationView(Context context, ConstraintLayout layout) {
        adapterBaseNotification = new ViewAdapterNotification(context, false);
        adapterPinnedNotification = new ViewAdapterNotification(context, true);
        this.layout = layout;
        this.setListeners();
    }

    public void setListeners() {
        LinearLayout layout_list_view = layout.findViewById(R.id.layout_list_view);
        ((ListView) layout_list_view.findViewById(R.id.list_notifications)).setAdapter(adapterBaseNotification);
        ((ListView) layout_list_view.findViewById(R.id.list_notifications_pinned)).setAdapter(adapterPinnedNotification);

        LinearLayout layout_buttons = layout.findViewById(R.id.buttons_sort);
        layout_buttons.findViewById(R.id.increase_time_button).setOnClickListener(click -> controller.sortNotificationInIncreasingTime());
        layout_buttons.findViewById(R.id.decrease_time_button).setOnClickListener(click -> controller.sortNotificationInDecreasingTime());
    }

    public BaseAdapter getAdapterBaseNotification() {
        return adapterBaseNotification;
    }

    public BaseAdapter getAdapterPinnedNotification() {
        return adapterPinnedNotification;
    }

    public void setController(NotificationsController controller) {
        this.controller = controller;
        this.adapterBaseNotification.setController(controller);
        this.adapterPinnedNotification.setController(controller);
    }
}
