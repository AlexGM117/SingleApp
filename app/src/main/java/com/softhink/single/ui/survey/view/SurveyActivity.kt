package com.softhink.single.ui.survey.view

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.softhink.single.ui.base.BaseActivity
import com.softhink.single.R
import com.softhink.single.ui.dashboard.MainContainer
import com.softhink.single.ui.dashboard.TermsFragment
import com.softhink.single.ui.registro.Status.*
import com.softhink.single.ui.survey.SurveyViewModel

class SurveyActivity : BaseActivity() {

    private val surveyFlag = "SURVEY_DESTINATION"
    private var welcomeScreen : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        welcomeScreen = intent.getBooleanExtra(surveyFlag, false)
        val model = ViewModelProviders.of(this).get(SurveyViewModel::class.java)
        model.setUsername(intent.getStringExtra("USERNAME"))
        model.getLists().observe(this, Observer {
            if (it != null) {
                when(it.status) {
                    SUCCESS -> initSurvey()
                    ERROR -> showMessageDialog(getString(R.string.error_generic_message), positiveClick = {
                        finish()
                        if (welcomeScreen) {
                            startActivity(Intent(this, MainContainer::class.java))
                        }
                    })
                    FAILED -> showMessageDialog(getString(R.string.error_generic_message), positiveClick = {
                        finish()
                        if (welcomeScreen) {
                            startActivity(Intent(this, MainContainer::class.java))
                        }
                    })
                }
            }
        })

        model.surveyResponse.observe(this, Observer {
            if(it != null) {
                when(it.status) {
                    SUCCESS -> showTerms()
                    ERROR -> showMessageDialog(it.message!!, positiveClick = {
                        showTerms()
                    })
                    FAILED -> showMessageDialog(getString(R.string.error_generic_message), positiveClick = {
                        showTerms()
                    })
                }
            }
        })
    }

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentByTag(TermsFragment::class.java.simpleName) == null){
            super.onBackPressed()
        }
    }

    fun showTerms() {
        if (welcomeScreen){
            supportFragmentManager.beginTransaction().replace(R.id.containerSurvey, TermsFragment(), TermsFragment::class.java.simpleName)?.
                    commit()
        } else{
            finish()
        }
    }

    private fun initSurvey(){
        if (welcomeScreen) {
            setUpToolbar("Encuesta", false)
            supportFragmentManager.beginTransaction().replace(R.id.containerSurvey, SurveyFragment()).commitNow()
        } else {
            setUpToolbar("Parámetros", true)
            supportFragmentManager.beginTransaction().replace(R.id.containerSurvey, PreferencesFragment()).commitNow()
        }
    }
}
