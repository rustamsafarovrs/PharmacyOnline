package tj.rs.pharmacyonline.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Rustam Safarov (RS) on 06.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
data class Medicine(
    val id: Int,
    val name: String,
    val price: Int,
    @SerializedName("description")
    val desc: String,
    val img: String,
    val isFavorite: Boolean = false
)