package com.softhink.single.survey;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.softhink.single.BaseActivity;
import com.softhink.single.Constants;
import com.softhink.single.R;

public class SurveyActivity extends BaseActivity implements View.OnClickListener{

    private Button btnStartSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        setUpToolbar("Encuesta", false);

        btnStartSurvey = findViewById(R.id.btnSurvey);
        btnStartSurvey.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnSurvey:
                setNavigationUp();
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.containerPreferences, new PreferencesFragment(),
                        Constants.PREFERENCESFRAGMENT).commit();
                break;
        }

    }
}
