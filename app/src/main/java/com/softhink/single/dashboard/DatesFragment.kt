package com.softhink.single.dashboard


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.softhink.single.BaseFragment

import com.softhink.single.R
import com.softhink.single.dashboard.adapters.DatesAdapter
import kotlinx.android.synthetic.main.fragment_dates.*


/**
 * A simple [Fragment] subclass.
 */
class DatesFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        datesRecycler.setHasFixedSize(true)
        datesRecycler.layoutManager = LinearLayoutManager(activity)
        val dates = ArrayList<String>()
        dates.add("Cita con Tania Ramírez")
        dates.add("Cita con Daniel Díaz")
        dates.add("Cita con Diana Mendoza")

        datesRecycler.adapter = DatesAdapter(dates)
    }
}
