package com.softhink.single.ui.registro.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.softhink.single.base.BaseFragment
import com.softhink.single.DialogCallBack
import com.softhink.single.GlideApp
import com.softhink.single.R
import com.softhink.single.SinglePreferences
import com.softhink.single.ui.registro.SignUpViewModel
import com.softhink.single.ui.survey.SurveyActivity
import kotlinx.android.synthetic.main.arrow_back.*
import kotlinx.android.synthetic.main.fragment_signup_finish.*
import java.io.IOException
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class SignUpFinishFragment : BaseFragment(), View.OnClickListener, BaseFragment.OnOptionsSelected,
        DialogCallBack.Callback {

    private val GALLERY = 0
    private val CAMERA = 1
    private val PERMISSION_REQUEST_READ_STORAGE = 1
    private lateinit var model: SignUpViewModel
    private val surveyFlag = "SURVEY_DESTINATION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = activity?.run {
            ViewModelProviders.of(this).get(SignUpViewModel::class.java)
        }?:throw Exception("Invalid Activity")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup_finish, container, false)
    }

    override fun onViewCreated(@NonNull view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            selectPhoto.setOnClickListener(this)
            btnPrevious.setOnClickListener(this)
            btnSendForm.setOnClickListener(this)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_READ_STORAGE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showImagePickerDialog(this)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            GALLERY -> if (data != null) {
                val contentURI = data.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, contentURI)
                    GlideApp.with(this).load(bitmap).centerCrop().into(imagePreview)
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }

            CAMERA -> if (data != null) {
                val thumbnail = data.extras!!.get("data") as Bitmap
                GlideApp.with(this).load(thumbnail).centerCrop().into(imagePreview)
            }
        }
    }

    override fun onClick(v: View) {
        val fragmentManager = fragmentManager

        when (v.id) {
            R.id.btnPrevious -> fragmentManager?.popBackStack()

            R.id.btnSendForm -> {
//                model.callSignUpService().observe(this, Observer {
//                    when (it.status) {
//                        SUCCESS -> signUpSuccess("")
//                        ERROR -> showMessageDialog("")
//                        FAILED -> showMessageDialog(it.data.toString())
//                    }
//                })
                signUpSuccess("Registro exitoso")
            }

            R.id.selectPhoto -> if (checkPermissions()) {
                showImagePickerDialog(this)
            } else {
                showMessageDialogGalery(this)
            }
        }
    }

    override fun fromGalery() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(galleryIntent, GALLERY)
    }

    override fun fromCamera() {
        val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    override fun onAccept() {
        requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA),
                PERMISSION_REQUEST_READ_STORAGE)
    }

    override fun onCancel() {

    }

    private fun signUpSuccess(message: String){
        val intent = Intent(activity, SurveyActivity::class.java)
        intent.putExtra(surveyFlag, true)
        showMessageDialog(message, object : DialogCallBack.SingleCallback {
            override fun onAccept() {
                SinglePreferences().setAccessToken("token de registro")
                startActivity(intent)
                activity?.finish()
            }
        })
    }
}