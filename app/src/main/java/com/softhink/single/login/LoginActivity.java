package com.softhink.single.login;

import android.os.Bundle;
import android.view.View;
import com.softhink.single.BaseActivity;
import com.softhink.single.Constants;
import com.softhink.single.R;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpToolbar("", true);
        getToolbar().setVisibility(View.GONE);

        mFragmentManager.beginTransaction().add(R.id.containerLogin, new LoginFragment(),
                Constants.LOGINFRAGMENT).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            getToolbar().setVisibility(View.GONE);
        }
        super.onBackPressed();
    }
}
