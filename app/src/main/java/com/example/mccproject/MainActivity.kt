package com.example.mccproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.fragment.app.Fragment
import com.example.mccproject.fragments.*

import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.mainContainer,
                        LatestNewsFragment()
                    ).commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_statistics -> {
                    replaceFragment(StatisticsFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_urgent -> {
                    replaceFragment(UrgentFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_history -> {
                    replaceFragment(HistoricalInformation())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile-> {
                    replaceFragment(ProfileFragment() )
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        nav_view.selectedItemId =R.id.navigation_home


    }


    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.mainContainer,
            fragment).commit()
    }


}