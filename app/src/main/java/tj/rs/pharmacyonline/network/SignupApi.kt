package tj.rs.pharmacyonline.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import tj.rs.pharmacyonline.data.model.Response

/**
 * @author Rustam Safarov (RS)
 * created at 09.06.2020
 */
interface SignupApi {

    @POST("api/signup/request_sms_code.php")
    fun requestSMSCode(@Body params: Map<String, String>): Call<Response>

    @POST("api/signup/check_sms_code.php")
    fun checkSMSCode(@Body params: Map<String, String>): Call<Response>

}