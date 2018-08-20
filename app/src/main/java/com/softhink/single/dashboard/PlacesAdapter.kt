package com.softhink.single.dashboard

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.softhink.single.R

class PlacesAdapter(private val placeList: ArrayList<Int>, private val listener: PlacesAdapter.ClickItem) :
        RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_place, parent, false)

        return PlaceViewHolder(view)
    }

    override fun getItemCount() = placeList.size

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        if(position %2 == 0)
            holder.image?.setImageResource(R.drawable.restaurante)
        else
            holder.image?.setImageResource(R.drawable.restaurante_1)

        holder.card?.setOnClickListener {
            listener.onClickItem()
        }
    }


    class PlaceViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val card = itemView?.findViewById<CardView>(R.id.cardPlace)
        val image = itemView?.findViewById<ImageView>(R.id.placeImage)
        val name = itemView?.findViewById<TextView>(R.id.placeName)
        val locattion = itemView?.findViewById<TextView>(R.id.placeLocation)
        val description = itemView?.findViewById<TextView>(R.id.placeDescription)
        val score = itemView?.findViewById<RatingBar>(R.id.placeScore)
    }

    interface ClickItem{
        fun onClickItem()
    }
}