package com.softhink.single.registro.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.softhink.single.BaseFragment;
import com.softhink.single.Constants;
import com.softhink.single.R;
import com.softhink.single.registro.presenter.RegDataContract;
import com.softhink.single.registro.presenter.RegDataPresenter;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroFormFragment extends BaseFragment implements
        View.OnClickListener,
        RadioGroup.OnCheckedChangeListener,
        RegDataContract {

    private ImageView btnNext;
    private EditText inputName;
    private EditText txtDay;
    private EditText txtMonth;
    private EditText txtYear;
    private RadioGroup radioGender;

    private RegDataPresenter presenter;
    private Date userBirthday;
    private String gender;

    public RegistroFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro_uno, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new RegDataPresenter(this);

        if (savedInstanceState == null) {
            inputName = view.findViewById(R.id.inputName);
            txtDay = view.findViewById(R.id.pickerDay);
            txtMonth = view.findViewById(R.id.pickerMonth);
            txtYear = view.findViewById(R.id.pickerYear);
            radioGender = view.findViewById(R.id.radioGender);
            radioGender.setOnCheckedChangeListener(this);
            btnNext = view.findViewById(R.id.btnNextPage);
        }

        btnNext.setOnClickListener(this);
        txtDay.setOnClickListener(this);
        txtMonth.setOnClickListener(this);
        txtYear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pickerDay:
                showDatePicker();
                break;
            case R.id.pickerMonth:
                showDatePicker();
                break;
            case R.id.pickerYear:
                showDatePicker();
                break;

            case R.id.btnNextPage:
                presenter.validateForm(inputName.getText().toString(), userBirthday, "");
                break;
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioFemale:
                gender = "F";
                break;

            case R.id.radioMale:
                gender = "M";
                break;
        }
    }

    private void showDatePicker() {
        Integer[] date = presenter.getDateForPicker(txtYear.getText().toString(),
                txtMonth.getText().toString(), txtDay.getText().toString());

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                userBirthday = presenter.getUserBirthDay(year, month, dayOfMonth);

                txtDay.setText((dayOfMonth < 10)?getString(R.string.date_mask, dayOfMonth):
                        String.valueOf(dayOfMonth));
                txtMonth.setText((month < 9)?getString(R.string.date_mask, month+1):String.valueOf(month));
                txtYear.setText(String.valueOf(year));
            }
        }, date[0], date[1], date[2]);

        datePickerDialog.show();
    }

    @Override
    public void nameIsEmpty() {
        showMessageDialog(getString(R.string.empty_name));
    }

    @Override
    public void nameStringLenght() {
        showMessageDialog("Desbes agregar un nombre válido");
    }

    @Override
    public void dateEmpty() {
        showMessageDialog("Agrega tu fecha de nacimiento");
    }

    @Override
    public void isUnderAge() {
        showMessageDialog("Debes ser mayor de edad para poder utilizar la aplicacion");
    }

    @Override
    public void genderUnselected() {
        showMessageDialog("Selecciona H ó M");
    }

    @Override
    public void serviceUnavailable() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void toNextFragment() {
        getFragmentManager().beginTransaction().replace(R.id.registroContainer,
                new RegistroDosFragment(),
                Constants.REGISTRODOSFRAGMENT)
                .addToBackStack(Constants.REGISTRODOSFRAGMENT)
                .commit();
    }
}
