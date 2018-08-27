package com.softhink.single.dashboard;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.softhink.single.MapsActivity;
import com.softhink.single.R;
import com.softhink.single.survey.SurveyActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment implements View.OnClickListener {

    private TextView editParam;
    private TextView editProfile;
    private View view;
    private Button singlear;


    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_perfil, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        if (savedInstanceState == null) {
            editParam = view.findViewById(R.id.toEditParams);
            editProfile = view.findViewById(R.id.toEditProfile);
            singlear = view.findViewById(R.id.singlear);

            editProfile.setOnClickListener(this);
            editParam.setOnClickListener(this);
            singlear.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.toEditParams:
                intent = new Intent(getActivity(), SurveyActivity.class);
                break;

            case R.id.toEditProfile:
                intent = new Intent(getActivity(), EditProfileActivity.class);
                break;

            case R.id.singlear:
                intent = new Intent(getActivity(), MapsActivity.class);
                break;
        }
        startActivity(intent);
    }
}
