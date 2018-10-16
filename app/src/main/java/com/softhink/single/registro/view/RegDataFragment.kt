package com.softhink.single.registro.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import com.softhink.single.BaseFragment
import com.softhink.single.R
import com.softhink.single.registro.presenter.RegistroContract
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * A simple [Fragment] subclass.
 */
class RegDataFragment : BaseFragment(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private var btnNext: ImageView? = null
    private var inputName: EditText? = null
    private var txtDay: EditText? = null
    private var txtMonth: EditText? = null
    private var txtYear: EditText? = null
    private var radioGender: RadioGroup? = null
    private var userBirthday: Date? = null
    private var gender: String? = null
    private var callback: RegistroContract.DataContract.CallbackData? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_registro_uno, container, false)
    }

    override fun onViewCreated(@NonNull view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            inputName = view.findViewById(R.id.inputName)
            txtDay = view.findViewById(R.id.pickerDay)
            txtMonth = view.findViewById(R.id.pickerMonth)
            txtYear = view.findViewById(R.id.pickerYear)
            radioGender = view.findViewById(R.id.radioGender)
            radioGender!!.setOnCheckedChangeListener(this)
            btnNext = view.findViewById(R.id.btnNextPage)

            btnNext!!.setOnClickListener(this)
            txtDay!!.setOnClickListener(this)
            txtMonth!!.setOnClickListener(this)
            txtYear!!.setOnClickListener(this)
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
        val inflater = activity.getLayoutInflater()

        val view = inflater.inflate(R.layout.picker_layout, null)
        val datePicker = view.findViewById<DatePicker>(R.id.pickerDate)

        val builder = AlertDialog.Builder(activity)
        builder.setView(view)
        builder.setPositiveButton("ACEPTAR") { dialogInterface, i ->
            userBirthday = getUserBirthDay(datePicker.year,
                    datePicker.month, datePicker.dayOfMonth)

            txtDay!!.setText(datePicker.dayOfMonth.toString())
            txtMonth!!.setText((datePicker.month + 1).toString())
            txtYear!!.setText(datePicker.year.toString())
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
