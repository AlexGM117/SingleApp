package com.softhink.single.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.softhink.single.R
import com.softhink.single.survey.SurveyActivity


/**
 * A simple [Fragment] subclass.
 */
class PerfilFragment : Fragment(), View.OnClickListener {

    private var editParam: TextView? = null
    private var editProfile: TextView? = null
    private var view: View? = null
    private var singlear: Button? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view = inflater!!.inflate(R.layout.fragment_perfil, container, false)
        return view
    }

    override fun onViewCreated(@NonNull v: View?, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)
        if (savedInstanceState == null) {
            editParam = view!!.findViewById(R.id.toEditParams)
            editProfile = view!!.findViewById(R.id.toEditProfile)
            singlear = view!!.findViewById(R.id.singlear)

            editProfile!!.setOnClickListener(this)
            editParam!!.setOnClickListener(this)
            singlear!!.setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {
        var intent: Intent? = null
        when (v.id) {
            R.id.toEditParams -> intent = Intent(activity, SurveyActivity::class.java)

            R.id.toEditProfile -> intent = Intent(activity, EditProfileActivity::class.java)

            R.id.singlear -> intent = Intent(activity, MapsActivity::class.java)
        }
        startActivity(intent)
    }
}// Required empty public constructor
