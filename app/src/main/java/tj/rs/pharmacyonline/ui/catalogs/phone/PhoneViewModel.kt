package tj.rs.pharmacyonline.ui.catalogs.phone

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tj.rs.pharmacyonline.data.model.CatalogPhoneCountry
import tj.rs.pharmacyonline.data.repository.catalogs.CatalogsRepository
import tj.rs.pharmacyonline.utils.live_data.Event

/**
 * Created by Rustam Safarov (RS) on 30.06.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */

class PhoneViewModel(app: Application) :
    AndroidViewModel(app) {

    private val repository = CatalogsRepository()

    val catalogPhoneCountry = MutableLiveData<CatalogPhoneCountry>()

    init {
        catalogPhoneCountry.postValue(CatalogPhoneCountry(domain = "tj", code = "+992"))
    }

    private val _showSelectCodeCatalog = MutableLiveData<Event<List<CatalogPhoneCountry>>>()
    val showSelectCodeCatalog: LiveData<Event<List<CatalogPhoneCountry>>>
        get() = _showSelectCodeCatalog

    fun onCodeBtnClick() {
        val data = repository.getPhoneCountries()
        _showSelectCodeCatalog.postValue(Event(data))
    }

    fun onCodeSelected(code: CatalogPhoneCountry) {
        catalogPhoneCountry.postValue(code)
    }

}