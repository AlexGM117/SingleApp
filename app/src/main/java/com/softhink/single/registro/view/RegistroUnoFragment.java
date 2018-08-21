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

import com.afollestad.materialdialogs.MaterialDialog;
import com.softhink.single.R;
import com.softhink.single.registro.presenter.RegDataContract;
import com.softhink.single.registro.presenter.RegDataPresenter;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroUnoFragment extends Fragment implements View.OnClickListener, RegDataContract {

    private ImageView btnNext;
    private EditText inputName;
    private EditText txtDay;
    private EditText txtMonth;
    private EditText txtYear;

    private RegDataPresenter presenter;
    private Date userBirthday;
    private String gender;

    public RegistroUnoFragment() {
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
            btnNext = view.findViewById(R.id.buttonNext);
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

            case R.id.buttonNext:
                presenter.validateForm(inputName.getText().toString(), userBirthday, "");
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
        new MaterialDialog(getActivity())
                .title(0,"")
                .message(0, getString(R.string.empty_name)).show();
    }

    @Override
    public void nameStringLenght() {

    }

    @Override
    public void dateEmpty() {

    }

    @Override
    public void isUnderAge() {

    }

    @Override
    public void genderUnselected() {

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

    }
}
