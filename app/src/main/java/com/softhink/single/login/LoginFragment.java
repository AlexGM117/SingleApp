package com.softhink.single.login;

import android.content.Context;
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
import android.widget.LinearLayout;

import com.softhink.single.Constants;
import com.softhink.single.R;
import com.softhink.single.dashboard.MainContainer;
import com.softhink.single.registro.view.RegistroActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Context context;
    private onNavigationListener navigationListener;
    private LinearLayout layoutRegistro;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        context = getActivity();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = view.findViewById(R.id.button);
        Button button2 = view.findViewById(R.id.button2);
        layoutRegistro = view.findViewById(R.id.registro);
        layoutRegistro.setOnClickListener(this);

        button2.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            navigationListener = (onNavigationListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (v.getId()){
            case R.id.button2:
                getActivity().finish();
                startActivity(new Intent(context, MainContainer.class));
                break;

            case R.id.button:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.containerLogin,
                        new LoginCommonFragment())
                        .addToBackStack(Constants.LOGINCOMMONFRAGMENT)
                        .commit();
                navigationListener.onNavigationclick();
                break;

            case R.id.registro:
                startActivity(new Intent(getActivity(), RegistroActivity.class));
                break;
        }
    }

    public interface onNavigationListener {
        void onNavigationclick();
    }
}
