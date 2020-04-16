package tj.rs.pharmacyonline.ui.main

import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import kotlinx.android.synthetic.main.toolbar.*
import tj.rs.pharmacyonline.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = toolbar
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupWithNavController(toolbar, navController, appBarConfiguration)

        val profileView = findViewById<RelativeLayout>(R.id.profileRelativeLayout)
        profileView.setOnClickListener { navController.navigate(R.id.profileActivity) }
    }
}
