package edu.polytech.projet_td2_menu.MVC;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Observable;
import java.util.Observer;

import edu.polytech.projet_td2_menu.MVC.NotificationsController;
import edu.polytech.projet_td2_menu.R;
import edu.polytech.projet_td2_menu.adapters.ViewAdapterNotification;

public class NotificationView implements Observer {

    public static final String TAG = "NotificationView";

    private ViewAdapterNotification adapterBaseNotification;
    private ViewAdapterNotification adapterPinnedNotification;
    private NotificationsController controller;
    private ConstraintLayout layout;

    public NotificationView(Context context, ConstraintLayout layout) {
        adapterBaseNotification = new ViewAdapterNotification(context, false);
        adapterPinnedNotification = new ViewAdapterNotification(context, true);
        this.layout = layout;
    }

    public void setListeners() {
        LinearLayout layout_list_view = layout.findViewById(R.id.layout_list_view);
        ((ListView) layout_list_view.findViewById(R.id.list_notifications)).setAdapter(adapterBaseNotification);
        ((ListView) layout_list_view.findViewById(R.id.list_notifications_pinned)).setAdapter(adapterPinnedNotification);

        LinearLayout layout_buttons = layout.findViewById(R.id.buttons_sort);
        controller.setOnClickListenerSortNotification(layout_buttons.findViewById(R.id.increase_time_button));
        controller.setOnClickListenerReverseSortNotification(layout_buttons.findViewById(R.id.decrease_time_button));
    }

    public BaseAdapter getAdapterBaseNotification() {
        return adapterBaseNotification;
    }

    public BaseAdapter getAdapterPinnedNotification() {
        return adapterPinnedNotification;
    }

    public void setController(NotificationsController controller) {
        this.controller = controller;
        this.setListeners();
        this.adapterBaseNotification.setController(controller);
        this.adapterPinnedNotification.setController(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
        Log.d(TAG, "Les données du modèle ont changé : " + arg);
        adapterPinnedNotification.notifyDataSetChanged();
        adapterBaseNotification.notifyDataSetChanged();
    }
}

