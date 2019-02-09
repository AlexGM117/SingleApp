package com.softhink.single.ui.survey.view

import android.os.Bundle
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.appyvet.materialrangebar.RangeBar
import com.softhink.single.ui.base.BaseFragment
import com.softhink.single.util.Constants
import com.softhink.single.R
import com.softhink.single.ui.survey.SurveyViewModel
import kotlinx.android.synthetic.main.arrow_next.*
import kotlinx.android.synthetic.main.fragment_preferences.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class PreferencesFragment : BaseFragment(), View.OnClickListener, RangeBar.OnRangeBarChangeListener {

    private lateinit var viewModel: SurveyViewModel
    private var minAge: Int = -1
    private var maxAge: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(SurveyViewModel::class.java)
        }?:throw Exception("Invalid Activity")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preferences, container, false)
    }

    override fun onViewCreated(@NonNull view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            minAge = 18
            maxAge = 23
            ageRangebar.setRangePinsByValue(18f, 23f)
            ageRangebar.setOnRangeBarChangeListener(this)
            btnNextPage.setOnClickListener(this)
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnNextPage -> {
                if (!switchMan.isChecked && !switchWoman.isChecked) {
                    showMessageDialog("Debes seleccionar al menos una opcion")
                } else {
                    viewModel.savePreferences(switchMan.isChecked, switchWoman.isChecked, switchVisible.isChecked, minAge, maxAge)
                    val fragmentManager = fragmentManager
                    fragmentManager!!.beginTransaction()
                            .replace(R.id.containerSurvey, InterestsFragment())
                            .addToBackStack(Constants.PREFERENCESFRAGMENT)
                            .commit()
                }
            }
        }
    }

    override fun onRangeChangeListener(rangeBar: RangeBar, leftPinIndex: Int, rightPinIndex: Int, leftPinValue: String, rightPinValue: String) {
        if (leftPinValue > rightPinValue) {
            minAge = rightPinValue.toInt()
            maxAge = leftPinValue.toInt()
        } else {
            minAge = leftPinValue.toInt()
            maxAge = rightPinValue.toInt()
        }
    }
}