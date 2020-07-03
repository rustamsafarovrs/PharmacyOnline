package tj.rs.pharmacyonline.ui

import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import kotlinx.android.synthetic.main.toolbar.*
import tj.rs.pharmacyonline.R
import tj.rs.pharmacyonline.databinding.ActivityAuthorizedBinding
import tj.rs.pharmacyonline.ui.profile.profilesettings.ProfileSettingsViewModel

class AuthorizedActivity : AppCompatActivity() {

    lateinit var binding: ActivityAuthorizedBinding
    lateinit var profileViewModel: ProfileSettingsViewModel

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
    }

    override fun onResume() {
        super.onResume()

        profileViewModel.loadProfile()
    }
}
