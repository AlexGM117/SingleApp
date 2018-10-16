package com.softhink.single.registro.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull
import android.view.View
import com.softhink.single.BaseActivity
import com.softhink.single.Constants
import com.softhink.single.DialogCallBack
import com.softhink.single.R
import com.softhink.single.login.ui.LoginActivity
import com.softhink.single.registro.presenter.RegistroContract
import com.softhink.single.registro.presenter.RegistroPresenter
import com.softhink.single.survey.SurveyActivity
import java.util.Date

class RegistroActivity : BaseActivity(), View.OnClickListener, RegistroContract, RegistroContract.DataContract, RegistroContract.AccountContract, RegistroContract.DataContract.CallbackData, RegistroContract.AccountContract.CallbackAccount, RegistroContract.PhotoProfileContract, RegistroContract.PhotoProfileContract.CallbackPhoto {

    private var presenter: RegistroPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        if (savedInstanceState == null) {
            presenter = RegistroPresenter(this, this, this)
            val context = this
            setUpToolbar("Registro", this)
            mFragmentManager = supportFragmentManager
            mFragmentManager.beginTransaction()
                    .add(R.id.registroContainer, RegDataFragment(),
                            Constants.REGISTROUNOFRAGMENT)
                    .commit()
        }
    }

    override fun onClick(view: View) {
        if (mFragmentManager.backStackEntryCount > 0) {
            mFragmentManager.popBackStack()
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun succesToSurvey(@NonNull responseMessage: String) {
        val intent = Intent(this, SurveyActivity::class.java)
        showMessageDialog(responseMessage, object : DialogCallBack {
            override fun onAccept() {
                startActivity(intent)
                finish()
            }

            override fun onCancel() {

            }
        })
    }

    override fun errorMessage(@NonNull message: String) {
        showMessageDialog(message)
    }

    override fun serviceUnavailable() {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun dataForm(name: String?, date: Date?, gender: String?) {
        presenter!!.validateForm(name!!, date, gender)
    }

    override fun accountData(email: String?, pss: String?, pss1: String?) {
        presenter!!.validAccountData(email, pss, pss1)
    }

    override fun nameIsEmpty() {
        showMessageDialog(getString(R.string.empty_name))
    }

    override fun nameStringLenght() {
        showMessageDialog("Desbes agregar un nombre válido")
    }

    override fun dateEmpty() {
        showMessageDialog("Agrega tu fecha de nacimiento")
    }

    override fun isUnderAge() {
        showMessageDialog("Debes ser mayor de edad para poder utilizar la aplicacion")
    }

    override fun genderUnselected() {
        showMessageDialog("Selecciona H ó M")
    }

    override fun toAccountFragment() {
        mFragmentManager.beginTransaction()
                .add(R.id.registroContainer,
                        RegAccountFragment(), RegAccountFragment::class.java.simpleName)
                .addToBackStack(RegAccountFragment::class.java.simpleName)
                .commit()
    }

    override fun emailEmpty() {
        showMessageDialog("Agrega una direccion de correo electronico")
    }

    override fun invalidEmail() {
        showMessageDialog("Agrega una direccion de correo electronico valida")
    }

    override fun passEmpty() {
        showMessageDialog("Ingresa una contraseña y confirmala")
    }

    override fun passLenght() {
        showMessageDialog("Tú contraseña debe contener minimo 8 caracteres")
    }

    override fun passNotMatch() {
        showMessageDialog("Verifica la contraseña que ingresaste")
    }

    override fun finishRegistro() {
        mFragmentManager.beginTransaction()
                .add(R.id.registroContainer,
                        RegistroTresFragment(), RegistroTresFragment::class.java.simpleName)
                .addToBackStack(RegistroTresFragment::class.java.simpleName)
                .commit()
    }

    override fun callService(string: String?) {
        presenter!!.sendRegistro(string)
    }
}