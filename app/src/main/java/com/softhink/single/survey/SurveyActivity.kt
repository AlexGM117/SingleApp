package com.softhink.single.survey

import android.os.Bundle
import android.view.View
import com.softhink.single.BaseActivity
import com.softhink.single.Constants
import com.softhink.single.R
import kotlinx.android.synthetic.main.survey_layout.*

class SurveyActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        setUpToolbar("Encuesta", false)

        btnSurvey.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnSurvey -> {
                setNavigationUp()
                mFragmentManager.beginTransaction()
                        .add(R.id.containerPreferences, PreferencesFragment(),
                                Constants.PREFERENCESFRAGMENT).commit()
            }
        }

    }
}
