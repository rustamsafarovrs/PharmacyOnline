package tj.rs.pharmacyonline.data.model

/**
 * Created by Rustam Safarov (RS) on 14.07.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */

data class MedicineCart(
    val id: Int,
    val name: String,
    val price: Price,
    val desc: String,
    val img: String,
    var isFavorite: Boolean
)