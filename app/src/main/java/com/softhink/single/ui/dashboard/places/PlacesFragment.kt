package com.softhink.single.ui.dashboard.places

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.softhink.single.ui.common.CustomLinearLayoutManager

import com.softhink.single.R
import com.softhink.single.ui.dashboard.adapters.PlacesAdapter
import kotlinx.android.synthetic.main.fragment_places.*

import java.util.ArrayList

class PlacesFragment : Fragment(), PlacesAdapter.ClickItem {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_places, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            val layoutManager = CustomLinearLayoutManager(activity, LinearLayout.VERTICAL, false)
            layoutManager.setScrollEnabled(false)
            recyclerPlaces.setHasFixedSize(true)
            recyclerPlaces.layoutManager = layoutManager

            val integers = ArrayList<Int>()
            integers.add(1)
            integers.add(2)
            integers.add(3)
            integers.add(4)
            integers.add(5)

            recyclerPlaces.adapter = PlacesAdapter(integers, this)
        }
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onClickItem() {
        startActivity(Intent(activity, PlaceDetailActivity::class.java))
    }
}
