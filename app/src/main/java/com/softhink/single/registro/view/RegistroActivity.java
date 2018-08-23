package com.softhink.single.registro.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.softhink.single.BaseActivity;
import com.softhink.single.Constants;
import com.softhink.single.R;
import com.softhink.single.login.LoginActivity;
import com.softhink.single.registro.presenter.RegAccountContract;
import com.softhink.single.registro.presenter.RegDataContract;

import org.jetbrains.annotations.NotNull;

public class RegistroActivity extends BaseActivity implements View.OnClickListener,
        RegDataContract.CallbackData,
        RegAccountContract.CallbackAccount {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        final Context context = this;

        setUpToolbar("Registro", this);

        mFragmentManager = getSupportFragmentManager();
                mFragmentManager.beginTransaction()
                .add(R.id.registroContainer, new RegDataFragment(), Constants.INSTANCE.getREGISTROUNOFRAGMENT()).commit();
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
    public void dataForm(@NotNull String name, @NotNull String date, @NotNull String gender) {

    }

    @Override
    public void accountData(@NotNull String email, @NotNull String pss, @NotNull String pss1) {

    }
}
