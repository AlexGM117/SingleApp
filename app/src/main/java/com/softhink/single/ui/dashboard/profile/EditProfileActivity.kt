package com.softhink.single.ui.dashboard.profile

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.softhink.single.ui.base.BaseActivity
import com.softhink.single.R

class EditProfileActivity : BaseActivity() {

    private val mViewModel: ProfileViewModel by lazy {
        ViewModelProviders.of(this).get(ProfileViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        if (savedInstanceState == null) {
            setUpToolbar("Editar Perfil", true)
            mViewModel.getMyProfile()

            mFragmentManager.beginTransaction()
                    .add(R.id.containerEdit, EditProfileFragment())
                    .commit()
        }
    }
}
