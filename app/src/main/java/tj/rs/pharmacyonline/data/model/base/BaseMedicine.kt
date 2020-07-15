package tj.rs.pharmacyonline.data.model.base

import androidx.room.PrimaryKey

/**
 * Created by Rustam Safarov (RS) on 15.07.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
open class BaseMedicine(
    @PrimaryKey
    val id: Int,
    val name: String,
    val desc: String,
    val img: String,
    var isFavorite: Boolean
)