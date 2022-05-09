package edu.polytech.projet_td2_menu.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import edu.polytech.projet_td2_menu.R;

public class ValidationCancelButtons extends Fragment {

    private ValidationCancelButtonsInterface callback;

    public ValidationCancelButtons() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.validation_cancel_buttons, container, false);
        callback.onClickValid(layout.findViewById(R.id.valid_button));
        callback.onClickCancel(layout.findViewById(R.id.cancel_button));
        return layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.createCallBackToParentActivity();
    }

    private void createCallBackToParentActivity() {
        try {
            callback = (ValidationCancelButtonsInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e
                    + " must implement NavigationBarInterface");
        }

    }
}
