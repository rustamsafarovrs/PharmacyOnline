package tj.rs.pharmacyonline.ui.profile.profilesettings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import tj.rs.pharmacyonline.data.const.UserParams
import tj.rs.pharmacyonline.data.model.User
import tj.rs.pharmacyonline.data.preferences.Preferences
import tj.rs.pharmacyonline.data.repository.profile.ProfileRepository
import tj.rs.pharmacyonline.utils.live_data.Event
import java.text.SimpleDateFormat
import java.util.*

class ProfileSettingsViewModel(application: Application) : AndroidViewModel(application) {

    val profileRepository = ProfileRepository(Preferences(getApplication()))
    val user =
        MutableLiveData(User(profileRepository.getCode(), profileRepository.getPhoneNumber()))
    val isLoading = MutableLiveData<Boolean>()
    var avatar = MutableLiveData("")
    val dateFormatted = MutableLiveData<String>()

    init {
        loadProfile()
    }

    fun loadProfile() {
        isLoading.postValue(true)
        profileRepository.getProfile(
            mapOf(
                UserParams.PHONE_NUMBER to user.value!!.phoneNumber,
                UserParams.CODE to user.value?.code.toString()
            ),
            object : ProfileRepository.OnGetProfileReadyCallback {
                override fun onDataReady(data: User?) {
                    isLoading.postValue(false)
                    if (data != null) {
                        user.postValue(data)
                        initAvatar(data)
                        initDate(data.created)
                    }
                }
            })
    }

    private fun initAvatar(data: User) {
        var tmp = ""
        if (data.username != null && data.username!!.isNotEmpty()) {
            tmp = data.username!![0].toString()
        }
        if (data.surname != null && data.surname!!.isNotEmpty()) {
            tmp += data.surname!![0].toString()
        }
        if (tmp.isEmpty()) {
            tmp = data.phoneNumber.substring(0, 2)
        }

        avatar.postValue(tmp)
    }

    private fun initDate(date: Date?) {
        if (date != null) {
            val tz = TimeZone.getDefault();

            val dateFormat =
                SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            dateFormat.format(date)
            dateFormat.timeZone = tz

            dateFormatted.postValue("с " + dateFormat.format(date))
        }
    }

    val openSplashActivity = MutableLiveData<Event<Unit>>()

    fun onSignOutBtnClick() {
        profileRepository.setAuthorized(false)
        profileRepository.setPhoneNumber("")
        profileRepository.setCode(0)
        openSplashActivity.postValue(Event(Unit))
    }
}
