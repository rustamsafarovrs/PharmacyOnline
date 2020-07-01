package tj.rs.pharmacyonline.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Rustam Safarov (RS) on 30.06.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */

@Parcelize
data class CatalogPhoneCountry(
    val domain: String?,
    val code: String?
) : Parcelable
