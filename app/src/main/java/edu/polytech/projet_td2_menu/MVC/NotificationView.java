package edu.polytech.projet_td2_menu.MVC;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Observable;
import java.util.Observer;

import edu.polytech.projet_td2_menu.adapters.ViewAdapterNotification;
import edu.polytech.projet_td2_menu.models.data.ModelNotifications;

public class NotificationView implements Observer {

    public static final String TAG = "NotificationView";
    private final ConstraintLayout layout;
    private final Context context;
    private ViewAdapterNotification adapterBaseNotification;
    private ViewAdapterNotification adapterPinnedNotification;
    private NotificationsController controller;

    public NotificationView(Context context, ConstraintLayout layout) {
        this.layout = layout;
        this.context = context;
    }

    public void setAdapterBaseNotification(ViewAdapterNotification adapterBaseNotification) {
        this.adapterBaseNotification = adapterBaseNotification;
    }

    public void setAdapterPinnedNotification(ViewAdapterNotification adapterPinnedNotification) {
        this.adapterPinnedNotification = adapterPinnedNotification;
    }

    public void setController(NotificationsController controller) {
        this.controller = controller;
        this.controller.setListenersView();
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
        controller.setGesturesAdapters(adapter, layout, i);
    }
}
