package com.softhink.single.registro.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.RadioGroup
import com.softhink.single.BaseFragment
import com.softhink.single.R
import com.softhink.single.registro.presenter.RegistroContract
import kotlinx.android.synthetic.main.arrow_next.*
import kotlinx.android.synthetic.main.fragment_registro_uno.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * A simple [Fragment] subclass.
 */
class RegDataFragment : BaseFragment(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {


    private var userBirthday: Date? = null
    private var gender: String? = null
    private var callback: RegistroContract.DataContract.CallbackData? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro_uno, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            btnNextPage.setOnClickListener(this)
            pickerDay.setOnClickListener(this)
            pickerMonth.setOnClickListener(this)
            pickerYear.setOnClickListener(this)
            radioGender.setOnCheckedChangeListener(this)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            callback = context as RegistroContract.DataContract.CallbackData?
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implements interface")
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.pickerDay -> showDatePicker()
            R.id.pickerMonth -> showDatePicker()
            R.id.pickerYear -> showDatePicker()

            R.id.btnNextPage -> callback!!.dataForm(inputName!!.text.toString(), userBirthday, gender)
        }
    }


    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        when (checkedId) {
            R.id.radioFemale -> gender = "F"

            R.id.radioMale -> gender = "M"
        }
    }

    private fun showDatePicker() {
        val inflater = activity?.layoutInflater

        val view = inflater?.inflate(R.layout.picker_layout, null)
        val datePicker = view?.findViewById<DatePicker>(R.id.pickerDate)

        val builder = AlertDialog.Builder(activity)
        builder.setView(view)
        builder.setPositiveButton("ACEPTAR") {
            dialogInterface, i ->
            userBirthday = getUserBirthDay(datePicker?.year!!,
                    datePicker.month, datePicker.dayOfMonth)

            pickerDay.setText(datePicker.dayOfMonth.toString())
            pickerMonth.setText((datePicker.month + 1).toString())
            pickerYear.setText(datePicker.year.toString())
        }
        builder.setNegativeButton("CANCELAR", null)
        builder.create().show()
    }

    private fun getUserBirthDay(year: Int, month: Int, dayOfMonth: Int): Date? {
        try {
            return SimpleDateFormat("yyyy-MM-dd", Locale("es"))
                    .parse("" + year + "-" + (month + 1) + "-" + dayOfMonth)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return null
    }
}// Required empty public constructor
