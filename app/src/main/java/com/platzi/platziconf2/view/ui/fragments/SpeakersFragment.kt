package com.platzi.platziconf2.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.platzi.platziconf2.R
import com.platzi.platziconf2.model.Speaker
import com.platzi.platziconf2.view.adapter.SpeakerAdapter
import com.platzi.platziconf2.view.adapter.SpeakerItemClickListener
import com.platzi.platziconf2.viewmodel.SpeakersViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*

class SpeakersFragment : Fragment(), SpeakerItemClickListener {

    private lateinit var adapterSpeakers : SpeakerAdapter
    private lateinit var viewModelSpeakers : SpeakersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelSpeakers = ViewModelProviders.of(this).get(SpeakersViewModel::class.java)
        viewModelSpeakers.refreshSpeakers()

        adapterSpeakers = SpeakerAdapter(this)

        rvSpeakers.apply {
            layoutManager = GridLayoutManager(view.context,2 )
            adapter = adapterSpeakers
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModelSpeakers.listSpeakers.observe(this, Observer<List<Speaker>>{
            adapterSpeakers.updateDateSpeakers(it)
        })

        viewModelSpeakers.isLoading.observe(this, Observer<Boolean>{
            if(it != null){
                rlLoadingSpeakers.visibility = View.INVISIBLE
            }
        })
    }


    override fun onSpeakerClickListener(speaker: Speaker, position: Int) {
        val clickedSpeaker = bundleOf("speaker" to speaker)
        findNavController().navigate(R.id.speakersDetailDialogFragment, clickedSpeaker)
    }
}
