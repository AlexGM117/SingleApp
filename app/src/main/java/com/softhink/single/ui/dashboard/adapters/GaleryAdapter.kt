package com.softhink.single.ui.dashboard.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.softhink.single.R
import com.softhink.single.ui.common.GlideApp

class GaleryAdapter : RecyclerView.Adapter<GaleryAdapter.GaleryView>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GaleryView {
        context = parent.context
        return GaleryView(LayoutInflater.from(parent.context).inflate(R.layout.galery_item, parent, false))
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: GaleryView, position: Int) {
        GlideApp.with(context)
                .load("https://picsum.photos/1080/1080/?random")
                .placeholder(R.drawable.place_holder)
                .into(holder.item)
    }

    class GaleryView(itemView: View) : RecyclerView.ViewHolder(itemView){
        val item = itemView.findViewById<ImageView>(R.id.galeryItem)
    }
}