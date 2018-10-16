package com.softhink.single.registro.view


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import com.softhink.single.BaseFragment
import com.softhink.single.DialogCallBack
import com.softhink.single.R
import com.softhink.single.registro.presenter.RegistroContract
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 */
class RegistroTresFragment : BaseFragment(), View.OnClickListener, BaseFragment.OnOptionsSelected, DialogCallBack {

    private var btnBack: ImageView? = null
    private var imagePreview: ImageView? = null
    private var btnEnviarRegistro: Button? = null
    private var imageSelector: FrameLayout? = null

    private var callback: RegistroContract.PhotoProfileContract.CallbackPhoto? = null
    private val GALLERY = 0
    private val CAMERA = 1
    private val PERMISSION_REQUEST_READ_STORAGE = 1


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_registro_tres, container, false)
    }

    override fun onViewCreated(@NonNull view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            imageSelector = view.findViewById(R.id.selectPhoto)
            imagePreview = view.findViewById(R.id.imagePreview)
            btnBack = view.findViewById(R.id.btnPrevious)
            btnEnviarRegistro = view.findViewById(R.id.btnSendForm)

            imageSelector!!.setOnClickListener(this)
            btnBack!!.setOnClickListener(this)
            btnEnviarRegistro!!.setOnClickListener(this)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            callback = context as RegistroContract.PhotoProfileContract.CallbackPhoto?
        } catch (e: ClassCastException) {

        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>?, @NonNull grantResults: IntArray?) {
        when (requestCode) {
            PERMISSION_REQUEST_READ_STORAGE -> if (grantResults!!.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showImagePickerDialog(this)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            GALLERY -> if (data != null) {
                val contentURI = data.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), contentURI)
                    imagePreview!!.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }

            CAMERA -> if (data != null) {
                val thumbnail = data.extras!!.get("data") as Bitmap
                imagePreview!!.setImageBitmap(thumbnail)
            }
        }
    }

    override fun onClick(v: View) {
        val fragmentManager = fragmentManager

        when (v.id) {
            R.id.btnPrevious -> fragmentManager.popBackStack()

            R.id.btnSendForm -> callback!!.callService(null)

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
}// Required empty public constructor
