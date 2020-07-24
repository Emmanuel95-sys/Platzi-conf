package com.platzi.platziconf2.view.ui.fragments.dialogFragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.platzi.platziconf2.R
import com.platzi.platziconf2.model.Speaker
import kotlinx.android.synthetic.main.fragment_speakers_detail_dialog.*

class SpeakersDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_speakers_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolBarSpeakerDetail.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolBarSpeakerDetail.setTitleTextColor(Color.WHITE)
        toolBarSpeakerDetail.setNavigationOnClickListener {
            dismiss()
        }

        val clickedSpeaker = arguments?.getSerializable("speaker") as Speaker

        toolBarSpeakerDetail.title = clickedSpeaker.name
        Glide.with(view.context).load(clickedSpeaker.urlImage).into(ivSpeakerDetail)
        tvSpeakersDetailDialogName.text = clickedSpeaker.name
        tvSpeakersDetailDialogJobTitle.text = clickedSpeaker.jobtitle
        tvSpeakersDetailDialogCompanyName.text = clickedSpeaker.company
        tvSpeakersDetailDialogTwitterIcon.setOnClickListener {

            val baseurl = "https://twitter.com/"
            val twitterUsername = clickedSpeaker.twitter
            val twitterIntent =  Intent(Intent.ACTION_VIEW)
            twitterIntent.setData(Uri.parse(baseurl + twitterUsername))
            startActivity(twitterIntent)

        }
        tvSpeakersDetailDialogBio.text = clickedSpeaker.biography
    }



}