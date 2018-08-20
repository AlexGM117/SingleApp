package com.softhink.single.dashboard;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softhink.single.R;

import java.util.ArrayList;

public class PlacesFragment extends Fragment implements PlacesAdapter.ClickItem {

    private RecyclerView recyclerPlaces;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public PlacesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_places, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null) {
            recyclerPlaces = view.findViewById(R.id.recyclerPlaces);
            recyclerPlaces.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(getActivity());
            recyclerPlaces.setLayoutManager(layoutManager);

            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(1);
            integers.add(2);
            integers.add(3);
            integers.add(4);
            integers.add(5);

            recyclerPlaces.setAdapter(adapter = new PlacesAdapter(integers, this));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClickItem() {
        startActivity(new Intent(getActivity(), PlaceDetailActivity.class));
    }
}
