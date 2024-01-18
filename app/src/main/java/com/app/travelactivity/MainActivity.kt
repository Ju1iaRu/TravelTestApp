package com.app.travelactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.app.travelactivity.fragments.AddFragment
import com.app.travelactivity.fragments.DetailsFragment
import com.app.travelactivity.fragments.ProfileFragment
import com.app.travelactivity.fragments.TravelFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val fragmentManager: FragmentManager = supportFragmentManager
    private val travelFragment = TravelFragment()
    private val detailsFragment = DetailsFragment()
    private val addFragment = AddFragment()
    private val profileFragment = ProfileFragment()

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_travel -> {
                    switchFragment(travelFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_details -> {
                    switchFragment(detailsFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_add -> {
                    switchFragment(addFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    switchFragment(profileFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // Set the initial fragment
        switchFragment(travelFragment)
    }

    private fun switchFragment(fragment: Fragment) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }
}
