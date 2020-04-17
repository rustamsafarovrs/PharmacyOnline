package tj.rs.pharmacyonline.ui.profile

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_profile.*
import tj.rs.pharmacyonline.R

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val navigation = findNavController(R.id.nav_host_fragment)
        val close = findViewById<ImageView>(R.id.closeImageView)
        close.setOnClickListener {
            if (!navigation.popBackStack()) {
                this.finish()
            }
        }

        navigation.addOnDestinationChangedListener { _, destination, _ ->
            tv_toolbar_title.text = destination.label
        }
    }

}
