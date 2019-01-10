package com.softhink.single.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.softhink.single.ui.base.BaseActivity
import com.softhink.single.R
import com.softhink.single.data.manager.SinglePreferences
import com.softhink.single.ui.common.ZooOutPager
import com.softhink.single.ui.dashboard.MainContainer
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : BaseActivity(), ViewPager.OnPageChangeListener,
        OnboardingPagerAdapter.OnClickPager {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        setUpToolbar("Tutorial", true)

        onboardingPager.adapter = OnboardingPagerAdapter(arrayOf(R.layout.onboarding_singlear,
                R.layout.onboarding_slide,
                R.layout.onboarding_match,
                R.layout.onboarding_chat),
                this, this)

        onboardingPager.setPageTransformer(true, ZooOutPager())

        onboardingPager.addOnPageChangeListener(this)
    }

    override fun onPageScrollStateChanged(p0: Int) {

    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

    }

    override fun onPageSelected(p0: Int) {

    }

    override fun onSkipOnboarding() {
        if (intent.getBooleanExtra(key, false)){
            SinglePreferences().showTutorial = false
            SinglePreferences().firstAccess = false
            startActivity(Intent(this, MainContainer::class.java))
            finish()
        } else {
            finish()
        }
    }

    companion object {
        private const val key = "TermsFragment"
    }
}
