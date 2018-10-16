package com.softhink.single.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.softhink.single.R

import java.util.ArrayList

class PlacesFragment : Fragment(), PlacesAdapter.ClickItem {

    private var recyclerPlaces: RecyclerView? = null
    private var adapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_places, container, false)
    }

    override fun onViewCreated(@NonNull view: View?, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            recyclerPlaces = view!!.findViewById(R.id.recyclerPlaces)
            recyclerPlaces!!.setHasFixedSize(true)

            layoutManager = LinearLayoutManager(activity)
            recyclerPlaces!!.layoutManager = layoutManager

            val integers = ArrayList<Int>()
            integers.add(1)
            integers.add(2)
            integers.add(3)
            integers.add(4)
            integers.add(5)

            recyclerPlaces!!.adapter = PlacesAdapter(integers, this)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onClickItem() {
        startActivity(Intent(activity, PlaceDetailActivity::class.java))
    }
}// Required empty public constructor
