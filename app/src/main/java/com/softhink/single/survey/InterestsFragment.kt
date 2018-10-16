package com.softhink.single.survey


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.igalata.bubblepicker.BubblePickerListener
import com.igalata.bubblepicker.adapter.BubblePickerAdapter
import com.igalata.bubblepicker.model.PickerItem
import com.softhink.single.R
import kotlinx.android.synthetic.main.arrow_back_and_next.*
import kotlinx.android.synthetic.main.fragment_interests.*

/**
 * A simple [Fragment] subclass.
 */
class InterestsFragment : Fragment(), BubblePickerListener, View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_interests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            picker.adapter = object : BubblePickerAdapter {
                var titles = arrayOf("Encontrar pareja", "Diversión", "Sexo casual", "Conocer gente nueva")

                override val totalCount: Int
                    get() = titles.size

                override fun getItem(i: Int): PickerItem {
                    val item = PickerItem()
                    item.title = titles[i]
                    item.color = Color.parseColor("#DA7C86")
                    item.textColor = Color.WHITE
                    item.backgroundImage = ContextCompat.getDrawable(context!!, R.drawable.background_bubble)
                    return item
                }
            }

            picker.centerImmediately = true
            picker.bubbleSize = 30
            picker.listener = this

            btnLastPage.setOnClickListener(this)
            btnFirstPage.setOnClickListener(this)
        }
    }

    override fun onResume() {
        super.onResume()
        picker!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        picker!!.onPause()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnLastPage -> fragmentManager?.beginTransaction()?.
                    replace(R.id.containerPreferences,
                    TastesFragment(),
                    TastesFragment::class.java.simpleName)
                    ?.addToBackStack(TastesFragment::class.java.simpleName)
                    ?.commit()

            R.id.btnFirstPage -> fragmentManager?.popBackStack()
        }
    }

    override fun onBubbleSelected(pickerItem: PickerItem) {
        Log.i("SingleApp", "Burbuja seleccionada: " + pickerItem.title!!)
    }

    override fun onBubbleDeselected(pickerItem: PickerItem) {
        Log.i("SingleApp", "Burbuja desseleccionada: " + pickerItem.title!!)
    }
}// Required empty public constructor
