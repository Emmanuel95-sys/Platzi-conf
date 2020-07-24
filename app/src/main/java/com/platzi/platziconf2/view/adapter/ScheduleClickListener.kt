package com.platzi.platziconf2.view.adapter

import com.platzi.platziconf2.model.Conference

interface ScheduleClickListener {

    fun onConferenceClick(conference: Conference, position: Int)

}