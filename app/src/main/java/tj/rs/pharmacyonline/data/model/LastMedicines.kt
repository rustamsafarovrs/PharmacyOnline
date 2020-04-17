package tj.rs.pharmacyonline.data.model

import com.google.gson.annotations.SerializedName

data class LastMedicines(
    @SerializedName("last")
    var items: List<Medicine?>?
)
