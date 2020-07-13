package tj.rs.pharmacyonline.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Rustam Safarov (RS) on 06.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
@Entity
data class Medicine(
    @PrimaryKey
    val id: Int,
    val name: String,
    val prices: List<Price>,
    val desc: String,
    val img: String,
    val isFavorite: Boolean = false
)