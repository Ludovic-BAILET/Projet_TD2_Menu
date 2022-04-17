package edu.polytech.projet_td2_menu.adapters;

import android.app.Notification;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import edu.polytech.projet_td2_menu.R;
import edu.polytech.projet_td2_menu.models.Ingredient;
import edu.polytech.projet_td2_menu.models.data.ModelNotifications;

public class ViewAdapterNotification extends BaseAdapter {
    private final LayoutInflater inflater;

    public ViewAdapterNotification(Context context) {
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return ModelNotifications.size();
    }

    @Override
    public Notification getItem(int position) {
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

        return layout;
    }
}
