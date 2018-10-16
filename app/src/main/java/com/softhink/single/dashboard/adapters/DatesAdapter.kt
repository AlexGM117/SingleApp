package com.softhink.single.dashboard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.softhink.single.R

class DatesAdapter(val dateList: ArrayList<String>) : RecyclerView.Adapter<DatesAdapter.DateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_date, parent,false)

        return DateViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dateList.size
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.dateDesc.text = dateList[position]
    }

    class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateDesc = itemView.findViewById<TextView>(R.id.dateDescription)
    }
}