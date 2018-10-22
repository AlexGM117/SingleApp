package com.softhink.single.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.softhink.single.base.BaseActivity
import com.softhink.single.R
import com.softhink.single.ZooOutPager
import com.softhink.single.dashboard.MainContainer
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
        startActivity(Intent(this, MainContainer::class.java))
        finish()
    }
}
