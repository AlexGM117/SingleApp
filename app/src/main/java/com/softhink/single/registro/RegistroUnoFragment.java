package com.softhink.single.registro;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.softhink.single.Constants;
import com.softhink.single.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroUnoFragment extends Fragment implements View.OnClickListener {

    private ImageView btnNext;

    public RegistroUnoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro_uno, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnNext = view.findViewById(R.id.buttonNext);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonNext:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.containerLogin, new RegistroDosFragment())
                        .addToBackStack(Constants.REGISTRODOSFRAGMENT)
                        .commit();
                break;
        }
    }
}
