package com.softhink.single.dashboard.adapters

import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import java.util.ArrayList

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val fragments = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    fun addFragments(fragment: Fragment, title: String) {
        fragments.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence {
        return mFragmentTitleList[position]
    }
}
