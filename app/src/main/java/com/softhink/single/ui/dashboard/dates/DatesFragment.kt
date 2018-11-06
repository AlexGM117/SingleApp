package com.softhink.single.ui.dashboard.dates


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.softhink.single.CustomLinearLayoutManager
import com.softhink.single.base.BaseFragment
import com.softhink.single.R
import com.softhink.single.ui.dashboard.adapters.DatesAdapter
import kotlinx.android.synthetic.main.fragment_dates.*


/**
 * A simple [Fragment] subclass.
 */
class DatesFragment : BaseFragment(), DatesAdapter.RecyclerClickListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null){
            val layoutManager = CustomLinearLayoutManager(activity, LinearLayout.VERTICAL, false)
            layoutManager.setScrollEnabled(false)

            datesRecycler.setHasFixedSize(true)
            datesRecycler.layoutManager = layoutManager
            val dates = ArrayList<String>()
            dates.add("Cita con Tania Ramírez")
            dates.add("Cita con Daniel Díaz")
            dates.add("Cita con Diana Mendoza")

            datesRecycler.adapter = DatesAdapter(dates, this)

            actionButton.addActionItem(SpeedDialActionItem
                    .Builder(R.id.action_all, R.drawable.ic_action_all)
                    .setFabBackgroundColor(ResourcesCompat.getColor(resources, R.color.colorAccent, null))
                    .setLabel("Todos")
                    .create())

            actionButton.addActionItem(SpeedDialActionItem
                    .Builder(R.id.action_fav, R.drawable.ic_action_fav)
                    .setFabBackgroundColor(ResourcesCompat.getColor(resources, R.color.colorAccent, null))
                    .setLabel("Favoritos")
                    .create())
        }
    }

    override fun onClick() {
        startActivity(Intent(activity, SingleProfileActivity::class.java))
    }
}
