package com.softhink.single.login;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.softhink.single.BaseActivity;
import com.softhink.single.Constants;
import com.softhink.single.R;

public class LoginActivity extends BaseActivity implements LoginFragment.onNavigationListener,
        View.OnClickListener {

    private FragmentManager fragmentManager;
    private Toolbar toolbar;
    private LinearLayout layoutToolbar;
    private ImageView toolbarBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.toolbar);
        toolbarBack = findViewById(R.id.back);
        layoutToolbar = findViewById(R.id.include2);

        layoutToolbar.setVisibility(View.GONE);
        toolbarBack.setOnClickListener(this);
        setSupportActionBar(toolbar);


        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.containerLogin, new LoginFragment(),
                Constants.LOGINFRAGMENT).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onNavigationclick() {
        layoutToolbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                if (fragmentManager.getBackStackEntryCount() == 1) {
                    layoutToolbar.setVisibility(View.GONE);
                }
                fragmentManager.popBackStack();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (fragmentManager.findFragmentByTag(Constants.LOGINFRAGMENT).isVisible()){
            layoutToolbar.setVisibility(View.GONE);
        }
    }
}
