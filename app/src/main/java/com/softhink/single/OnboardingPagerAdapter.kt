package com.softhink.single

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class OnboardingPagerAdapter(private val views: Array<Int>,
                             private val context: Context,
                             private val listener: OnboardingPagerAdapter.OnClickPager) : PagerAdapter(),
        View.OnClickListener {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(views[position], container, false)

        val btnSkip = layout.findViewById<TextView>(R.id.skipOnboarding)
        btnSkip.setOnClickListener(this)

        container.addView(layout)
        return layout
    }

    override fun isViewFromObject(v: View, o: Any): Boolean {
        return v == o
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount()  = views.size

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.skipOnboarding -> {
                listener.onSkipOnboarding()
            }
        }
    }

    interface OnClickPager{
        fun onSkipOnboarding()
    }
}