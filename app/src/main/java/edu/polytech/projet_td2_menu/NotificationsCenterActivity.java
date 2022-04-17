package edu.polytech.projet_td2_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        BaseAdapter adapter = new ViewAdapterNotification(getApplicationContext());
        ((ListView) findViewById(R.id.list_notifications)).setAdapter(adapter);

        LinearLayout layout = findViewById(R.id.buttons_sort);
        layout.findViewById(R.id.increase_time_button).setOnClickListener(click -> {
            ModelNotifications.sortTimeIncrease();
            adapter.notifyDataSetChanged();
        });

        layout.findViewById(R.id.decrease_time_button).setOnClickListener(click -> {
            ModelNotifications.sortTimeDecrease();
            adapter.notifyDataSetChanged();
        });
    }
}