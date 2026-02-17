package com.app.travelactivity.models

data class Country(
    val id: Int,
    val name: String,
    val imageResId: Int,
    val attractions: List<Attraction>
)
