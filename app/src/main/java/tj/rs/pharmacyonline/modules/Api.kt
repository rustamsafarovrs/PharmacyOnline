package tj.rs.pharmacyonline.modules

import retrofit2.Call
import retrofit2.http.GET
import tj.rs.pharmacyonline.data.model.Medicines

interface Api {
    @get:GET("products/last")
    val last: Call<Medicines>
}
