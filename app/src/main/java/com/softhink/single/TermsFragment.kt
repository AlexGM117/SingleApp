package com.softhink.single

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softhink.single.base.BaseActivity
import com.softhink.single.base.BaseFragment
import com.softhink.single.ui.dashboard.HelpActivity
import com.softhink.single.ui.dashboard.MainContainer
import kotlinx.android.synthetic.main.fragment_terms.*

/**
 * A simple [Fragment] subclass.
 *
 */
class TermsFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_terms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbarActivity = activity as BaseActivity
        toolbarActivity.setUpToolbar("Términos y condiciones", this)

        webView.loadUrl("https://developer.android.com/studio/terms")

        btnAccept.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnAccept -> {
                if(activity is HelpActivity){
                    activity?.finish()
                } else {
                    dialogAcceptTerms()
                }
            }
            else ->{
                if (activity is HelpActivity)
                    activity?.finish()
            }
        }
    }

    private fun dialogAcceptTerms() {
        showMessageDialog("Atención", "Estás aceptando los términos y condiciones de Single." +
                "\n\n¿Deseas continuar?", object : DialogCallBack.Callback {
            override fun onAccept() {
                startActivity(Intent(activity, MainContainer::class.java))
                activity?.finish()
            }

            override fun onCancel() {
            }
        })
    }

}
