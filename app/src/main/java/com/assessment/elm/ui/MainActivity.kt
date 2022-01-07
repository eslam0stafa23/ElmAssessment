package com.assessment.elm.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.assessment.elm.R
import com.assessment.elm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var navController: NavController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupActivity()
    setupNavigationDestinationListener()
  }

  private fun setupActivity() {
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    navController = findNavController(R.id.nav_host_fragment_activity_main)
    binding.navView.setupWithNavController(navController)
  }

  private fun setupNavigationDestinationListener() {
    navController.addOnDestinationChangedListener { _: NavController?, destination: NavDestination, _: Bundle? ->
      when (destination.id) {
        R.id.navigation_login, R.id.navigation_verify_otp -> {
          binding.navView.visibility = View.GONE
        }
        R.id.navigation_home, R.id.navigation_dashboard -> {
          binding.navView.visibility = View.VISIBLE
        }
      }
    }
  }

}