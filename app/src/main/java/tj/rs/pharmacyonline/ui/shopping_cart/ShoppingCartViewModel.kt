package tj.rs.pharmacyonline.ui.shopping_cart

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.data.model.MedicineCart
import tj.rs.pharmacyonline.data.model.Price
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
    }

    val openShoppingCardFragment = MutableLiveData<Event<Unit>>()

    fun openShoppingCart() {
        openShoppingCardFragment.postValue(Event(Unit))
    }

    fun deleteLastItem() {
        list.removeAt(list.size - 1)
    }

}