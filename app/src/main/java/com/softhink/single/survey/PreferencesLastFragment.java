package com.softhink.single.survey;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.softhink.single.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreferencesLastFragment extends Fragment implements View.OnClickListener {

    private ImageButton btnBack;
    private Button btnSaveChanges;

    public PreferencesLastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preferences_last, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnBack = view.findViewById(R.id.buttonBack2);
        btnBack.setOnClickListener(this);
        btnSaveChanges = view.findViewById(R.id.btnSave);
        btnSaveChanges.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonBack2:
                getFragmentManager().popBackStack();
                break;

            case R.id.btnSave:
                getActivity().finish();
                break;
        }
    }
}
