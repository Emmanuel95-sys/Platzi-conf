package com.platzi.platziconf2.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.platzi.platziconf2.R
import com.platzi.platziconf2.model.Speaker

class SpeakerAdapter(var speakerItemClickListener: SpeakerItemClickListener) : RecyclerView.Adapter<SpeakerAdapter.ViewHolder>() {

    var speakerList = ArrayList<Speaker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeakerAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_speaker,parent,false))
    }


    override fun getItemCount(): Int {
        return speakerList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentSpeaker = speakerList[position]

        holder.speakerJobTitleTextView.text = currentSpeaker.jobtitle
        holder.speakerNameTextView.text = currentSpeaker.name
        Glide.with(holder.speakerImageView).load(currentSpeaker.urlImage)
            .into(holder.speakerImageView)

        holder.itemView.setOnClickListener {
            speakerItemClickListener.onSpeakerClickListener(currentSpeaker, position)
        }
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val speakerImageView = itemView.findViewById<ImageView>(R.id.ivItemSpeakerPhoto)
        val speakerNameTextView = itemView.findViewById<TextView>(R.id.tvItemSpeakerName)
        val speakerJobTitleTextView = itemView.findViewById<TextView>(R.id.tvItemSpeakerJobTitle)
    }

    fun updateDateSpeakers(speakers: List<Speaker>){
        speakerList.clear()
        speakerList.addAll(speakers)
        notifyDataSetChanged()
    }
}