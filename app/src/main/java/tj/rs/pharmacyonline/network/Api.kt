package tj.rs.pharmacyonline.network

import retrofit2.Call
import retrofit2.http.GET
import tj.rs.pharmacyonline.data.model.LastMedicines
import tj.rs.pharmacyonline.data.model.Purchases

interface Api {
    @get:GET("last.json")
    val last: Call<LastMedicines>

    @get:GET("purchases.json")
    val purchases: Call<Purchases>
}
