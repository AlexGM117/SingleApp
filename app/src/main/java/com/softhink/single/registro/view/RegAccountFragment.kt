package com.softhink.single.registro.view


import android.content.Context
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.google.android.material.textfield.TextInputLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.softhink.single.R
import com.softhink.single.registro.presenter.RegistroContract

/**
 * A simple [Fragment] subclass.
 */
class RegAccountFragment : Fragment(), View.OnClickListener {

    private var btnNext: ImageView? = null
    private var btnBack: ImageView? = null
    private var inputEmail: TextInputLayout? = null
    private var inputPss: TextInputLayout? = null
    private var inputPss2: TextInputLayout? = null
    private var callback: RegistroContract.AccountContract.CallbackAccount? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_registro_dos, container, false)
    }

    override fun onViewCreated(@NonNull view: View?, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            inputEmail = view!!.findViewById(R.id.inputEmail)
            inputPss = view.findViewById(R.id.inputPss)
            inputPss2 = view.findViewById(R.id.inputPss2)
            btnNext = view.findViewById(R.id.btnLastPage)
            btnBack = view.findViewById(R.id.btnFirstPage)

            btnNext!!.setOnClickListener(this)
            btnBack!!.setOnClickListener(this)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            callback = context as RegistroContract.AccountContract.CallbackAccount?
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implements interface")
        }

    }

    override fun onClick(v: View) {
        val fragmentManager = fragmentManager
        when (v.id) {
            R.id.btnLastPage -> callback!!.accountData(inputEmail!!.editText.getText().toString(),
                    inputPss!!.editText.getText().toString(),
                    inputPss2!!.editText.getText().toString())

            R.id.btnFirstPage -> fragmentManager.popBackStack()
        }
    }
}// Required empty public constructor
