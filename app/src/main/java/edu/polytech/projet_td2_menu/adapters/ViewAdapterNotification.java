package edu.polytech.projet_td2_menu.adapters;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import edu.polytech.projet_td2_menu.R;
import edu.polytech.projet_td2_menu.gestures.OnSwipeTouchListener;
import edu.polytech.projet_td2_menu.models.data.ModelNotifications;

public class ViewAdapterNotification extends BaseAdapter {
    private final LayoutInflater inflater;
    private final boolean pinnedNotification;
    private final BaseAdapter otherAdapter;

    public ViewAdapterNotification(Context context, boolean pinnedNotification, BaseAdapter otherAdapter) {
        inflater = LayoutInflater.from(context);
        this.pinnedNotification = pinnedNotification;
        this.otherAdapter = otherAdapter;
    }
    @Override
    public int getCount() {
        if (pinnedNotification) {
            return ModelNotifications.sizePinnedNotification();
        }
        return ModelNotifications.sizeNotification();
    }

    @Override
    public Notification getItem(int position) {
        if (pinnedNotification) {
            return ModelNotifications.getPinnedNotification(position);
        }
        return ModelNotifications.getNotification(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ConstraintLayout layout = (ConstraintLayout) (view == null ? inflater.inflate(R.layout.view_adapter_notification, viewGroup, false) : view);

        Notification notification = getItem(i);

        long cmp = new Date().getTime() - notification.when;
        String time;
        if (TimeUnit.MILLISECONDS.toMinutes(cmp) < 1) {
            time = TimeUnit.MILLISECONDS.toSeconds(cmp) + " s";
        } else if (TimeUnit.MILLISECONDS.toHours(cmp) < 1) {
            time = TimeUnit.MILLISECONDS.toMinutes(cmp) + " min";
        } else if (TimeUnit.MILLISECONDS.toDays(cmp) < 1) {
            time = TimeUnit.MILLISECONDS.toHours(cmp) + " h";
        } else {
            time = TimeUnit.MILLISECONDS.toDays(cmp) + "j";
        }

        ((ImageView) layout.findViewById(R.id.image_notification)).setImageResource(notification.getSmallIcon().getResId());
        ((TextView) layout.findViewById(R.id.text_message)).setText(notification.extras.getString(Notification.EXTRA_TEXT));
        ((TextView) layout.findViewById(R.id.text_date)).setText(time);

        OnSwipeTouchListener swipeTouchListener;

        if (pinnedNotification) {
            swipeTouchListener = new OnSwipeTouchListener(layout.getContext()) {
                @Override
                public void onSwipeLeft() {
                    super.onSwipeLeft();
                    ModelNotifications.transferNotificationToUnpinned(i);
                    ViewAdapterNotification.this.notifyDataSetChanged();
                    otherAdapter.notifyDataSetChanged();
                }
            };
        } else {
            swipeTouchListener = new OnSwipeTouchListener(layout.getContext()) {
                @Override
                public void onSwipeLeft() {
                    super.onSwipeLeft();
                    ModelNotifications.removeNotification(i);
                    ViewAdapterNotification.this.notifyDataSetChanged();
                }

                @Override
                public void onSwipeRight() {
                    super.onSwipeRight();
                    ModelNotifications.transferNotificationToPinned(i);
                    ViewAdapterNotification.this.notifyDataSetChanged();
                    otherAdapter.notifyDataSetChanged();
                }
            };
        }

        layout.setOnTouchListener(swipeTouchListener);

        return layout;
    }
}
