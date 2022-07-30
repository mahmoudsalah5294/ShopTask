package com.mahmoudsalah.shoptask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.mahmoudsalah.shoptask.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val nController by lazy {findNavController(R.id.fragment_container)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment))
        setupActionBarWithNavController(nController, appBarConfiguration)


    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}