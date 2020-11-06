package com.riyaldi.e_toko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private var currentFragment : Fragment = HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        setupFragment(currentFragment)
        bottomNavClick()
    }

    private fun bottomNavClick() {
        main_bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    currentFragment = HomeFragment()
                    setupFragment(currentFragment)
                    true
                }

                R.id.navigation_cart -> {
                    currentFragment = CartFragment()
                    setupFragment(currentFragment)
                    true
                }

                R.id.navigation_profile -> {
                    currentFragment = TokoFragment()
                    setupFragment(currentFragment)
                    true
                }

                else -> false
            }
        }
    }

    private fun setupFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, fragment::class.java.simpleName)
                .commit()
    }
}