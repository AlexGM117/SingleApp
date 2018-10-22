package com.softhink.single.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softhink.single.Constants
import com.softhink.single.R
import com.softhink.single.dashboard.MainContainer
import com.softhink.single.registro.view.RegistroActivity
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            registro.setOnClickListener(this)
            btnLoginFB.setOnClickListener(this)
            btnDoLogin.setOnClickListener(this)
        }
    }


    override fun onClick(v: View) {
        val fragmentManager = fragmentManager
        when (v.id) {
            R.id.btnLoginFB -> {
                activity?.finish()
                startActivity(Intent(context, MainContainer::class.java))
            }

            R.id.btnDoLogin -> fragmentManager?.beginTransaction()
                    ?.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                            R.anim.enter_from_left, R.anim.exit_to_right)
                    ?.replace(R.id.containerLogin,
                            LoginCommonFragment())
                    ?.addToBackStack(Constants.LOGINCOMMONFRAGMENT)
                    ?.commit()

            R.id.registro -> {
                startActivity(Intent(activity, RegistroActivity::class.java))
                activity?.finish()
            }
        }
    }
}