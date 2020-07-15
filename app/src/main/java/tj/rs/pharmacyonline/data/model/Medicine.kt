package tj.rs.pharmacyonline.data.model

import androidx.room.Entity
import tj.rs.pharmacyonline.data.model.base.BaseMedicine

/**
 * Created by Rustam Safarov (RS) on 06.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
@Entity
class Medicine(
    id: Int,
    name: String,
    val prices: List<Price>,
    desc: String,
    img: String,
    isFavorite: Boolean
) : BaseMedicine(id, name, desc, img, isFavorite)