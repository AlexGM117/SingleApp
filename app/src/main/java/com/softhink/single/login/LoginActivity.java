package com.softhink.single.login;

import android.os.Bundle;
import com.softhink.single.BaseActivity;
import com.softhink.single.Constants;
import com.softhink.single.R;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFragmentManager.beginTransaction()
                .add(R.id.containerLogin, new LoginFragment(),
                Constants.INSTANCE.getLOGINFRAGMENT())
                .commit();
    }

}
