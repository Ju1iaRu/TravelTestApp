package com.app.travelactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class TravelAdapter(
    private val travelList: List<TravelCard>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<TravelAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val travelImageView: ImageView = itemView.findViewById(R.id.travelImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.travel_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val travelCard: TravelCard = travelList[position]

        holder.titleTextView.text = travelCard.title
        holder.descriptionTextView.text = travelCard.description

        // Загрузка изображения с использованием библиотеки Glide
        Glide.with(holder.itemView.context)
            .load(travelCard.imageResId)
            .centerCrop()
            .into(holder.travelImageView)

        // Добавляем слушатель кликов к ImageView
        holder.travelImageView.setOnClickListener {
            onItemClick(travelCard.clickText)

        }
    }

    override fun getItemCount(): Int {
        return travelList.size
    }
}
