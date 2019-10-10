package com.softhink.single.ui.login.view

import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.softhink.single.ui.base.BaseFragment
import com.softhink.single.util.Constants
import com.softhink.single.R
import com.softhink.single.data.manager.SinglePreferences
import com.softhink.single.data.remote.response.UserResponse
import com.softhink.single.ui.dashboard.MainContainer
import com.softhink.single.ui.login.LoginViewModel
import com.softhink.single.ui.registro.Status.*
import kotlinx.android.synthetic.main.fragment_login_common.*
import kotlinx.android.synthetic.main.loading.*

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
        loadingScreen.visibility = View.VISIBLE
        mViewModel.login(toString, toString1).observe(this, Observer {
            if (it != null) {
                loadingScreen.visibility = View.GONE
                when (it.status) {
                    SUCCESS -> loginSuccess(it.data!!)
                    ERROR -> showMessageDialog(it.message!!)
                    FAILED -> showMessageDialog(it.message!!)
                }

                btnContinuar.isEnabled = true
            }
        })
    }

    private fun loginSuccess(data: UserResponse) {
        mViewModel.saveLocalData(data)
        SinglePreferences().accessToken = data.username
        startActivity(Intent(activity, MainContainer::class.java))
        activity?.finish()
    }
}
