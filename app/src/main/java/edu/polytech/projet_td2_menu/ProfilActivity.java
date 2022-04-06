package edu.polytech.projet_td2_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfilActivity extends AppCompatActivity {

    private Button buttonTMP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        buttonTMP = (Button) findViewById(R.id.tmp_camera);
        buttonTMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilActivity.this,PhotoLoadActivity.class);
                startActivity(intent);
            }
        });
    }
}