package com.softhink.single.dashboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.softhink.single.Constants;
import com.softhink.single.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreferencesFragment extends Fragment implements View.OnClickListener, OnRangeSeekbarChangeListener {

    private CrystalRangeSeekbar rangeSeekbar;
    private TextView textMin;
    private ImageButton btnNext;
//    private IndicatorSeekBar seekBarMin;
//    private IndicatorSeekBar seekBarMax;


    public PreferencesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preferences, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnNext = view.findViewById(R.id.buttonNext);
        textMin = view.findViewById(R.id.textMin);
//        textMax = view.findViewById(R.id.textMax);
        rangeSeekbar = view.findViewById(R.id.rangeSeekbar1);
//        rangeSeekbar.setOnRangeSeekbarChangeListener(this);
        btnNext.setOnClickListener(this);

//        seekBarMin = view.findViewById(R.id.seekBarMin);
//        seekBarMax = view.findViewById(R.id.seekBarMax);
//        seekBarMin.setOnSeekChangeListener(this);
        rangeSeekbar.setOnRangeSeekbarChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonNext:
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
//                        .replace(R.id.containerPreferences, new PreferencesLastFragment())
                        .replace(R.id.containerPreferences, new InterestsFragment())
                        .addToBackStack(Constants.PREFERENCESFRAGMENT)
                        .commit();
                break;
        }
    }

    @Override
    public void valueChanged(Number minValue, Number maxValue) {
        rangeSeekbar.setGap(0f);
        textMin.setText(String.valueOf(minValue + " - " + maxValue));
    }
}
