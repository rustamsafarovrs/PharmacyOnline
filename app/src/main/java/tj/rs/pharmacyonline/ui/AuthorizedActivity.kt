package tj.rs.pharmacyonline.ui

import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import kotlinx.android.synthetic.main.toolbar.*
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.databinding.ActivityAuthorizedBinding
import tj.rs.pharmacyonline.ui.profile.profilesettings.ProfileSettingsViewModel
import tj.rs.pharmacyonline.ui.shopping_cart.ShoppingCartViewModel
import tj.rs.pharmacyonline.utils.getSlideUpAnimBuilder

class AuthorizedActivity : AppCompatActivity() {

    lateinit var binding: ActivityAuthorizedBinding
    lateinit var profileViewModel: ProfileSettingsViewModel
    lateinit var shoppingCartViewModel: ShoppingCartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_authorized)
        binding.lifecycleOwner = this
        profileViewModel = ViewModelProvider(this).get(ProfileSettingsViewModel::class.java)
        binding.profileViewModel = profileViewModel

        val toolbar = toolbar
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupWithNavController(toolbar, navController, appBarConfiguration)

        val profileView = findViewById<RelativeLayout>(R.id.profileRelativeLayout)
        profileView.setOnClickListener { navController.navigate(R.id.profileActivity) }

        shoppingCartViewModel = ViewModelProvider(this).get(ShoppingCartViewModel::class.java)
        binding.shoppingCartViewModel = shoppingCartViewModel

        shoppingCartViewModel.openShoppingCardFragment.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                if (navController.currentDestination != navController.graph.findNode(R.id.shoppingCartFragment)) {
                    navController.navigate(
                        R.id.shoppingCartFragment,
                        Bundle.EMPTY,
                        getSlideUpAnimBuilder().build()
                    )
                }
            }
        })

    }

    override fun onResume() {
        super.onResume()

        profileViewModel.loadProfile()
    }
}
