package com.softhink.single.survey;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.igalata.bubblepicker.BubblePickerListener;
import com.igalata.bubblepicker.adapter.BubblePickerAdapter;
import com.igalata.bubblepicker.model.PickerItem;
import com.igalata.bubblepicker.rendering.BubblePicker;
import com.softhink.single.R;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class InterestsFragment extends Fragment
        implements BubblePickerListener, View.OnClickListener {

    private BubblePicker picker;
    private ImageButton btnNext;
    private ImageButton btnPrev;

    public InterestsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_interests, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        picker = view.findViewById(R.id.picker);

        picker.setAdapter(new BubblePickerAdapter() {
            String[] titles = new String[]{
                    "Encontrar pareja",
                    "Diversión",
                    "Sexo casual",
                    "Conocer gente nueva"
            };

            @Override
            public int getTotalCount() {
                return titles.length;
            }

            @NotNull
            @Override
            public PickerItem getItem(int i) {
                PickerItem item = new PickerItem();
                item.setTitle(titles[i]);
                item.setColor(Color.parseColor("#DA7C86"));
                item.setTextColor(Color.WHITE);
                item.setBackgroundImage(ContextCompat.getDrawable(getContext(), R.drawable.background_bubble));
                return item;
            }
        });

        picker.setCenterImmediately(true);
        picker.setBubbleSize(30);
        picker.setListener(this);

        btnNext = view.findViewById(R.id.btnLastPage);
        btnPrev = view.findViewById(R.id.btnFirstPage);

        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        picker.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        picker.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLastPage:
                getFragmentManager().beginTransaction().replace(R.id.containerPreferences,
                        new TastesFragment(),
                        TastesFragment.class.getSimpleName())
                        .addToBackStack(TastesFragment.class.getSimpleName())
                        .commit();
                break;

            case R.id.btnFirstPage:
                getFragmentManager().popBackStack();
                break;
        }
    }

    @Override
    public void onBubbleSelected(PickerItem pickerItem) {
        Log.i("SingleApp", "Burbuja seleccionada: " + pickerItem.getTitle());
    }

    @Override
    public void onBubbleDeselected(PickerItem pickerItem) {
        Log.i("SingleApp", "Burbuja desseleccionada: " + pickerItem.getTitle());
    }
}
