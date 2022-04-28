package edu.polytech.projet_td2_menu.MVC;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import edu.polytech.projet_td2_menu.R;
import edu.polytech.projet_td2_menu.fragments.NavigationBar;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterface;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterfaceImplementation;
import edu.polytech.projet_td2_menu.models.data.ModelNotifications;

public class NotificationsCenterActivity extends AppCompatActivity implements NavigationBarInterface {
    private NavigationBarInterfaceImplementation implementation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_center);

        implementation = new NavigationBarInterfaceImplementation(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.navigation_bar, new NavigationBar()).commit();

        NotificationView view = new NotificationView(getApplicationContext(), findViewById(R.id.notification_center));

        NotificationsController controller = new NotificationsController(view, this);
        ModelNotifications.getInstance().addObserver(view);
        view.setController(controller);
        ModelNotifications.getInstance().setController(controller);
    }

    @Override
    public void onButtonPlanningClicked(View v) {
        implementation.onButtonPlanningClicked(v);
    }

    @Override
    public void onButtonProfilClicked(View v) {
        implementation.onButtonProfilClicked(v);
    }

    @Override
    public void onButtonMesCoursesClicked(View v) {
        implementation.onButtonMesCoursesClicked(v);
    }

    @Override
    public void onButtonRecettesClicked(View v) {
        implementation.onButtonRecettesClicked(v);
    }

    public NotificationsCenterActivity getActivity() {
        return NotificationsCenterActivity.this;
    }

}