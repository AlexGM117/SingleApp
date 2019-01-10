package com.softhink.single.ui.login.view


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softhink.single.R
import com.softhink.single.ui.dashboard.MainContainer
import kotlinx.android.synthetic.main.fragment_pass_tmp.*

/**
 * A simple [Fragment] subclass.
 *
 */
class PassTmpFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pass_tmp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        buttonNxt.setOnClickListener {
            activity?.finish()
            val intent = Intent(activity, MainContainer::class.java)
            startActivity(intent)
        }
    }
}
