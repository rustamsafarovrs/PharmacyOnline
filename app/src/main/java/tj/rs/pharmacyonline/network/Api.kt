package tj.rs.pharmacyonline.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tj.rs.pharmacyonline.data.model.Banner
import tj.rs.pharmacyonline.data.model.Department
import tj.rs.pharmacyonline.data.model.LastMedicines
import tj.rs.pharmacyonline.data.model.Purchases

interface Api {
    @GET("api/products/last.php")
    fun last(
        @Query("phone_number") phoneNumber: String,
        @Query("code") code: Int
    ): Call<LastMedicines>

    @GET("purchases.json")
    fun purchases(): Call<Purchases>

    @GET("banner/banner.json")
    fun banner(): Call<Banner>

    @GET("api/department/get_department.php")
    fun getDepartment(
        @Query("id_department") id: Int,
        @Query("phone_number") phoneNumber: String,
        @Query("code") code: Int
    ): Call<Department>

}
