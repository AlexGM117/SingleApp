package com.softhink.single.dashboard.profile

import android.os.Bundle
import com.softhink.single.base.BaseActivity
import com.softhink.single.R

class EditProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        if (savedInstanceState == null) {
            setUpToolbar("Editar Perfil", true)

            mFragmentManager.beginTransaction()
                    .add(R.id.containerEdit, EditProfileFragment())
                    .commit()
        }
    }
}
