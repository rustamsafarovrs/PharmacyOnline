package tj.rs.pharmacyonline.modules

import retrofit2.Call
import retrofit2.http.GET
import tj.rs.pharmacyonline.data.model.LastMedicines
import tj.rs.pharmacyonline.data.model.Purchases

interface Api {
    @get:GET("products/last")
    val last: Call<LastMedicines>
    @get:GET("products/purchases")
    val purchases: Call<Purchases>
}
