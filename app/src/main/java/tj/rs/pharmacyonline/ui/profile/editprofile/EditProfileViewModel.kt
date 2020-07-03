package tj.rs.pharmacyonline.ui.profile.editprofile

import android.app.Application
import android.os.Handler
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import tj.rs.pharmacyonline.data.const.UserParams
import tj.rs.pharmacyonline.data.model.Response
import tj.rs.pharmacyonline.data.model.User
import tj.rs.pharmacyonline.data.preferences.Preferences
import tj.rs.pharmacyonline.data.repository.profile.ProfileRepository
import tj.rs.pharmacyonline.utils.live_data.Event

class EditProfileViewModel(application: Application) : AndroidViewModel(application) {

    val profileRepository = ProfileRepository(Preferences(application))
    val user = MutableLiveData(User(phoneNumber = profileRepository.getPhoneNumber()))
    val isLoading = MutableLiveData<Boolean>()


    val surnameFieldText = MutableLiveData<String>()
    val addressFieldText = MediatorLiveData<String>()
    val emailFieldText = MutableLiveData<String>()

    init {
        loadProfile()
    }

    private fun loadProfile() {
        isLoading.postValue(true)
        profileRepository.getProfile(
            mapOf(UserParams.PHONE_NUMBER to user.value?.phoneNumber!!),
            object : ProfileRepository.OnGetProfileReadyCallback {
                override fun onDataReady(data: User?) {
                    Handler().postDelayed({ isLoading.postValue(false) }, 1000)
                    if (data != null) {
                        user.postValue(data)
                        initFields(data)
                    }
                }
            }
        )
    }

    private fun initFields(data: User) {
        usernameFieldText.postValue(data.username)
        surnameFieldText.postValue(data.surname)
        addressFieldText.postValue(data.address)
        emailFieldText.postValue(data.email)
    }

    val popBack = MutableLiveData<Event<Unit>>()

    val onUpdateErrorHandler = MutableLiveData<Event<String>>()

    fun onSaveBtnClick() {
        val usernameIsNotNull = usernameFieldText.value.isNullOrBlank()
        if (!usernameIsNotNull) {
            isLoading.postValue(true)
            profileRepository.updateProfile(
                getParams(),
                object : ProfileRepository.OnUpdateProfileReadyCallback {
                    override fun onUpdateReady(response: Response) {
                        isLoading.postValue(false)

                        if (response.responseCode == 202 || response.responseCode == 503) {
                            popBack.postValue(Event(Unit))
                            updatedEvent = true
                        } else {
                            onUpdateErrorHandler.postValue(Event(response.message))
                        }
                    }
                })
        } else {
            if (usernameIsNotNull) {
                usernameFieldError.postValue("Требутся заполнить")
            }
        }
    }

    private fun getParams(): Map<String, String> {
        return mapOf(
            UserParams.PHONE_NUMBER to user.value!!.phoneNumber,
            UserParams.USERNAME to usernameFieldText.value.toString(),
            UserParams.SURNAME to surnameFieldText.value.toString(),
            UserParams.ADDRESS to addressFieldText.value.toString(),
            UserParams.EMAIL to emailFieldText.value.toString()
        )
    }

    var updatedEvent = false

    /*
    username field
     */

    val usernameFieldText = MutableLiveData<String>()
    val usernameFieldError = MutableLiveData<String>()

    fun onUsernameFieldTextChanged() {
        if (!usernameFieldText.value.isNullOrBlank()) {
            usernameFieldError.postValue(null)
        }
    }

}
