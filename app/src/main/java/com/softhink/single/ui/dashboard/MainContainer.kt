package com.softhink.single.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.facebook.login.LoginManager
import com.softhink.single.ui.common.Converter
import com.softhink.single.ui.base.BaseActivity
import com.softhink.single.ui.login.view.LoginActivity
import com.softhink.single.ui.onboarding.OnboardingActivity
import com.softhink.single.R
import com.softhink.single.data.manager.SinglePreferences
import com.softhink.single.ui.dashboard.adapters.PagerAdapter
import kotlinx.android.synthetic.main.activity_main_container.*

class MainContainer : BaseActivity() {

    private var pushCount = 0

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
        tabs.getTabAt(0)?.setIcon(R.drawable.ic_dates_tab)
        tabs.getTabAt(1)?.setIcon(R.drawable.ic_profile_tab)
        tabs.getTabAt(2)?.setIcon(R.drawable.ic_place_tab)
    }

    private fun setUpViewPager() {
        viewPager.adapter = PagerAdapter(supportFragmentManager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu.findItem(R.id.actionNotifications)
        menuItem.icon = Converter().convertLayoutToImage(this, pushCount, R.drawable.ic_custom_range_on)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.actionNotifications -> {
                if (pushCount == 10){
                    pushCount--
                } else{
                    pushCount++
                }
                invalidateOptionsMenu()
            }

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

