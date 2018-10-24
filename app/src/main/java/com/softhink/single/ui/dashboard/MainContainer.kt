package com.softhink.single.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.facebook.login.LoginManager
import com.softhink.single.base.BaseActivity
import com.softhink.single.ui.login.ui.LoginActivity
import com.softhink.single.ui.onboarding.OnboardingActivity
import com.softhink.single.R
import com.softhink.single.SinglePreferences
import com.softhink.single.ui.dashboard.adapters.PagerAdapter
import com.softhink.single.ui.dashboard.dates.DatesFragment
import com.softhink.single.ui.dashboard.places.PlacesFragment
import com.softhink.single.ui.dashboard.profile.PerfilFragment
import kotlinx.android.synthetic.main.activity_main_container.*

class MainContainer : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_container)

        if (savedInstanceState == null) {
            setUpToolbar("", false)

            setUpViewPager()
            tabs.setupWithViewPager(viewPager)
            setupTabIcons()
            viewPager.currentItem = 1
        }
    }

    private fun setupTabIcons() {
        tabs.getTabAt(0)?.setIcon(R.drawable.mis_citas)
        tabs.getTabAt(1)?.setIcon(R.drawable.perfil)
        tabs.getTabAt(2)?.setIcon(R.drawable.lugares)
    }

    private fun setUpViewPager() {
        val adapter = PagerAdapter(supportFragmentManager)
        adapter.addFragments(DatesFragment(), "MIS CITAS")
        adapter.addFragments(PerfilFragment(), "PERFIL")
        adapter.addFragments(PlacesFragment(), "LUGARES")

        viewPager!!.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menuHelp -> startActivity(Intent(this, HelpActivity::class.java).putExtra(FRAGMENT_KEY, 0))

            R.id.menuAsk -> startActivity(Intent(this, HelpActivity::class.java).putExtra(FRAGMENT_KEY, 1))

            R.id.menuTerms -> startActivity(Intent(this, HelpActivity::class.java).putExtra(FRAGMENT_KEY, 2))

            R.id.menuAbout -> startActivity(Intent(this, HelpActivity::class.java).putExtra(FRAGMENT_KEY, 3))

            R.id.menuOnboarding -> startActivity(Intent(this, OnboardingActivity::class.java))

            R.id.menuLogout -> {
                LoginManager.getInstance().logOut()
                SinglePreferences().setAccessToken(null)
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        return true
    }

    companion object {
        const val FRAGMENT_KEY = "FRAGMENT_MENU"
    }
}

