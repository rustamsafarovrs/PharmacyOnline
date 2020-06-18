package tj.rs.pharmacyonline.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tj.rs.pharmacyonline.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }

    override fun onBackPressed() {
//        super.onBackPressed()
        //do nothing
    }
}