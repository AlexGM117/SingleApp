package com.softhink.single.login.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout

import com.softhink.single.Constants
import com.softhink.single.R
import com.softhink.single.dashboard.MainContainer
import com.softhink.single.registro.view.RegistroActivity

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment(), View.OnClickListener {

    private var view: View? = null
    private var context: Context? = null
    private var layoutRegistro: LinearLayout? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view = inflater!!.inflate(R.layout.fragment_login, container, false)
        context = activity
        return view
    }

    override fun onViewCreated(@NonNull view: View?, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            val button = view!!.findViewById<Button>(R.id.btnDoLogin)
            val button2 = view.findViewById<Button>(R.id.btnLoginFB)
            layoutRegistro = view.findViewById(R.id.registro)
            layoutRegistro!!.setOnClickListener(this)

            button2.setOnClickListener(this)
            button.setOnClickListener(this)
        }
    }


    override fun onClick(v: View) {
        val fragmentManager = fragmentManager
        when (v.id) {
            R.id.btnLoginFB -> {
                activity.finish()
                startActivity(Intent(context, MainContainer::class.java))
            }

            R.id.btnDoLogin -> fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                            R.anim.enter_from_left, R.anim.exit_to_right)
                    .replace(R.id.containerLogin,
                            LoginCommonFragment())
                    .addToBackStack(Constants.LOGINCOMMONFRAGMENT)
                    .commit()

            R.id.registro -> {
                startActivity(Intent(activity, RegistroActivity::class.java))
                activity.finish()
            }
        }
    }
}// Required empty public constructor
