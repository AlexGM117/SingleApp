package com.softhink.single.ui.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import com.softhink.single.R


class Converter {
    fun convertLayoutToImage(context: Context, count: Int, drawableId: Int): Drawable {
        val view = LayoutInflater.from(context).inflate(R.layout.badge_icon_layout, null)
        (view.findViewById(R.id.icon_badge) as ImageView).setImageResource(drawableId)
        if (count == 0){
            val counterTextPanel = view.findViewById<View>(R.id.counterValuePanel)
            counterTextPanel.visibility = View.GONE
        } else {
            val textCount = view.findViewById<TextView>(R.id.count)
            textCount.text = count.toString()
        }

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight())

        view.isDrawingCacheEnabled = true
        view.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
        val bitmap = Bitmap.createBitmap(view.drawingCache)
        view.isDrawingCacheEnabled = false

        return BitmapDrawable(context.resources, bitmap)
    }
}