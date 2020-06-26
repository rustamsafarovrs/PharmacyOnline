package tj.rs.pharmacyonline.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import tj.rs.pharmacyonline.data.preferences.Preferences
import tj.rs.pharmacyonline.data.repository.profile.ProfileRepository

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    val authRepository =
        ProfileRepository(
            Preferences(getApplication())
        )

    fun isAuthorized(): Boolean {
        return authRepository.getAuthorized()
    }
}