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
import edu.polytech.projet_td2_menu.models.data.ModelNotifications;

public class NotificationView implements Observer {

    public static final String TAG = "NotificationView";

    private ViewAdapterNotification adapterBaseNotification;
    private ViewAdapterNotification adapterPinnedNotification;
    private NotificationsController controller;
    private final ConstraintLayout layout;
    private final ListView listViewNotification;
    private final ListView listViewPinnedNotification;
    private final LinearLayout layoutListView;
    private final Context context;

    public NotificationView(Context context, ConstraintLayout layout) {
        layoutListView =  layout.findViewById(R.id.layout_list_view);
        listViewNotification = layoutListView.findViewById(R.id.list_notifications);
        listViewPinnedNotification = layoutListView.findViewById(R.id.list_notifications_pinned);
        this.layout = layout;
        this.context = context;
    }

    private void setListeners() {
        listViewNotification.setAdapter(adapterBaseNotification);
        listViewPinnedNotification.setAdapter(adapterPinnedNotification);

        LinearLayout layout_buttons = layout.findViewById(R.id.buttons_sort);
        controller.setOnClickListenerSortNotification(layout_buttons.findViewById(R.id.increase_time_button));
        controller.setOnClickListenerReverseSortNotification(layout_buttons.findViewById(R.id.decrease_time_button));
    }

    public void setAdapterBaseNotification(ViewAdapterNotification adapterBaseNotification) {
        this.adapterBaseNotification = adapterBaseNotification;
    }

    public void setAdapterPinnedNotification(ViewAdapterNotification adapterPinnedNotification) {
        this.adapterPinnedNotification = adapterPinnedNotification;
    }

    public void setController(NotificationsController controller) {
        this.controller = controller;
        this.setListeners();
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void update(Observable o, Object arg) {
        ModelNotifications model = (ModelNotifications) o;
        adapterPinnedNotification.refresh(model.getPinnedNotificationList());
        adapterBaseNotification.refresh(model.getNotificationList());
    }

    public void setGestureAdapters(ViewAdapterNotification adapter, ConstraintLayout layout, int i) {
        controller.setGestureAdapters(adapter, layout, i);
    }
}
