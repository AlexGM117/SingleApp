package com.softhink.single.registro.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.softhink.single.R;
import com.softhink.single.registro.presenter.RegistroContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegAccountFragment extends Fragment implements View.OnClickListener {

    private ImageView btnNext;
    private ImageView btnBack;
    private TextInputLayout inputEmail;
    private TextInputLayout inputPss;
    private TextInputLayout inputPss2;
    private RegistroContract.AccountContract.CallbackAccount callback;

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

        if (savedInstanceState == null) {
            inputEmail = view.findViewById(R.id.inputEmail);
            inputPss = view.findViewById(R.id.inputPss);
            inputPss2 = view.findViewById(R.id.inputPss2);
            btnNext = view.findViewById(R.id.btnLastPage);
            btnBack = view.findViewById(R.id.btnFirstPage);

            btnNext.setOnClickListener(this);
            btnBack.setOnClickListener(this);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (RegistroContract.AccountContract.CallbackAccount) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implements interface");
        }

    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (v.getId()){
            case R.id.btnLastPage:
                callback.accountData(inputEmail.getEditText().getText().toString(),
                        inputPss.getEditText().getText().toString(),
                        inputPss2.getEditText().getText().toString());
                break;

            case R.id.btnFirstPage:
                fragmentManager.popBackStack();
                break;
        }
    }
}
