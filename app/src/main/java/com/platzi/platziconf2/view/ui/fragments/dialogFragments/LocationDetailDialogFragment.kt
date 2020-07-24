package com.platzi.platziconf2.view.ui.fragments.dialogFragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.platzi.platziconf2.R
import com.platzi.platziconf2.model.Location
import kotlinx.android.synthetic.main.fragment_location_detail_dialog.*
import kotlinx.android.synthetic.main.fragment_speakers_detail_dialog.*

class LocationDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_location_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolBarLocationDetail.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolBarLocationDetail.setTitleTextColor(Color.WHITE)
        toolBarLocationDetail.setNavigationOnClickListener {
            dismiss()
        }


        val location = Location()
        toolBarLocationDetail.title = location.name
        tvDetailLocationPlace.text = location.name
        tvDetailLocationAddress.text = location.address
        tvDetailLocationPhone.text = location.phone
        tvDetailLocationWebsite.text = location.website
    }
}
