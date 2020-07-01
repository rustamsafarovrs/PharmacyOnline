package tj.rs.pharmacyonline.data.repository.catalogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tj.rs.pharmacyonline.data.model.CatalogPhoneCountry

/**
 * Created by Rustam Safarov (RS) on 30.06.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class CatalogsRepository {

    fun getPhoneCountriesLiveData(): LiveData<List<CatalogPhoneCountry>> {
        val liveData = MutableLiveData<List<CatalogPhoneCountry>>()

        val list =
            listOf(
                CatalogPhoneCountry("tj", "+992"),
                CatalogPhoneCountry("ru", "+7"),
                CatalogPhoneCountry("us", "+1")
            )
        liveData.postValue(list)

        return liveData
    }

    fun getPhoneCountries(): List<CatalogPhoneCountry> {

        return listOf(
            CatalogPhoneCountry("tj", "+992"),
            CatalogPhoneCountry("ru", "+7"),
            CatalogPhoneCountry("us", "+1")
        )
    }

}