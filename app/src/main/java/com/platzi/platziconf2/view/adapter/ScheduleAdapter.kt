package com.platzi.platziconf2.view.adapter
import android.view.LayoutInflater
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.platzi.platziconf2.R
import com.platzi.platziconf2.model.Conference
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleAdapter(val scheduleClickListener: ScheduleClickListener) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    //crear una lista de contenido
    var conferenceList = ArrayList<Conference>()

    //diseño de cada item pasarlo de un layout en xml a un objeto viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleAdapter.ViewHolder {
       return  ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false))
    }

    //cuantos elementos tenemos para el recycler view
    override fun getItemCount() = conferenceList.size

    //como vamos a enlazar los elementos del layout con las propiedades del objeto
    //adignadndo valores a las variables creadas en ViewHolder
    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        //crear una varibale de refrencia para el objeto que se esta renderizando en pantalla
        val conference = conferenceList[position]

        holder.tvConferenceName.text = conference.title
        holder.tvConferenceSpeakerName.text = conference.speaker
        holder.tvConferenceTag.text = conference.tag

        //parsear hora
        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val simpleDateFormatAMPM = SimpleDateFormat("a")

        val cal = Calendar.getInstance()
        cal.time = conference.datetime
        val hourFormat = simpleDateFormat.format(conference.datetime)

        holder.tvConferencesHour.text = hourFormat
        holder.tvConferenceAMPM.text = simpleDateFormatAMPM.format(conference.datetime).toUpperCase()
        //click listener action
        holder.itemView.setOnClickListener {
            scheduleClickListener.onConferenceClick(conference, position)
        }
    }

    //crear varaibles para cada uno de los elemetnos del layout
    //inicializando
    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val tvConferencesHour = itemView.findViewById<TextView>(R.id.tvItemScheduleHour)
        val tvConferenceAMPM = itemView.findViewById<TextView>(R.id.tvItemScheduleAMPM)
        val tvConferenceName = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceName)
        val tvConferenceSpeakerName = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceSpeaker)
        val tvConferenceTag = itemView.findViewById<TextView>(R.id.tvItemScheduleTag)

    }

    //el adaptador esta listo pero debemos encontrar la forma de llamarlo y asisnarle los elemetnos a renderizar
    fun updateDataConferences(conferences: List<Conference>){
        conferenceList.clear()
        conferenceList.addAll(conferences)
        //se notifica cambios en el data set
        notifyDataSetChanged()
    }
}