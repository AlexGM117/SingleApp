package com.softhink.single.survey


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softhink.single.base.BaseFragment
import com.softhink.single.Constants
import com.softhink.single.R
import kotlinx.android.synthetic.main.fragment_survey.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SurveyFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_survey, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSurvey.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSurvey -> {
                updateToolbar("Encuesta", true)
                fragmentManager?.
                        beginTransaction()?.
                        add(R.id.containerPreferences, PreferencesFragment(),
                                Constants.PREFERENCESFRAGMENT)?.
                        commit()
            }
        }
    }
}
