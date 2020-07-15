package tj.rs.pharmacyonline.ui.catalogs.select

import android.app.Application
import android.os.Parcelable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tj.rs.pharmacyonline.data.model.CatalogPhoneCountry
import tj.rs.pharmacyonline.ui_commons.RecyclerViewItemClickCallback
import tj.rs.pharmacyonline.utils.live_data.Event

/**
 * Created by Rustam Safarov (RS) on 30.06.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */

class SelectCatalogViewModel constructor(app: Application) : AndroidViewModel(app) {

    private val _list = MutableLiveData<List<Any>>()
    val list: LiveData<List<Any>>
        get() = _list

    fun setList(list: List<Any>) {
        val temp = mutableListOf<Any>()

        if (!list.isNullOrEmpty()) {
            temp.addAll(list)
        }

        _list.postValue(temp)
    }

    private val _dismiss = MutableLiveData<Event<Unit>>()
    val dismiss: LiveData<Event<Unit>>
        get() = _dismiss

    fun onCloseBtnClick() {
        _dismiss.postValue(Event(Unit))
    }

    private val _setResult = MutableLiveData<Event<Parcelable>>()
    val setResult: LiveData<Event<Parcelable>>
        get() = _setResult

    val onRecyclerViewItemClickListener = object : RecyclerViewItemClickCallback {

        override fun onRecyclerViewItemClick(any: Any) {
            when (any) {
                is CatalogPhoneCountry -> {
                    _setResult.postValue(Event(any))
                    _dismiss.postValue(Event(Unit))
                }
            }
        }

        override fun onRecyclerViewItemRemoveClick(any: Any) {
            // do nothing
        }
    }

}