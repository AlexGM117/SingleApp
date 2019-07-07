package com.softhink.single.ui.dashboard.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.softhink.single.R
import com.softhink.single.data.remote.response.UserResponse
import com.softhink.single.ui.common.GlideApp

class SingleHeaderAdapter(private val singles: List<UserResponse>) : RecyclerView.Adapter<SingleHeaderAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_header, parent, false))
    }

    override fun getItemCount() = singles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        GlideApp.with(context)
                .load(R.drawable.ic_avatar_big)
                .into(holder.avatar)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar = itemView.findViewById<ImageView>(R.id.single_mini)
    }
}