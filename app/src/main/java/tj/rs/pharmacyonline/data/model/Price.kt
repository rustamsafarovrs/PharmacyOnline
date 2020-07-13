package tj.rs.pharmacyonline.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Rustam Safarov (RS) on 09.07.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
@Entity
data class Price(
    @PrimaryKey
    val id: Int,
    @SerializedName("id_medicine")
    val idMedicine: Int,
    @SerializedName("id_department")
    val idDepartment: Int,
    val department: Department,
    val price: Int
)