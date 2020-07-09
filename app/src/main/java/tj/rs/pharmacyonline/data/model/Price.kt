package tj.rs.pharmacyonline.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Rustam Safarov (RS) on 09.07.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */

data class Price(
    @SerializedName("id_medicine")
    val idMedicine: Int,
    @SerializedName("id_department")
    val idDepartment: Int,
    val price: Int
)