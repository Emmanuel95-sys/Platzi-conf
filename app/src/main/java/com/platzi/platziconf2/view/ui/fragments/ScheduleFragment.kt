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
import androidx.recyclerview.widget.LinearLayoutManager

import com.platzi.platziconf2.R
import com.platzi.platziconf2.model.Conference
import com.platzi.platziconf2.view.adapter.ScheduleAdapter
import com.platzi.platziconf2.view.adapter.ScheduleClickListener
import com.platzi.platziconf2.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*

//hay que implementar el evento click
class ScheduleFragment : Fragment(), ScheduleClickListener {

    //acceso a los datos de view model
    private lateinit var adapterSchedule: ScheduleAdapter
    private lateinit var viewModelSchedule :ScheduleViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //necesitamos hcer uso del ciclo de vida de view model
        //tenemos que agregar dos penendencias.
        //nos permite observar activamente los datos y acceder a la informacion
        viewModelSchedule = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)

        //acceder a los metodos del viewModel
        viewModelSchedule.refreshSchedule()
        //crear una instancia del adaptador
        adapterSchedule = ScheduleAdapter(this)

        //atributos al recycler view
        rvSchedule.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterSchedule
        }
        //observacion de los datos
        observeViewModel()

    }

    private fun observeViewModel() {
        //observar cosntantemente la informacioon en los datos de rv
        //monitoreando en tiempo real si hay cambios en la informacion.
        //y en caunto ocurra un cambio en ese instante voy a actualizar mi Interfaz de usuario
        viewModelSchedule.listSchedule.observe(this, Observer<List<Conference>>{conferences ->
            adapterSchedule.updateDataConferences(conferences)
        })

        //progressBar
        viewModelSchedule.isLoading.observe(this, Observer<Boolean>{isLoading ->
            if(isLoading != null ){
                rlProgressBarSchedule.visibility = View.INVISIBLE
            }
        })
    }
//en este metodo capturamos el objeto al hacer click en el y lo enviamos a la vista de detalle
    override fun onConferenceClick(conference: Conference, position: Int) {
        val clickedConference = bundleOf("conference" to conference)
        findNavController().navigate(R.id.scheduleDetailDialogFragment, clickedConference)
    }
}
