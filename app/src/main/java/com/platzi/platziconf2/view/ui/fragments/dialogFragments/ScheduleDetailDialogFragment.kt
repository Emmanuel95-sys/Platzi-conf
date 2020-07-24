package com.platzi.platziconf2.view.ui.fragments.dialogFragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment

import com.platzi.platziconf2.R
import com.platzi.platziconf2.model.Conference
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import java.text.SimpleDateFormat

class ScheduleDetailDialogFragment : DialogFragment() {
    //settear el estilo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolBarScheduleDetail.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolBarScheduleDetail.setTitleTextColor(Color.WHITE)
        toolBarScheduleDetail.setNavigationOnClickListener {
            dismiss()
        }

        //obteniendo el objeto enviado desde el cronograma
        val clickedConference = arguments?.getSerializable("conference") as Conference
        toolBarScheduleDetail.title = clickedConference.title
        tvScheduleDetailTitle.text = clickedConference.title
        tvDetailConferenceNameSpeaker.text = clickedConference.speaker
        tvDetailConferenceTag.text = clickedConference.tag
        tvDetailConferenceDescription.text = clickedConference.description
        val pattern = "dd/MM/yyy hh:mm a"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date = simpleDateFormat.format(clickedConference.datetime)
        tvDetailConferenceHour.text = date
    }

    //margenes de toda la pantalla
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT )
    }
}
