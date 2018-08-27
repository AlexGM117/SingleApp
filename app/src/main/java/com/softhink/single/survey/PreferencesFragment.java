package com.softhink.single.survey;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.appyvet.materialrangebar.RangeBar;
import com.softhink.single.BaseFragment;
import com.softhink.single.Constants;
import com.softhink.single.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreferencesFragment extends BaseFragment
        implements View.OnClickListener,
        RangeBar.OnRangeBarChangeListener {

    private ImageButton btnNext;
    private RangeBar rangeBar;


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

        if (savedInstanceState == null) {
            rangeBar = view.findViewById(R.id.ageRangebar);
            btnNext = view.findViewById(R.id.btnNextPage);

            rangeBar.setRangePinsByValue(18F, 28F);
            rangeBar.setOnRangeBarChangeListener(this);
            btnNext.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNextPage:
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.containerPreferences, new InterestsFragment())
                        .addToBackStack(Constants.INSTANCE.getPREFERENCESFRAGMENT())
                        .commit();
                break;
        }
    }

    @Override
    public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {

    }
}
