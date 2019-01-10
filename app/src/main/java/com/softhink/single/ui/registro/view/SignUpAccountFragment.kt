package com.softhink.single.ui.registro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.softhink.single.ui.base.BaseFragment
import com.softhink.single.R
import com.softhink.single.ui.registro.SignUpViewModel
import com.softhink.single.ui.registro.Status.*
import kotlinx.android.synthetic.main.arrow_back_and_next.*
import kotlinx.android.synthetic.main.fragment_signup_account.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class SignUpAccountFragment : BaseFragment(), View.OnClickListener {

    private lateinit var model: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = activity?.run {
            ViewModelProviders.of(this).get(SignUpViewModel::class.java)
        }?:throw Exception("Invalid Activity")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            btnLastPage.setOnClickListener(this)
            btnFirstPage.setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {
        val fragmentManager = fragmentManager
        when (v.id) {
            R.id.btnLastPage -> model.accountData(inputEmail.editText?.text?.trim().toString(),
                    inputPss.editText?.text?.trim().toString(),
                    inputPss2.editText?.text?.trim().toString()).observe(this,
                    Observer {
                        when(it.status){
                            SUCCESS -> finishSignUp()
                            ERROR -> showMessageDialog(it.message!!)
                            FAILED -> TODO()
                        }
                    })
            R.id.btnFirstPage -> fragmentManager?.popBackStack()
        }
    }

    private fun finishSignUp() {
        fragmentManager?.beginTransaction()
                ?.add(R.id.signUpContainer,
                        SignUpFinishFragment(), SignUpFinishFragment::class.java.simpleName)
                ?.addToBackStack(SignUpFinishFragment::class.java.simpleName)
                ?.commit()
    }
}