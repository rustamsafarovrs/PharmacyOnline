package tj.rs.pharmacyonline.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap
import tj.rs.pharmacyonline.data.model.Response
import tj.rs.pharmacyonline.data.model.User

/**
 * @author Rustam Safarov (RS)
 * created at 09.06.2020
 */
interface ProfileApi {

    @POST("api/profile/update_profile.php")
    fun updateProfile(@Body params: Map<String, String>): Call<Response>

    @GET("api/profile/get_profile.php")
    fun getProfile(@QueryMap params: Map<String, String>): Call<User>

}