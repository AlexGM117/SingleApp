package com.softhink.single.ui.login.view

import android.content.Intent
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.facebook.AccessToken
import com.softhink.single.*
import com.softhink.single.data.manager.SinglePreferences
import com.softhink.single.ui.base.BaseFragment
import com.softhink.single.ui.login.LoginFbViewModel
import com.softhink.single.ui.registro.Status.*
import com.softhink.single.ui.registro.view.SignUpActivity
import com.softhink.single.ui.survey.view.SurveyActivity
import com.softhink.single.util.Constants
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : BaseFragment(), View.OnClickListener {

    private val surveyFlag = "SURVEY_DESTINATION"
    private val mViewModel: LoginFbViewModel by lazy {
        ViewModelProviders.of(this).get(LoginFbViewModel::class.java)
    }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mViewModel.onActivityResult(requestCode, resultCode, data)
    }

    override fun onClick(v: View) {
        val fragmentManager = fragmentManager
        when (v.id) {
            R.id.btnLoginFB -> {
                facebookCallback()
            }

            R.id.btnDoLogin -> {
                fragmentManager?.beginTransaction()
                        ?.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                                R.anim.enter_from_left, R.anim.exit_to_right)
                        ?.replace(R.id.containerLogin,
                                LoginCommonFragment())
                        ?.addToBackStack(Constants.LOGINCOMMONFRAGMENT)
                        ?.commit()
            }

            R.id.registro -> {
                startActivity(Intent(activity, SignUpActivity::class.java))
                activity?.finish()
            }
        }
    }

    private fun facebookCallback() {
        mViewModel.fbLogin(this).observe(this, Observer {
            if (it != null) {
                when (it.status) {
                    SUCCESS -> {
//                        SinglePreferences().setAccessToken(AccessToken.getCurrentAccessToken().token)
                        showMessageDialog(it.message!!, positiveClick = {
                            startActivity(Intent(activity, SurveyActivity::class.java)
                                    .putExtra("USERNAME", it.data?.username)
                                    .putExtra(surveyFlag, true))
                            activity?.finish()
                        })
                    }
                    ERROR -> {
                        showMessageDialog(it.message!!)
                    }
                    FAILED -> {
                        showMessageDialog(it.message!!)
                    }
                }
            }
        })
    }
}