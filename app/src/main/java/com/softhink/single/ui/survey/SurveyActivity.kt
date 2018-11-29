package com.softhink.single.ui.survey

import android.os.Bundle
import com.softhink.single.base.BaseActivity
import com.softhink.single.R
import com.softhink.single.ui.dashboard.TermsFragment

class SurveyActivity : BaseActivity(), TastesFragment.CallbackSurvey {

    private val surveyFlag = "SURVEY_DESTINATION"
    private var welcomeScreen : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        welcomeScreen = intent.getBooleanExtra(surveyFlag, false)

        if (welcomeScreen) {
            setUpToolbar("Encuesta", false)
            supportFragmentManager?.beginTransaction()?.
                    replace(R.id.containerSurvey, SurveyFragment())?.
                    commitNow()
        } else {
            setUpToolbar("Parámetros", true)
            supportFragmentManager?.beginTransaction()?.
                    replace(R.id.containerSurvey, PreferencesFragment())?.
                    commitNow()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentByTag(TermsFragment::class.java.simpleName) == null){
            super.onBackPressed()
        }
    }

    override fun showTerms() {
        if (welcomeScreen){
            supportFragmentManager?.
                    beginTransaction()?.
                    replace(R.id.containerSurvey, TermsFragment(), TermsFragment::class.java.simpleName)?.
                    commit()
        } else{
            //Call Service
            finish()
        }
    }
}
