package tj.rs.pharmacyonline.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_unauthorized.*
import tj.rs.pharmacyonline.R

class UnauthorizedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unauthorized)

        val toolbar = toolbar
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.chooseLoginTypeFragment) {
                toolbar.visibility = View.INVISIBLE
            } else {
                toolbar.visibility = View.VISIBLE
            }
        }
    }
}