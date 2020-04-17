package tj.rs.pharmacyonline.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Rustam Safarov (RS) on 16.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
data class Purchases(
    @SerializedName("purchases")
    var items: List<Medicine?>?
)