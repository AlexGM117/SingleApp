package com.softhink.single.dashboard.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softhink.single.R
import com.softhink.single.dashboard.MapsActivity
import com.softhink.single.survey.SurveyActivity
import kotlinx.android.synthetic.main.fragment_perfil.*

/**
 * A simple [Fragment] subclass.
 */
class PerfilFragment : Fragment(), View.OnClickListener {

    private val surveyFlag = "SURVEY_DESTINATION"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)
        if (savedInstanceState == null) {
            toEditProfile.setOnClickListener(this)
            toEditParams.setOnClickListener(this)
            singlear.setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {
        var intent: Intent? = null
        when (v.id) {
            R.id.toEditParams -> intent = Intent(activity, SurveyActivity::class.java).putExtra(surveyFlag, false)

            R.id.toEditProfile -> intent = Intent(activity, EditProfileActivity::class.java)

            R.id.singlear -> intent = Intent(activity, MapsActivity::class.java)
        }
        startActivity(intent)
    }
}
