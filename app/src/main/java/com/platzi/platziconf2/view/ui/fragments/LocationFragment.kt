package com.platzi.platziconf2.view.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

import com.platzi.platziconf2.R
import com.platzi.platziconf2.model.Location
import com.platzi.platziconf2.view.ui.fragments.dialogFragments.LocationDetailDialogFragment


class LocationFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_location, container, false) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val location = Location()
        val zoom = 16f
        val centerMap = LatLng(location.latitud, location.longitud)
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap,zoom))
        //agregando marcador
        val markerOptions = MarkerOptions()
        markerOptions.position(centerMap)
        markerOptions.title("Platzi Conf 2019")
        val bitMapLogoPlatzi = context?.applicationContext?.let {
            ContextCompat.getDrawable(it, R.drawable.logo_platzi)
        } as BitmapDrawable
        val smallMarker = Bitmap.createScaledBitmap(bitMapLogoPlatzi.bitmap, 150,150,false)
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))

        //agregar marcardor a mapa
        googleMap?.addMarker(markerOptions)
        googleMap?.setOnMarkerClickListener(this)

    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        findNavController().navigate(R.id.locationDetailDialogFragment)
        return true
    }

}
