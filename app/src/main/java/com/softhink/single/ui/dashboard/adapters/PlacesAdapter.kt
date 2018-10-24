package com.softhink.single.ui.dashboard.adapters

import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.softhink.single.R
import java.util.*

class PlacesAdapter(private val placeList: ArrayList<Int>, private val listener: ClickItem) :
        RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_place, parent, false)

        return PlaceViewHolder(view)
    }

    override fun getItemCount() = placeList.size

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        if(position %2 == 0) {
            holder.image?.setImageResource(R.drawable.restaurante)
            holder.score?.rating = (0..5).random().toFloat()
        } else {
            holder.image?.setImageResource(R.drawable.restaurante_1)
        }

        holder.card?.setOnClickListener {
            listener.onClickItem()
        }
    }

    fun ClosedRange<Int>.random() =
            Random().nextInt((endInclusive + 1) - start) +  start


    class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card = itemView.findViewById<CardView>(R.id.cardPlace)
        val image = itemView.findViewById<ImageView>(R.id.placeImage)
        val name = itemView.findViewById<TextView>(R.id.placeName)
        val locattion = itemView.findViewById<TextView>(R.id.placeLocation)
        val description = itemView.findViewById<TextView>(R.id.placeDescription)
        val score = itemView.findViewById<RatingBar>(R.id.placeScore)
    }

    interface ClickItem{
        fun onClickItem()
    }
}