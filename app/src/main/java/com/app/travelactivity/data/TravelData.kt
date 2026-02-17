package com.app.travelactivity.data

import com.app.travelactivity.R
import com.app.travelactivity.models.Attraction
import com.app.travelactivity.models.Country

object TravelData {
    val countries = listOf(
        Country(
            id = 1,
            name = "Vietnam",
            imageResId = R.drawable.vietnam_flag,
            attractions = listOf(
                Attraction(
                    id = 1,
                    name = "Ho Chi Minh City",
                    description = "Ho Chi Minh City, formerly Saigon, is Vietnam's largest city. It is known for its vibrant street life, historic French colonial architecture, and landmarks such as Notre-Dame Cathedral and the Central Post Office.",
                    imageResId = R.drawable.ho_chi_minh
                ),
                Attraction(
                    id = 2,
                    name = "Hanoi",
                    description = "Hanoi is the capital of Vietnam, famous for its centuries-old architecture, narrow Old Quarter streets, and rich culture blending Southeast Asian, Chinese, and French influences.",
                    imageResId = R.drawable.hanoi
                ),
                Attraction(
                    id = 3,
                    name = "Nha Trang",
                    description = "Nha Trang is a coastal city known for its beautiful beaches, clear waters, and nearby islands, making it one of Vietnam's most popular seaside destinations.",
                    imageResId = R.drawable.nha_trang
                ),
                Attraction(
                    id = 4,
                    name = "Da Nang",
                    description = "Da Nang is a modern coastal city in central Vietnam, known for its long sandy beaches, the Marble Mountains, and the famous Golden Bridge held by giant stone hands.",
                    imageResId = R.drawable.da_nang
                ),
                Attraction(
                    id = 5,
                    name = "Phu Quoc",
                    description = "Phu Quoc is a tropical island off the coast of Vietnam, famous for its white-sand beaches, clear waters, and relaxed island atmosphere.",
                    imageResId = R.drawable.phu_quoc
                )
            )
        )
    )

    fun getCountryById(id: Int): Country? {
        return countries.find { it.id == id }
    }

    fun getAttractionById(countryId: Int, attractionId: Int): Attraction? {
        return getCountryById(countryId)?.attractions?.find { it.id == attractionId }
    }
}
