package com.softhink.single.registro.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.softhink.single.Constants;
import com.softhink.single.R;
import com.softhink.single.dashboard.MainContainer;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroTresFragment extends Fragment implements View.OnClickListener {

    private ImageView btnBack;
    private Button btnEnviarRegistro;

    public RegistroTresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro_tres, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnBack = view.findViewById(R.id.buttonBack2);
        btnEnviarRegistro = view.findViewById(R.id.button3);
        btnBack.setOnClickListener(this);
        btnEnviarRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()){
            case R.id.buttonBack2:
                fragmentManager.popBackStack();
                break;
            case R.id.button3:
                getActivity().startActivity(new Intent(getActivity(), MainContainer.class));
                break;
        }
    }
}
