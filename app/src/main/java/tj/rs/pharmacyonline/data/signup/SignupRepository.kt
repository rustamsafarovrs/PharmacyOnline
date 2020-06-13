package tj.rs.pharmacyonline.data.signup

import retrofit2.Call
import retrofit2.Callback
import tj.rs.pharmacyonline.data.model.Response
import tj.rs.pharmacyonline.modules.NetworkService

/**
 * @author Rustam Safarov (RS)
 * created at 09.06.2020
 */

class SignupRepository() {

    fun requestSmsCode(
        params: Map<String, String>,
        onRequestSmsCodeReadyCallback: OnRequestSmsCodeReadyCallback
    ) {
        NetworkService.signupInstance().requestSMSCode(params).enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                onRequestSmsCodeReadyCallback.onRequestDone(
                    Response()
                        .apply {
                            message = t.message.toString()
                            responseCode = 0
                        })
            }

            override fun onResponse(
                call: Call<Response>,
                retrofitResponse: retrofit2.Response<Response>
            ) {
                if (retrofitResponse.body() != null) {
                    onRequestSmsCodeReadyCallback.onRequestDone(
                        retrofitResponse.body()!!.apply { responseCode = retrofitResponse.code() })
                } else {
                    onRequestSmsCodeReadyCallback.onRequestDone(
                        Response()
                            .apply {
                                message = "retrofitResponse = null"
                                responseCode = retrofitResponse.code()
                            })
                }
            }
        })
    }

    fun confirmPhone(
        params: Map<String, String>,
        onConfirmPhoneReadyCallback: OnConfirmPhoneReadyCallback
    ) {
        NetworkService.signupInstance().checkSMSCode(params).enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                onConfirmPhoneReadyCallback.onConfirmDone(
                    Response()
                        .apply {
                            message = t.message.toString()
                            responseCode = 0
                        })
            }

            override fun onResponse(
                call: Call<Response>,
                retrofitResponse: retrofit2.Response<Response>
            ) {
                if (retrofitResponse.body() != null) {
                    onConfirmPhoneReadyCallback.onConfirmDone(
                        retrofitResponse.body()!!.apply { responseCode = retrofitResponse.code() })
                } else {
                    onConfirmPhoneReadyCallback.onConfirmDone(
                        Response()
                            .apply {
                                message = "retrofitResponse = null"
                                responseCode = retrofitResponse.code()
                            })
                }
            }
        })
    }

    interface OnRequestSmsCodeReadyCallback {
        fun onRequestDone(response: Response)
    }

    interface OnConfirmPhoneReadyCallback {
        fun onConfirmDone(response: Response)
    }
}