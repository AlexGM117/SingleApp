package com.softhink.single.registro.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.softhink.single.BaseActivity;
import com.softhink.single.Constants;
import com.softhink.single.R;
import com.softhink.single.login.LoginActivity;
import com.softhink.single.registro.presenter.RegistroContract;
import com.softhink.single.registro.presenter.RegistroPresenter;
import org.jetbrains.annotations.Nullable;
import java.util.Date;

public class RegistroActivity extends BaseActivity implements View.OnClickListener,
        RegistroContract.DataContract,
        RegistroContract.AccountContract,
        RegistroContract.DataContract.CallbackData,
        RegistroContract.AccountContract.CallbackAccount,
        RegistroContract.PhotoProfileContract,
        RegistroContract.PhotoProfileContract.CallbackPhoto{

    private RegistroPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        presenter = new RegistroPresenter(this, this);

        final Context context = this;

        setUpToolbar("Registro", this);

        mFragmentManager = getSupportFragmentManager();
                mFragmentManager.beginTransaction()
                .add(R.id.registroContainer, new RegDataFragment(),
                        Constants.INSTANCE.getREGISTROUNOFRAGMENT())
                        .commit();
    }

    @Override
    public void onClick(View view) {
        if (mFragmentManager.getBackStackEntryCount() > 0) {
            mFragmentManager.popBackStack();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void dataForm(String name, Date date, String gender) {
        presenter.validateForm(name, date, gender);
    }

    @Override
    public void accountData(String email, String pss, String pss1) {
        presenter.validAccountData(email, pss, pss1);
    }

    @Override
    public void nameIsEmpty() {
        showMessageDialog(getString(R.string.empty_name));
    }

    @Override
    public void nameStringLenght() {
        showMessageDialog("Desbes agregar un nombre válido");
    }

    @Override
    public void dateEmpty() {
        showMessageDialog("Agrega tu fecha de nacimiento");
    }

    @Override
    public void isUnderAge() {
        showMessageDialog("Debes ser mayor de edad para poder utilizar la aplicacion");
    }

    @Override
    public void genderUnselected() {
        showMessageDialog("Selecciona H ó M");
    }

    @Override
    public void toAccountFragment() {
        mFragmentManager.beginTransaction()
                .add(R.id.registroContainer,
                new RegAccountFragment(), RegAccountFragment.class.getSimpleName())
                .addToBackStack(RegAccountFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void emailEmpty() {
        showMessageDialog("Agrega una direccion de correo electronico");
    }

    @Override
    public void invalidEmail() {
        showMessageDialog("Agrega una direccion de correo electronico valida");
    }

    @Override
    public void passEmpty() {
        showMessageDialog("Ingresa una contraseña y confirmala");
    }

    @Override
    public void passLenght() {
        showMessageDialog("Tú contraseña debe contener minimo 8 caracteres");
    }

    @Override
    public void passNotMatch() {
        showMessageDialog("Verifica la contraseña que ingresaste");
    }

    @Override
    public void finishRegistro() {
        mFragmentManager.beginTransaction()
                .add(R.id.registroContainer,
                        new RegistroTresFragment(), RegistroTresFragment.class.getSimpleName())
                .addToBackStack(RegistroTresFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void callService(@Nullable String string) {
        presenter.sendRegistro(string);
    }
}