package com.baish.skyscanner.ui.onboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.baish.skyscanner.R

class NavigationActivity : AppCompatActivity() {
    private var host : NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        host = Navigation.findNavController(this, R.id.nav_host_fragment_container)
    }
}