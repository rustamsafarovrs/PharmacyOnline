package tj.rs.pharmacyonline.data.model

import tj.rs.pharmacyonline.data.model.base.BaseMedicine

/**
 * Created by Rustam Safarov (RS) on 14.07.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */

class MedicineCart(
    id: Int,
    name: String,
    val price: Price,
    desc: String,
    img: String,
    isFavorite: Boolean
) : BaseMedicine(id, name, desc, img, isFavorite)