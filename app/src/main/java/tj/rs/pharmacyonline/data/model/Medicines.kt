package tj.rs.pharmacyonline.data.model

import com.google.gson.annotations.SerializedName

data class Medicines(
    @SerializedName("last")
    var items: List<Medicine?>?
)
