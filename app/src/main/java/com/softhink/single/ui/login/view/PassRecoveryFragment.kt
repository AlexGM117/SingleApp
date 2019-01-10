package com.softhink.single.ui.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softhink.single.util.Constants
import com.softhink.single.R
import kotlinx.android.synthetic.main.fragment_pass_forgot.*

/**
 * A simple [Fragment] subclass.
 *
 */
class PassRecoveryFragment : Fragment(), View.OnClickListener{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pass_forgot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrowBack.setOnClickListener(this)
        btnToTmp.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnToTmp -> {
                val fragmentManager = fragmentManager
                fragmentManager?.beginTransaction()?.replace(R.id.containerLogin,
                        PassTmpFragment())?.addToBackStack(Constants.PASSTMPFRAGMENT)?.commit()
            }

            R.id.arrowBack -> {
                fragmentManager?.popBackStack()
            }
        }
    }
}
