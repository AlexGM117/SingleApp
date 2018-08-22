package com.softhink.single.registro.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.softhink.single.BaseActivity;
import com.softhink.single.Constants;
import com.softhink.single.R;

public class RegistroActivity extends BaseActivity {

    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        setUpToolbar("Registro", true);

        fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                .add(R.id.registroContainer, new RegistroFormFragment(), Constants.REGISTROUNOFRAGMENT).commit();
    }
}
