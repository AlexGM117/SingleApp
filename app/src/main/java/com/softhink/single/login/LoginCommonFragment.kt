package com.softhink.single.login


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softhink.single.BaseActivity
import com.softhink.single.Constants
import com.softhink.single.R
import com.softhink.single.dashboard.MainContainer
import kotlinx.android.synthetic.main.fragment_login_common.*

/**
 * A simple [Fragment] subclass.
 *
 */
class LoginCommonFragment : Fragment(), LoginCommonView, View.OnClickListener {

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

        (activity as BaseActivity).toolbar.visibility = View.VISIBLE

        txtUser = view.findViewById(R.id.loginUser)
        txtPss = view.findViewById(R.id.loginPss)

        btnContinuar.setOnClickListener(this)
        textView3.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){

            R.id.btnContinuar -> {
                val u = txtUser.editText?.text?.toString()
                val p = txtPss.editText?.text?.toString()

                presenter.login(u, p)

                activity?.finish()
                val intent = Intent(activity, MainContainer::class.java)
                startActivity(intent)
            }

            R.id.textView3 -> {
                val fragmentManager = fragmentManager
                fragmentManager?.beginTransaction()?.replace(R.id.containerLogin,
                        PassRecoveryFragment())?.addToBackStack(Constants.PASSRECOVERYFRAGMENT)?.commit()
            }
        }
    }

    override fun serviceUnavailable() {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }
}
