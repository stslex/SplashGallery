package com.stslex.splashgallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.stslex.splashgallery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var actionBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBarWithConfiguration()
    }

    private fun setActionBarWithConfiguration() {
        setSupportActionBar(binding.mainToolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        actionBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home))
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController, actionBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean = navigateUp(navController, actionBarConfiguration)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}