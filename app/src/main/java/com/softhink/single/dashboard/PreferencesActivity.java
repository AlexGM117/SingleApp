package com.softhink.single.dashboard;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.softhink.single.BaseActivity;
import com.softhink.single.Constants;
import com.softhink.single.R;
import com.softhink.single.login.LoginFragment;

public class PreferencesActivity extends BaseActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.containerPreferences, new PreferencesFragment(),
                Constants.PREFERENCESFRAGMENT).commit();
    }
}
