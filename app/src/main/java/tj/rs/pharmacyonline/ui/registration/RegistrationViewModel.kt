package tj.rs.pharmacyonline.ui.registration

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import tj.rs.pharmacyonline.data.model.Response
import tj.rs.pharmacyonline.data.preferences.Preferences
import tj.rs.pharmacyonline.data.repository.profile.ProfileRepository
import tj.rs.pharmacyonline.data.repository.signup.SignupRepository
import tj.rs.pharmacyonline.modules.NetManager
import tj.rs.pharmacyonline.utils.live_data.Event

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {

    val domain = MutableLiveData<Int>()
    var formattedPhone = ""
    val phoneFieldText = MutableLiveData<String>()
    val errorPhoneField = MutableLiveData<String>()
    val requestSmsFieldText = MutableLiveData<String>()
    val signupRepository = SignupRepository()
    val authRepository =
        ProfileRepository(
            Preferences(getApplication())
        )
    val isLoading = MutableLiveData<Boolean>()

    init {
        domain.postValue(992)
    }

    fun requestSmsCode() {
        isLoading.postValue(true)
        if (NetManager(getApplication()).isConnectedToInternet()) {
            if (phoneFieldText.value != null || phoneFieldText.value == "") {
                signupRepository.requestSmsCode(
                    mapOf("phone_number" to phoneFieldText.value!!),
                    object : SignupRepository.OnRequestSmsCodeReadyCallback {
                        override fun onRequestDone(response: Response) {
                            isLoading.postValue(false)
                            if (response.responseCode == 201) {
                                openConfirmFragment.postValue(Event(Unit))
                            } else {
                                showError.postValue(Event(response.message))
                            }
                        }
                    })
            } else {
                isLoading.postValue(false)
                phoneError.postValue(Event(Unit))
                errorPhoneField.postValue("Phone number = null")
            }
        } else {
            showError.postValue(Event("Network Error"))
            isLoading.postValue(false)
        }
    }

    val openConfirmFragment = MutableLiveData<Event<Unit>>()
    val showError = MutableLiveData<Event<String>>()
    val phoneError = MutableLiveData<Event<Unit>>()


    fun confirmPhone() {
        isLoading.postValue(true)
        if (NetManager(getApplication()).isConnectedToInternet()) {
            signupRepository.confirmPhone(
                mapOf(
                    "phone_number" to phoneFieldText.value!!,
                    "generated_code" to requestSmsFieldText.value!!
                ), object : SignupRepository.OnConfirmPhoneReadyCallback {
                    override fun onConfirmDone(response: Response) {
                        isLoading.postValue(false)
                        when (response.responseCode) {
                            202 -> {
                                openAuthorizedActivity.postValue(Event(Unit))
                                authenticated()
                            }
                            406 -> {
                                smsCodeError.postValue(Event(Unit))
                            }
                            else -> {
                                showError.postValue(Event("Network Error"))
                            }
                        }
                    }
                }
            )
        } else {
            showError.postValue(Event("Network Error"))
            isLoading.postValue(false)
        }
    }

    val openAuthorizedActivity = MutableLiveData<Event<Unit>>()
    val smsCodeError = MutableLiveData<Event<Unit>>()

    private fun authenticated() {
        authRepository.setAuthorized(true)
        authRepository.setPhoneNumber(phoneFieldText.value!!)
    }

    fun getFormattedPhoneNumber(): String {
        return "+${domain.value.toString()} $formattedPhone"
    }


}