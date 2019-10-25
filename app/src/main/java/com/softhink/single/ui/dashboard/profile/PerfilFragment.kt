package com.softhink.single.ui.dashboard.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.softhink.single.R
import com.softhink.single.ui.base.BaseFragment
import com.softhink.single.ui.dashboard.MapsActivity
import com.softhink.single.ui.survey.view.SurveyActivity
import kotlinx.android.synthetic.main.fragment_perfil.*

/**
 * A simple [Fragment] subclass.
 */
class PerfilFragment : BaseFragment(), View.OnClickListener {

    private val surveyFlag = "SURVEY_DESTINATION"
    private lateinit var mViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
    }

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

            loadSingleData()
        }
    }

    private fun loadSingleData() {
        mViewModel.mData?.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                singleUserName.text = it[0].fullName
                singleDescription.text = it[0].description
            }
        })
    }

    override fun onClick(v: View) {
        var intent: Intent? = null
        when (v.id) {
            R.id.toEditParams -> intent = Intent(activity, SurveyActivity::class.java)
                    .putExtra("USERNAME", "username")
                    .putExtra(surveyFlag, false)

            R.id.toEditProfile -> intent = Intent(activity, EditProfileActivity::class.java)

            R.id.singlear -> intent = Intent(activity, MapsActivity::class.java)
        }
        startActivity(intent)
    }
}
