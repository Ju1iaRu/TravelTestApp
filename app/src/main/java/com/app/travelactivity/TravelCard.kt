package com.app.travelactivity

data class TravelCard(
    val title: String,
    val description: String,
    val imageResId: Int,
    val clickText: String
)

fun generateTravelList(): List<TravelCard> {
    val travelList = mutableListOf<TravelCard>()

    travelList.add(
        TravelCard(
            "Тайланд, Пхукет",
            "Город любви и света",
            R.drawable.kyala_image,
            "Январь 2023 Достопримечательности: Башни близнецы"
        )
    )
    travelList.add(
        TravelCard(
            "Малайзия, Куала-Лумпур",
            "Древний инкский город",
            R.drawable.kyala_image,
            "Январь 2023 Достопримечательности: Башни близнецы"
        )
    )
    return travelList
}

