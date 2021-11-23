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
import com.stslex.splashgallery.ui.utils.AppResources.setResources

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val navController: NavController by lazy {
        val navId = R.id.nav_host_fragment
        val fragment = supportFragmentManager.findFragmentById(navId) as NavHostFragment
        fragment.navController
    }

    private val actionBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(setOf(R.id.nav_home))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.CustomTheme)
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        resources.setResources()
        setSupportActionBar(binding.mainToolbar)
        setupActionBarWithNavController(navController, actionBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean = navigateUp(navController, actionBarConfiguration)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}