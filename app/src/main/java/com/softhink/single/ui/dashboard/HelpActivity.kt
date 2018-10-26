package com.softhink.single.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.softhink.single.base.BaseActivity
import com.softhink.single.R

class HelpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        val fragment = intent.getIntExtra(FRAGMENT_KEY, -1)

        when(fragment){
            FEEDBACK -> initFragment(HelpUsFragment(), "SDK de ayuda")
            FAQ -> initFragment(FaqFragment(), "Preguntas frecuentes")
            TERMS -> initFragment(TermsFragment(), "Términos y condiciones")
            ABOUT -> initFragment(Fragment(), "Acerca de")
        }
    }

    private fun initFragment(fragment: Fragment, title: String){
        setUpToolbar(title, true)
        supportFragmentManager?.
                beginTransaction()?.
                replace(R.id.helpContainer, fragment)?.
                commitNow()
    }

    companion object {
        private const val FEEDBACK = 0
        private const val FAQ = 1
        private const val TERMS = 2
        private const val ABOUT = 3
        private const val FRAGMENT_KEY = "FRAGMENT_MENU"
    }
}
