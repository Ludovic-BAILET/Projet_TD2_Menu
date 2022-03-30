package edu.polytech.projet_td2_menu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import edu.polytech.projet_td2_menu.R;

public class ValidationCancelButtons extends Fragment {

    public ValidationCancelButtons() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.validation_cancel_buttons, container, false);
    }
}
