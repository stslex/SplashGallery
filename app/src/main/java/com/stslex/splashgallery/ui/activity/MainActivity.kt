package com.stslex.splashgallery.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.stslex.splashgallery.R
import com.stslex.splashgallery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.CustomTheme)
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)
        setupActionBarWithNavController(navController, actionBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean = navigateUp(navController, actionBarConfiguration)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    private val actionBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(setOf(R.id.nav_home))
    }
}