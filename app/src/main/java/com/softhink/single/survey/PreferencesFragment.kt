package com.softhink.single.survey

import android.os.Bundle
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton

import com.appyvet.materialrangebar.RangeBar
import com.softhink.single.BaseFragment
import com.softhink.single.Constants
import com.softhink.single.R
import kotlinx.android.synthetic.main.arrow_next.*
import kotlinx.android.synthetic.main.fragment_preferences.*


/**
 * A simple [Fragment] subclass.
 */
class PreferencesFragment : BaseFragment(), View.OnClickListener, RangeBar.OnRangeBarChangeListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preferences, container, false)
    }

    override fun onViewCreated(@NonNull view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            ageRangebar.setRangePinsByValue(18f, 28f)
            ageRangebar.setOnRangeBarChangeListener(this)
            btnNextPage.setOnClickListener(this)
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnNextPage -> {
                val fragmentManager = fragmentManager
                fragmentManager!!.beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.containerPreferences, InterestsFragment())
                        .addToBackStack(Constants.PREFERENCESFRAGMENT)
                        .commit()
            }
        }
    }

    override fun onRangeChangeListener(rangeBar: RangeBar, leftPinIndex: Int, rightPinIndex: Int, leftPinValue: String, rightPinValue: String) {

    }
}// Required empty public constructor
