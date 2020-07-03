package tj.rs.pharmacyonline.data.repository.profile

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.rs.pharmacyonline.data.model.User
import tj.rs.pharmacyonline.data.preferences.Preferences
import tj.rs.pharmacyonline.modules.NetworkService

/**
 * @author Rustam Safarov (RS)
 * created at 13.06.2020
 */

class ProfileRepository(private val preferences: Preferences) {
    fun setAuthorized(isAuthorized: Boolean) {
        preferences.setAuthorized(isAuthorized)
    }

    fun getAuthorized() = preferences.getAuthorized()

    fun setPhoneNumber(phoneNumber: String) {
        preferences.setPhoneNumber(phoneNumber)
    }

    fun getPhoneNumber() = preferences.getPhoneNumber()

    fun setCode(code: Int) {
        preferences.setCode(code)
    }

    fun getCode() = preferences.getCode()

    fun getProfile(
        params: Map<String, String>,
        onGetProfileReadyCallback: OnGetProfileReadyCallback
    ) {
        NetworkService.profileInstance().getProfile(params).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                onGetProfileReadyCallback.onDataReady(null)
                t.printStackTrace()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.code() == 200) {
                    onGetProfileReadyCallback.onDataReady(response.body())
                } else {
                    onGetProfileReadyCallback.onDataReady(null)
                }
            }
        })
    }

    fun updateProfile(
        params: Map<String, String>,
        onUpdateProfileReadyCallback: OnUpdateProfileReadyCallback
    ) {
        NetworkService.profileInstance().updateProfile(params)
            .enqueue(object : Callback<tj.rs.pharmacyonline.data.model.Response> {
                override fun onFailure(
                    call: Call<tj.rs.pharmacyonline.data.model.Response>,
                    t: Throwable
                ) {
                    onUpdateProfileReadyCallback.onUpdateReady(
                        tj.rs.pharmacyonline.data.model.Response().apply {
                            message = t.message.toString()
                            responseCode = 0
                        })

                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<tj.rs.pharmacyonline.data.model.Response>,
                    retrofitResponse: Response<tj.rs.pharmacyonline.data.model.Response>
                ) {
                    onUpdateProfileReadyCallback.onUpdateReady(
                        tj.rs.pharmacyonline.data.model.Response().apply {
                            responseCode = retrofitResponse.code()
                            message = retrofitResponse.message()
                        })
                }
            })
    }


    interface OnGetProfileReadyCallback {
        fun onDataReady(data: User?)
    }

    interface OnUpdateProfileReadyCallback {
        fun onUpdateReady(response: tj.rs.pharmacyonline.data.model.Response)
    }
}