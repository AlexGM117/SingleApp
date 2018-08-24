package com.softhink.single.registro.view;

import android.app.DatePickerDialog;
import android.content.Context;
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
import com.softhink.single.R;
import com.softhink.single.registro.presenter.RegistroContract;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegDataFragment extends BaseFragment implements
        View.OnClickListener,
        RadioGroup.OnCheckedChangeListener {

    private ImageView btnNext;
    private EditText inputName;
    private EditText txtDay;
    private EditText txtMonth;
    private EditText txtYear;
    private RadioGroup radioGender;
    private Date userBirthday;
    private String gender;
    private RegistroContract.DataContract.CallbackData callback;

    public RegDataFragment() {
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
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (RegistroContract.DataContract.CallbackData) context;
        } catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + " must implements interface");
        }
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
                callback.dataForm(inputName.getText().toString(), userBirthday, gender);
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
        Integer[] date = getDateForPicker(txtYear.getText().toString(),
                txtMonth.getText().toString(), txtDay.getText().toString());

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                userBirthday = getUserBirthDay(year, month, dayOfMonth);

                txtDay.setText((dayOfMonth < 10)?getString(R.string.date_mask, dayOfMonth):
                        String.valueOf(dayOfMonth));
                txtMonth.setText((month < 9)?getString(R.string.date_mask, month+1):String.valueOf(month));
                txtYear.setText(String.valueOf(year));
            }
        }, date[0], date[1], date[2]);

        datePickerDialog.show();
    }

    private Integer[] getDateForPicker(String s1, String s2, String s3) {
        if (s1.isEmpty() && s2.isEmpty() && s3.isEmpty()){
            return new Integer[]{Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)};
        } else {
            return new Integer[]{Integer.valueOf(s1), Integer.valueOf(s2) -1, Integer.valueOf(s3)};
        }
    }

    private Date getUserBirthDay(int year, int month, int dayOfMonth) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd", new Locale("es"))
                    .parse(""+year+"-"+(month+1)+"-"+dayOfMonth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
