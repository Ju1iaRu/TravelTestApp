package com.app.travelactivity

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.travelactivity.databinding.ActivityTravelBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TravelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTravelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTravelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Мои путешествия"

        val travelList = generateTravelList()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        val spanCount = 2 // количество столбцов
        val spacing = resources.getDimensionPixelSize(R.dimen.grid_spacing) // размер пространства

        fab.setOnClickListener {
            // Запускаем новую активность при клике на иконку "Добавить"
            val intent = Intent(this, AddTravelActivity::class.java)
            startActivity(intent)
        }

        recyclerView.layoutManager = GridLayoutManager(this, spanCount)
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.left = spacing
                outRect.right = spacing
                outRect.top = spacing
                outRect.bottom = spacing
            }
        })
        recyclerView.adapter = TravelAdapter(travelList) { clickText ->
            // Обработка клика на элементе списка
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("clickText", clickText)
            startActivity(intent)
        }
    }


    private fun generateTravelList(): List<TravelCard> {
        val travelList = mutableListOf<TravelCard>()

        // данные о путешествиях и изображения
        travelList.add(
            TravelCard(
                "Тайланд, Пхукет",
                "Город любви и света",
                R.drawable.phuket_image,
                "Январь 2023 Достопримечательности: Большой будда"
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

        travelList.add(
            TravelCard(
                "Рим, Италия",
                "Город чуда",
                R.drawable.rome_image,
                "Август 2023 Достопримечательности: Колизей"
            )
        )

        travelList.add(
            TravelCard(
                "Португалия, Лиссабон",
                "Древний город",
                R.drawable.portugal_image,
                "Март 2023 Достопримечательности: Порту, Назаре, Обидуш и др города"
            )
        )

        return travelList
    }
}
