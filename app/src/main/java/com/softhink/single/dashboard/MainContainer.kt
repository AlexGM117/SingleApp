package com.softhink.single.dashboard

import android.content.Intent
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.softhink.single.BaseActivity
import com.softhink.single.login.ui.LoginActivity
import com.softhink.single.onboarding.OnboardingActivity
import com.softhink.single.R
import com.softhink.single.dashboard.adapters.PagerAdapter
import com.softhink.single.dashboard.dates.DatesFragment
import com.softhink.single.dashboard.places.PlacesFragment
import com.softhink.single.dashboard.profile.PerfilFragment

class MainContainer : BaseActivity() {

    private var viewPager: ViewPager? = null
    private var tabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_container)

        if (savedInstanceState == null) {
            setUpToolbar("", false)

            viewPager = findViewById(R.id.viewPager)
            setUpViewPager()
            tabLayout = findViewById(R.id.tabs)
            tabLayout?.setupWithViewPager(viewPager)
            setupTabIcons()
        }
    }

    private fun setupTabIcons() {
        tabLayout?.getTabAt(0)?.setIcon(R.drawable.mis_citas)
        tabLayout?.getTabAt(1)?.setIcon(R.drawable.perfil)
        tabLayout?.getTabAt(2)?.setIcon(R.drawable.lugares)
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
        val FRAGMENT_KEY = "FRAGMENT_MENU"

        when (item.itemId) {
            R.id.menuHelp -> startActivity(Intent(this, HelpActivity::class.java).putExtra(FRAGMENT_KEY, 0))

            R.id.menuAsk -> startActivity(Intent(this, HelpActivity::class.java).putExtra(FRAGMENT_KEY, 1))

            R.id.menuTerms -> startActivity(Intent(this, HelpActivity::class.java).putExtra(FRAGMENT_KEY, 2))

            R.id.menuAbout -> startActivity(Intent(this, HelpActivity::class.java).putExtra(FRAGMENT_KEY, 3))

            R.id.menuOnboarding -> startActivity(Intent(this, OnboardingActivity::class.java))

            R.id.menuLogout -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        return true
    }
}

