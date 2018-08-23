package com.softhink.single.registro.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.softhink.single.Constants;
import com.softhink.single.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegAccountFragment extends Fragment implements View.OnClickListener {

    ImageView btnNext;
    ImageView btnBack;

    public RegAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro_dos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnNext = view.findViewById(R.id.btnLastPage);
        btnBack = view.findViewById(R.id.btnFirstPage);
        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (v.getId()){
            case R.id.btnLastPage:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.registroContainer, new RegistroTresFragment())
                        .addToBackStack(Constants.INSTANCE.getREGISTROTRESFRAGMENT())
                        .commit();
                break;

            case R.id.btnFirstPage:
                fragmentManager.popBackStack();
                break;
        }
    }
}
