package com.softhink.single.login


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softhink.single.BaseFragment
import com.softhink.single.Constants
import com.softhink.single.R
import com.softhink.single.dashboard.MainContainer
import kotlinx.android.synthetic.main.fragment_login_common.*

/**
 * A simple [Fragment] subclass.
 *
 */
class LoginCommonFragment : BaseFragment(), LoginCommonView,
        View.OnClickListener {

    private lateinit var presenter: LoginCommonPresenter
    private lateinit var txtUser: TextInputLayout
    private lateinit var txtPss: TextInputLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login_common, container, false)
        presenter = LoginCommonPresenter(this)

        return view
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
                presenter.login(txtUser.editText?.text?.toString()!!,
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

    override fun emailEmpty() {
        showMessageDialog("Usuario ó email vacio")
    }

    override fun passEmpty() {
        showMessageDialog("Contraseña vacia")
    }

    override fun loginSuccess() {
        val intent = Intent(activity, MainContainer::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun loginFail() {

    }

    override fun serviceUnavailable() {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }
}
