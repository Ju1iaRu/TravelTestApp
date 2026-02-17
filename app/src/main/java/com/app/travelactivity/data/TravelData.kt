package com.app.travelactivity.data

import com.app.travelactivity.R
import com.app.travelactivity.models.Attraction
import com.app.travelactivity.models.Country

object TravelData {
    val countries = listOf(
        Country(
            id = 1,
            name = "Vietnam",
            imageResId = R.drawable.vietnam_flag, // We'll create placeholder drawables
            attractions = listOf(
                Attraction(
                    id = 1,
                    name = "Ha Long Bay",
                    description = "Ha Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. The bay features thousands of limestone karsts and isles in various shapes and sizes. The bay is a center of a larger zone which includes Bái Tử Long Bay to the northeast, and Cát Bà Island to the southwest.",
                    imageResId = R.drawable.ha_long_bay
                ),
                Attraction(
                    id = 2,
                    name = "Hoi An Ancient Town",
                    description = "Hoi An Ancient Town is an exceptionally well-preserved example of a Southeast Asian trading port dating from the 15th to the 19th century. Its buildings and its street plan reflect the influences, both indigenous and foreign, that have combined to produce this unique heritage site.",
                    imageResId = R.drawable.hoi_an
                ),
                Attraction(
                    id = 3,
                    name = "Ho Chi Minh City",
                    description = "Ho Chi Minh City, formerly known as Saigon, is the largest city in Vietnam. It's known for its French colonial landmarks, including Notre-Dame Cathedral, made entirely of materials imported from France, and the 19th-century Central Post Office.",
                    imageResId = R.drawable.ho_chi_minh
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
