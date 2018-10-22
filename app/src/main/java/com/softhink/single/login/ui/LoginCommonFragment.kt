package com.softhink.single.login.ui

import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.google.android.material.textfield.TextInputLayout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.softhink.single.BaseFragment
import com.softhink.single.Constants
import com.softhink.single.R
import com.softhink.single.dashboard.MainContainer
import com.softhink.single.login.LoginViewModel
import com.softhink.single.registro.Status.*
import kotlinx.android.synthetic.main.fragment_login_common.*

/**
 * A simple [Fragment] subclass.
 *
 */
class LoginCommonFragment : BaseFragment(), View.OnClickListener {

    private lateinit var txtUser: TextInputLayout
    private lateinit var txtPss: TextInputLayout

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
            txtUser = view.findViewById(R.id.loginUser)
            txtPss = view.findViewById(R.id.loginPss)

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
                doLogin(txtUser.editText?.text?.toString()!!,
                        txtPss.editText?.text?.toString()!!)
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
                SUCCESS -> loginSuccess()
                ERROR -> loginFail(it.data)
                FAILED -> loginFail(it.data)
            }
        })
    }
    fun emailEmpty() {
        showMessageDialog("Usuario ó email vacio")
    }

    fun passEmpty() {
        showMessageDialog("Contraseña vacia")
    }

    fun loginSuccess() {
        val intent = Intent(activity, MainContainer::class.java)
        startActivity(intent)
        activity?.finish()
    }

    fun loginFail(message: String) {
        showMessageDialog(message)
    }
}
