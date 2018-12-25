package com.softhink.single.ui.login.ui

import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.softhink.single.base.BaseFragment
import com.softhink.single.Constants
import com.softhink.single.R
import com.softhink.single.SinglePreferences
import com.softhink.single.models.response.UserResponse
import com.softhink.single.ui.dashboard.MainContainer
import com.softhink.single.ui.login.LoginViewModel
import com.softhink.single.ui.registro.Status.*
import kotlinx.android.synthetic.main.fragment_login_common.*

/**
 * A simple [Fragment] subclass.
 *
 */
class LoginCommonFragment : BaseFragment(), View.OnClickListener {

    private val mViewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_common, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            arrowBack.setOnClickListener(this)
            btnContinuar.setOnClickListener(this)
            forgotPss.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){

            R.id.arrowBack -> {
                fragmentManager?.popBackStack()
            }

            R.id.btnContinuar -> {
                if(isConnected()) {
                    btnContinuar.isEnabled = false
                    doLogin(loginUser.editText?.text?.toString()!!,
                            loginPss.editText?.text?.toString()!!)
                } else {
                    showMessageDialog("Sin conexión a Internet")
                }
            }

            R.id.forgotPss -> {
                val fragmentManager = fragmentManager
                fragmentManager?.beginTransaction()
                        ?.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up,
                                R.anim.slide_in_down, R.anim.slide_out_down)
                        ?.replace(R.id.containerLogin,
                                PassRecoveryFragment())
                        ?.addToBackStack(Constants.PASSRECOVERYFRAGMENT)?.commit()
            }
        }
    }

    private fun doLogin(toString: String, toString1: String) {
        mViewModel.login(toString, toString1).observe(this, Observer {
            when (it.status){
                SUCCESS -> loginSuccess(it.data)
                ERROR -> loginFail(it.message!!)
                FAILED -> loginFail(it.message!!)
            }
        })
    }

    private fun loginSuccess(data: UserResponse?) {
        Log.i(LoginCommonFragment::class.java.simpleName, data.toString())
        SinglePreferences().setAccessToken("token login")
        startActivity(Intent(activity, MainContainer::class.java))
        activity?.finish()
    }

    private fun loginFail(message: String) {
        showMessageDialog(message)
        btnContinuar.isEnabled = true
    }
}
