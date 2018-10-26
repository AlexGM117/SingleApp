package com.softhink.single.ui.survey

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.igalata.bubblepicker.BubblePickerListener
import com.igalata.bubblepicker.adapter.BubblePickerAdapter
import com.igalata.bubblepicker.model.PickerItem
import com.softhink.single.R
import kotlinx.android.synthetic.main.arrow_back.*
import kotlinx.android.synthetic.main.fragment_tastes.*

/**
 * A simple [Fragment] subclass.
 *
 */
class TastesFragment : Fragment(),
        View.OnClickListener, BubblePickerListener {

    private lateinit var tastes :Array<String>
    private lateinit var callback: CallbackSurvey

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tastes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            tastes = arrayOf("Cafeterías", "Comida rápida", "Bares", "Restaurantes")

            pickerTastes.adapter = object : BubblePickerAdapter {
                override val totalCount = tastes.size

                override fun getItem(position: Int): PickerItem {
                    return PickerItem().apply {
                        title = tastes[position]
                        textColor = Color.WHITE
                        textSize = 32F
                        color = Color.parseColor("#8b9dd3")
                        backgroundImage = ContextCompat.getDrawable(context!!, R.drawable.bg_bubble_blue)
                    }
                }
            }
            pickerTastes.centerImmediately = true
            pickerTastes.bubbleSize = 60
            pickerTastes.listener = this

            btnSendSurvey.setOnClickListener(this)
            btnPrevious.setOnClickListener(this)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callback = context as CallbackSurvey
    }

    override fun onResume() {
        super.onResume()
        pickerTastes.onResume()
    }

    override fun onPause() {
        super.onPause()
        pickerTastes.onPause()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnSendSurvey -> {
                callback.showTerms()
            }

            R.id.btnPrevious -> {
                fragmentManager?.popBackStack()
            }
        }
    }

    override fun onBubbleDeselected(item: PickerItem) {

    }

    override fun onBubbleSelected(item: PickerItem) {

    }

    interface CallbackSurvey {
        fun showTerms()
    }
}
