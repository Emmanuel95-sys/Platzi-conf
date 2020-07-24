package com.platzi.platziconf2.view.adapter

import com.platzi.platziconf2.model.Speaker

interface SpeakerItemClickListener {

    fun onSpeakerClickListener(speaker: Speaker, position : Int)

}