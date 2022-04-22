package edu.polytech.projet_td2_menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import edu.polytech.projet_td2_menu.adapters.ViewAdapterNotification;
import edu.polytech.projet_td2_menu.models.data.ModelNotifications;

public class NotificationsCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_center);

        NotificationView view = new NotificationView(getApplicationContext(), (ConstraintLayout) findViewById(R.id.notification_center));

        NotificationsController controller = new NotificationsController(view);
        view.setController(controller);
    }
}