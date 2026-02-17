package com.app.travelactivity.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.travelactivity.R
import com.app.travelactivity.models.Attraction

class AttractionAdapter(
    private val attractions: List<Attraction>,
    private val onAttractionClick: (Attraction) -> Unit
) : RecyclerView.Adapter<AttractionAdapter.AttractionViewHolder>() {

    class AttractionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewAttraction)
        val textViewName: TextView = itemView.findViewById(R.id.textViewAttractionName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_attraction, parent, false)
        return AttractionViewHolder(view)
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        val attraction = attractions[position]
        holder.textViewName.text = attraction.name
        // Use the attraction's specific image resource
        holder.imageView.setImageResource(attraction.imageResId)
        
        holder.itemView.setOnClickListener {
            onAttractionClick(attraction)
        }
    }

    override fun getItemCount(): Int = attractions.size
}
