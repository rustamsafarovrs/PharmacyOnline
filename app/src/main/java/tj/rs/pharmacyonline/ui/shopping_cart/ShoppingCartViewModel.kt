package tj.rs.pharmacyonline.ui.shopping_cart

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.data.model.MedicineCart
import tj.rs.pharmacyonline.data.model.Price
import tj.rs.pharmacyonline.ui_commons.RecyclerViewItemClickCallback
import tj.rs.pharmacyonline.utils.live_data.Event

/**
 * Created by Rustam Safarov (RS) on 14.07.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class ShoppingCartViewModel : ViewModel() {

    val list = ObservableArrayList<MedicineCart>()

    fun add(medicine: Medicine, price: Price) {
        val newItem = MedicineCart(
            medicine.id,
            medicine.name,
            price,
            medicine.desc,
            medicine.img,
            medicine.isFavorite
        )
        list.add(newItem)
        initBla()
    }

    private fun initBla() {
        var summ = 0

        list.forEach {
            summ = summ.plus(it.price.price)
        }

        bla.postValue(summ)
    }

    val openShoppingCardFragment = MutableLiveData<Event<Unit>>()

    fun openShoppingCart() {
        openShoppingCardFragment.postValue(Event(Unit))
    }

    fun deleteLastItem() {
        list.removeAt(list.size - 1)
    }

    val bla = MutableLiveData(0)

    val notifyDataChanged = MutableLiveData<Event<Unit>>()

    val listener = object : RecyclerViewItemClickCallback {
        override fun onRecyclerViewItemClick(any: Any) {
            //do nothing
        }

        override fun onRecyclerViewItemRemoveClick(any: Any) {
            list.remove(any as MedicineCart)
            notifyDataChanged.postValue(Event(Unit))
        }
    }

}