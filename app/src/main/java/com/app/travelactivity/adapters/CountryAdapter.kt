package com.app.travelactivity.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.travelactivity.R
import com.app.travelactivity.models.Country

class CountryAdapter(
    private val countries: List<Country>,
    private val onCountryClick: (Country) -> Unit
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewCountry)
        val textViewName: TextView = itemView.findViewById(R.id.textViewCountryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.textViewName.text = country.name
        // For now, we'll use a placeholder. In a real app, you'd load images from resources or URLs
        holder.imageView.setImageResource(R.drawable.ic_launcher_foreground)
        
        holder.itemView.setOnClickListener {
            onCountryClick(country)
        }
    }

    override fun getItemCount(): Int = countries.size
}
