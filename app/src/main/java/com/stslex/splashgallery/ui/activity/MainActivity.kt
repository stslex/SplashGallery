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
import com.stslex.splashgallery.utils.Resources

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var _navController: NavController? = null
    private val navController: NavController get() = _navController!!
    private var _actionBarConfiguration: AppBarConfiguration? = null
    private val actionBarConfiguration: AppBarConfiguration get() = _actionBarConfiguration!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setResources()
        setActionBarWithConfiguration()
    }

    private fun setActionBarWithConfiguration() {
        setSupportActionBar(binding.mainToolbar)
        _navController =
            (supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
                .navController
        _actionBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home))
        setupActionBarWithNavController(navController, actionBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean = navigateUp(navController, actionBarConfiguration)

    private fun setResources() {
        Resources.photos = getString(R.string.label_photos)
        Resources.likes = getString(R.string.label_likes)
        Resources.collections = getString(R.string.label_collections)
        Resources.cache = cacheDir
        Resources.unknown = getString(R.string.string_unknown)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}