package com.example.catfacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.catfacts.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val itemSelectedListener = NavigationBarView.OnItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.facts_menu -> {
                val fragment = FactsListFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnItemSelectedListener true
            }
            R.id.favorites_menu -> {
                val fragment = FavoritesListFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val bottomNavigationBar = view.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationBar.setOnItemSelectedListener(itemSelectedListener)
        if (savedInstanceState == null) {
            val fragment = FactsListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.content, fragment, fragment.javaClass.simpleName)
                .commit()
        }
    }
}