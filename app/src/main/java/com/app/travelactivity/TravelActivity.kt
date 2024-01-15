package com.app.travelactivity

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.travelactivity.databinding.ActivityTravelBinding
import com.app.travelactivity.fragments.AddFragment
import com.app.travelactivity.fragments.DetailsFragment
import com.app.travelactivity.fragments.ProfileFragment
import com.app.travelactivity.fragments.TravelFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TravelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTravelBinding
    private val travelFragment = TravelFragment()
    private val detailsFragment = DetailsFragment()
    private val addFragment = AddFragment()
    private val profileFragment = ProfileFragment()

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_travel -> {
                    replaceFragment(travelFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_details -> {
                    replaceFragment(detailsFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_add -> {
                    replaceFragment(addFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_profile -> {
                    replaceFragment(profileFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

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

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        replaceFragment(travelFragment)

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

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
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
