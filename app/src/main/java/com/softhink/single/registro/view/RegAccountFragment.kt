package com.softhink.single.registro.view


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softhink.single.R
import com.softhink.single.registro.presenter.RegistroContract
import kotlinx.android.synthetic.main.arrow_back_and_next.*
import kotlinx.android.synthetic.main.fragment_registro_dos.*

/**
 * A simple [Fragment] subclass.
 */
class RegAccountFragment : Fragment(), View.OnClickListener {

    private var callback: RegistroContract.AccountContract.CallbackAccount? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro_dos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            btnLastPage.setOnClickListener(this)
            btnFirstPage.setOnClickListener(this)
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
            R.id.btnLastPage -> callback?.accountData(inputEmail!!.editText?.text.toString(),
                    inputPss!!.editText?.text.toString(),
                    inputPss2!!.editText?.text.toString())

            R.id.btnFirstPage -> fragmentManager?.popBackStack()
        }
    }
}// Required empty public constructor
