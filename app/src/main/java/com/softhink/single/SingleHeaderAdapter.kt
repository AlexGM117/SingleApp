package com.softhink.single

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.RelativeLayout

class SingleHeaderAdapter(context: Context, private val layoutId: Int,
                          private val list: List<String>) : ArrayAdapter<String>(context, layoutId, list) {
    private var holder: Holder? = null

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): String? {
        return list[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout: RelativeLayout
        val status = getItem(position)
        if (convertView == null) {

            layout = View.inflate(context, layoutId, null) as RelativeLayout

            holder = Holder()
            holder!!.ivStatus = layout.findViewById(R.id.single_mini)
            holder!!.ivStatus!!.setImageResource(R.drawable.ic_avatar_big)
            layout.tag = holder

        } else {
            layout = convertView as RelativeLayout
            holder = layout.tag as Holder
            holder!!.ivStatus!!.setImageResource(R.drawable.ic_avatar_big)
        }
        layout.id = position
        return layout
    }

    private inner class Holder {
        var ivStatus: ImageView? = null
    }
}
