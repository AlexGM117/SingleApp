package com.softhink.single.ui.survey.view

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.igalata.bubblepicker.BubblePickerListener
import com.igalata.bubblepicker.adapter.BubblePickerAdapter
import com.igalata.bubblepicker.model.PickerItem
import com.softhink.single.R
import com.softhink.single.ui.base.BaseFragment
import com.softhink.single.ui.survey.SurveyViewModel
import kotlinx.android.synthetic.main.arrow_back.*
import kotlinx.android.synthetic.main.fragment_tastes.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 *
 */
class TastesFragment : BaseFragment(),
        View.OnClickListener, BubblePickerListener {

    private lateinit var viewModel: SurveyViewModel
    private var listSelected = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(SurveyViewModel::class.java)
        }?:throw Exception("Invalid Activity")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tastes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            val tastes = viewModel.listGustos.value
            pickerTastes.adapter = object : BubblePickerAdapter {
                override val totalCount = tastes?.size!!

                override fun getItem(position: Int): PickerItem {
                    return PickerItem().apply {
                        title = tastes!![position].nombre
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
                if (listSelected.isEmpty()) {
                    showMessageDialog("Debes seleccionar al menos una opcion")
                } else {
                    viewModel.saveTastes(listSelected)
//                callback.showTerms()
                }
            }

            R.id.btnPrevious -> {
                fragmentManager?.popBackStack()
            }
        }
    }

    override fun onBubbleDeselected(item: PickerItem) {
        listSelected.remove(item.title)
    }

    override fun onBubbleSelected(item: PickerItem) {
        listSelected.add(item.title!!)
    }
}
