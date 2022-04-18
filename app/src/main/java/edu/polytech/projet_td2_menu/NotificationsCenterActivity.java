package edu.polytech.projet_td2_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.concurrent.TimeUnit;

import edu.polytech.projet_td2_menu.adapters.ViewAdapterNotification;
import edu.polytech.projet_td2_menu.models.data.ModelNotifications;

public class NotificationsCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_center);

        BaseAdapter adapter_base = null;
        BaseAdapter adapter_pinned = new ViewAdapterNotification(getApplicationContext(), true, adapter_base);
        adapter_base = new ViewAdapterNotification(getApplicationContext(), false, adapter_pinned);
        LinearLayout layout_list_view = findViewById(R.id.layout_list_view);
        ((ListView) layout_list_view.findViewById(R.id.list_notifications)).setAdapter(adapter_base);
        ((ListView) layout_list_view.findViewById(R.id.list_notifications_pinned)).setAdapter(adapter_pinned);

        LinearLayout layout_buttons = findViewById(R.id.buttons_sort);
        BaseAdapter finalAdapter_base = adapter_base;
        layout_buttons.findViewById(R.id.increase_time_button).setOnClickListener(click -> {
            ModelNotifications.sortTimeIncrease();
            finalAdapter_base.notifyDataSetChanged();
            adapter_pinned.notifyDataSetChanged();
        });

        layout_buttons.findViewById(R.id.decrease_time_button).setOnClickListener(click -> {
            ModelNotifications.sortTimeDecrease();
            finalAdapter_base.notifyDataSetChanged();
            adapter_pinned.notifyDataSetChanged();
        });
    }
}