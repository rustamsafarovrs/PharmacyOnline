package tj.rs.pharmacyonline.ui.registration

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import tj.rs.pharmacyonline.data.model.Response
import tj.rs.pharmacyonline.data.preferences.Preferences
import tj.rs.pharmacyonline.data.signup.AuthRepository
import tj.rs.pharmacyonline.data.signup.SignupRepository
import tj.rs.pharmacyonline.modules.NetManager
import tj.rs.pharmacyonline.utils.event.Emitter

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {

    val emitter = Emitter()
    val domain = MutableLiveData<Int>()
    var formattedPhone = ""
    val phoneFieldText = MutableLiveData<String>()
    val errorPhoneField = MutableLiveData<String>()
    val requestSmsFieldText = MutableLiveData<String>()
    val signupRepository = SignupRepository()
    val authRepository = AuthRepository(Preferences(getApplication()))
    val isLoading = MutableLiveData<Boolean>()

    init {
        domain.postValue(992)
    }

    fun requestSmsCode() {
        isLoading.postValue(true)
        if (NetManager(getApplication()).isConnectedToInternet!!) {
            if (phoneFieldText.value != null || phoneFieldText.value == "") {
                signupRepository.requestSmsCode(
                    mapOf("phone_number" to phoneFieldText.value!!),
                    object : SignupRepository.OnRequestSmsCodeReadyCallback {
                        override fun onRequestDone(response: Response) {
                            isLoading.postValue(false)
                            if (response.responseCode == 201) {
                                emitter.emitAndExecute(RegistrationFragmentNavigation.OpenConfirmFragment())
                            } else {
                                emitter.emitAndExecute(RegistrationFragmentNavigation.ShowNetworkError())
                            }
                        }
                    })
            } else {
                isLoading.postValue(false)
                emitter.emitAndExecute(RegistrationFragmentNavigation.PhoneError())
                errorPhoneField.postValue("Phone number =null")
            }
        } else {
            emitter.emitAndExecute(RegistrationFragmentNavigation.ShowNetworkError())
            isLoading.postValue(false)
        }
    }

    fun confirmPhone() {
        isLoading.postValue(true)
        if (NetManager(getApplication()).isConnectedToInternet!!) {
            signupRepository.confirmPhone(
                mapOf(
                    "phone_number" to phoneFieldText.value!!,
                    "generated_code" to requestSmsFieldText.value!!
                ), object : SignupRepository.OnConfirmPhoneReadyCallback {
                    override fun onConfirmDone(response: Response) {
                        isLoading.postValue(false)
                        when (response.responseCode) {
                            202 -> {
                                emitter.emitAndExecute(RegistrationFragmentNavigation.OpenAuthorizedActivity())
                                authenticated()
                            }
                            406 -> {
                                emitter.emitAndExecute(RegistrationFragmentNavigation.SMSCodeError())
                            }
                            else -> {
                                emitter.emitAndExecute(RegistrationFragmentNavigation.ShowNetworkError())
                            }
                        }
                    }
                }
            )
        } else {
            emitter.emitAndExecute(RegistrationFragmentNavigation.ShowNetworkError())
            isLoading.postValue(false)
        }
    }

    private fun authenticated() {
        authRepository.setAuthorized(true)
        authRepository.setPhoneNumber(phoneFieldText.value!!)
    }

    fun getFormattedPhoneNumber(): String {
        return "+${domain.value.toString()} $formattedPhone"
    }


}