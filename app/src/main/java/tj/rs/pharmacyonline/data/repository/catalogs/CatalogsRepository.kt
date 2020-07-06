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
                CatalogPhoneCountry("af", "+93"),
                CatalogPhoneCountry("us", "+1"),
                CatalogPhoneCountry("uz", "+998"),
                CatalogPhoneCountry("kz", "+7"),
                CatalogPhoneCountry("de", "+49"),
                CatalogPhoneCountry("ir", "+98"),
                CatalogPhoneCountry("it", "+39"),
                CatalogPhoneCountry("tr", "+90"),
                CatalogPhoneCountry("ua", "+380"),
                CatalogPhoneCountry("gb", "+44"),
                CatalogPhoneCountry("ae", "+971"),
                CatalogPhoneCountry("nl", "+31")

            )
        liveData.postValue(list)

        return liveData
    }

    fun getPhoneCountries(): List<CatalogPhoneCountry> {

        return listOf(
            CatalogPhoneCountry("tj", "+992"),
            CatalogPhoneCountry("ru", "+7"),
            CatalogPhoneCountry("af", "+93"),
            CatalogPhoneCountry("us", "+1"),
            CatalogPhoneCountry("uz", "+998"),
            CatalogPhoneCountry("kz", "+7"),
            CatalogPhoneCountry("de", "+49"),
            CatalogPhoneCountry("ir", "+98"),
            CatalogPhoneCountry("it", "+39"),
            CatalogPhoneCountry("tr", "+90"),
            CatalogPhoneCountry("ua", "+380"),
            CatalogPhoneCountry("gb", "+44"),
            CatalogPhoneCountry("ae", "+971"),
            CatalogPhoneCountry("nl", "+31")
        )
    }

}