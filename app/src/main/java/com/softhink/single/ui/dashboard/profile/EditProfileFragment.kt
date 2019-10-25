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
import com.softhink.single.data.remote.response.UserResponse
import com.softhink.single.ui.registro.Status.*
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_edit_profile.profileName
import kotlinx.android.synthetic.main.loading.*

/**
 * A simple [Fragment] subclass.
 *
 */
class EditProfileFragment : BaseFragment(){

    private val mViewModel: ProfileViewModel by lazy {
        ViewModelProviders.of(this).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(savedInstanceState == null){
            mViewModel.liveDta.observe(this, Observer {
                if (it != null) {
                    when(it.status) {
                        SUCCESS -> successResponse(it.data!!)
                        ERROR -> showMessageDialog(it.message)
                        FAILED -> showMessageDialog(it.message)
                    }
                }
            })
            btnUpdateProfile.setOnClickListener {
                updateProfile()
            }
        }
    }

    private fun updateProfile() {
        loadingScreen.visibility = View.VISIBLE
        mViewModel.updateProfile(profileName.editText?.text.toString(),
                profilePass.editText?.text.toString(),
                profileAboutMe.editText?.text.toString()).observe(this, Observer {
            loadingScreen.visibility = View.GONE
            when(it.status){
                SUCCESS -> updateSucces(it.data!!)
                ERROR -> showMessageDialog(it.message)
                FAILED -> showMessageDialog(it.message)
            }
        })
    }

    private fun successResponse(data: UserProfile) {
        profileName.editText?.setText(data.userName)
        profileAboutMe.editText?.setText(data.description)
    }

    private fun updateSucces(data: UserResponse) {
        mViewModel.updateLocaStore(data, profileAboutMe.editText?.text.toString())
        activity?.finish()
    }
}
