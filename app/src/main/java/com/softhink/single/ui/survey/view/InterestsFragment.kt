package com.softhink.single.ui.survey.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.igalata.bubblepicker.BubblePickerListener
import com.igalata.bubblepicker.adapter.BubblePickerAdapter
import com.igalata.bubblepicker.model.PickerItem
import com.softhink.single.R
import com.softhink.single.ui.base.BaseFragment
import com.softhink.single.ui.survey.SurveyViewModel
import kotlinx.android.synthetic.main.arrow_back_and_next.*
import kotlinx.android.synthetic.main.fragment_interests.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class InterestsFragment : BaseFragment(), BubblePickerListener, View.OnClickListener {

    private lateinit var viewModel: SurveyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(SurveyViewModel::class.java)
        }?:throw Exception("Invalid Activity")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_interests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            picker.adapter = object : BubblePickerAdapter {
                var titles = viewModel.listGustos.value!!.data!!

                override val totalCount: Int
                    get() = titles.size

                override fun getItem(position: Int): PickerItem {
                    return PickerItem().apply {
                        title = titles[position]
                        color = Color.parseColor("#DA7C86")
                        textColor = Color.WHITE
                        backgroundImage = ContextCompat.getDrawable(context!!, R.drawable.bg_bubble_red)
                    }
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
        picker.onResume()
    }

    override fun onPause() {
        super.onPause()
        picker.onPause()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnLastPage -> fragmentManager?.beginTransaction()?.
                    replace(R.id.containerSurvey, TastesFragment(), TastesFragment::class.java.simpleName)?.
                    addToBackStack(TastesFragment::class.java.simpleName)
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
}