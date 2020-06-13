package tj.rs.pharmacyonline.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import tj.rs.pharmacyonline.data.model.Response

/**
 * @author Rustam Safarov (RS)
 * created at 09.06.2020
 */
interface ProfileApi {

    @POST("profile/profile.php")
    fun profile(@Body params: Map<String, String>): Call<Response>
}