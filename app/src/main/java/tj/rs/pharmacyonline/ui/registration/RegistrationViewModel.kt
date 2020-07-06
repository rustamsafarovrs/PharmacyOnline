package tj.rs.pharmacyonline.ui.registration

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import tj.rs.pharmacyonline.data.const.UserParams
import tj.rs.pharmacyonline.data.model.Response
import tj.rs.pharmacyonline.data.preferences.Preferences
import tj.rs.pharmacyonline.data.repository.profile.ProfileRepository
import tj.rs.pharmacyonline.data.repository.signup.SignupRepository
import tj.rs.pharmacyonline.modules.NetManager
import tj.rs.pharmacyonline.utils.live_data.Event

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {

    val code = MutableLiveData<Int>()
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

    val resendIsEnabled = MutableLiveData<Boolean>()
    val resendCountdown = MutableLiveData<Int>()

    init {
        code.postValue(992)
    }

    fun requestSmsCode() {

        isLoading.postValue(true)
        if (NetManager(getApplication()).isConnectedToInternet()) {
            if (phoneFieldText.value != null || phoneFieldText.value == "") {
                signupRepository.requestSmsCode(
                    mapOf(
                        UserParams.PHONE_NUMBER to phoneFieldText.value!!,
                        UserParams.CODE to code.value.toString()
                    ),
                    object : SignupRepository.OnRequestSmsCodeReadyCallback {
                        override fun onRequestDone(response: Response) {
                            isLoading.postValue(false)
                            if (response.responseCode == 201) {
                                openConfirmFragment.postValue(Event(Unit))
                                authRepository.setPhoneNumber(phoneFieldText.value!!)
                                authRepository.setCode(code.value!!)
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

        initResendSMSTimer()
    }

    var countDownTimer: CountDownTimer? = null

    private fun initResendSMSTimer() {
        resendIsEnabled.postValue(false)
        countDownTimer?.cancel()

        countDownTimer = object : CountDownTimer(25000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                resendCountdown.postValue((millisUntilFinished / 1000).toInt())
            }

            override fun onFinish() {
                resendIsEnabled.postValue(true)
            }
        }
        countDownTimer?.start()
    }

    val openConfirmFragment = MutableLiveData<Event<Unit>>()
    val showError = MutableLiveData<Event<String>>()
    val phoneError = MutableLiveData<Event<Unit>>()


    fun confirmPhone() {
        isLoading.postValue(true)
        if (NetManager(getApplication()).isConnectedToInternet()) {
            signupRepository.confirmPhone(
                mapOf(
                    UserParams.PHONE_NUMBER to phoneFieldText.value!!,
                    "generated_code" to requestSmsFieldText.value!!,
                    UserParams.CODE to code.value.toString()
                ), object : SignupRepository.OnConfirmPhoneReadyCallback {
                    override fun onConfirmDone(response: Response) {
                        isLoading.postValue(false)
                        when (response.responseCode) {
                            202 -> {
                                openPostProfileFragment.postValue(Event(Unit))
                                savePhoneNumber()
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

    val openPostProfileFragment = MutableLiveData<Event<Unit>>()
    val smsCodeError = MutableLiveData<Event<Unit>>()

    private fun savePhoneNumber() {
        authRepository.setPhoneNumber(phoneFieldText.value!!)
        authRepository.setCode(code.value!!)
    }

    fun getFormattedPhoneNumber(): String {
        return "+${code.value.toString()} $formattedPhone"
    }


}