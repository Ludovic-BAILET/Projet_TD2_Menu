package edu.polytech.projet_td2_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ListView;

import edu.polytech.projet_td2_menu.adapters.ViewAdapterAdvancedFilter;

public class FilterActivity extends AppCompatActivity {

    private ViewAdapterAdvancedFilter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        adapter = new ViewAdapterAdvancedFilter(getApplicationContext());
        ViewGroup layout = findViewById(R.id.view_filters);
        ((ListView) layout.findViewById(R.id.list_filters)).setAdapter(adapter);
    }
}