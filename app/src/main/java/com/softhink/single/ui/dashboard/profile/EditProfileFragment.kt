package com.softhink.single.ui.dashboard.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.softhink.single.ui.base.BaseFragment
import com.softhink.single.R
import com.softhink.single.data.remote.response.UserProfile
import com.softhink.single.ui.registro.Status.*
import kotlinx.android.synthetic.main.activity_single_profile.*
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_edit_profile.profileName
import kotlinx.android.synthetic.main.loading.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 *
 */
class EditProfileFragment : BaseFragment(), View.OnClickListener {

    private lateinit var mViewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mViewModel = activity?.run {
            ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        }?:throw Exception("Invalid Activity")
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(savedInstanceState == null){
            mViewModel.liveDta.observe(this, Observer {
                if (it != null) {
                    when(it.status) {
                        SUCCESS -> successResponse(it.data)
                        ERROR -> showMessageDialog(it.message)
                        FAILED -> showMessageDialog(it.message)
                    }
                }
            })
            btnUpdateProfile.setOnClickListener(this)
        }
    }

    private fun successResponse(data: UserProfile?) {
        profileName.editText?.setText(data?.userName)
        profileAboutMe.editText?.setText(data?.description)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnUpdateProfile -> {
                loadingScreen.visibility = View.VISIBLE
                mViewModel.updateProfile(profileName.editText?.text.toString(),
                    profilePass.editText?.text.toString(),
                    profileAboutMe.editText?.text.toString()).observe(this, Observer {
                    loadingScreen.visibility = View.GONE
                    when(it.status){
                        SUCCESS -> activity?.finish()
                        ERROR -> showMessageDialog(it.message)
                        FAILED -> showMessageDialog(it.message)
                    }
                })
            }
        }
    }
}
