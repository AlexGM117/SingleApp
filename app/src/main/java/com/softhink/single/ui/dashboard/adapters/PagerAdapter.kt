package com.softhink.single.ui.dashboard.adapters

import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.softhink.single.ui.dashboard.dates.DatesFragment
import com.softhink.single.ui.dashboard.places.PlacesFragment
import com.softhink.single.ui.dashboard.profile.PerfilFragment

import java.util.ArrayList

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val fragments = listOf(DatesFragment(), PerfilFragment(), PlacesFragment())
    private val mFragmentTitleList = listOf("MIS CITAS", "PERFIL", "LUGARES")

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
