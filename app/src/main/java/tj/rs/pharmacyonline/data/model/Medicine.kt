package tj.rs.pharmacyonline.data.model

/**
 * Created by Rustam Safarov (RS) on 06.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
data class Medicine(
    val id: Int,
    val name: String,
    val price: Int,
    val desc: String,
    val img: String,
    val isFavorite: Boolean = false
)