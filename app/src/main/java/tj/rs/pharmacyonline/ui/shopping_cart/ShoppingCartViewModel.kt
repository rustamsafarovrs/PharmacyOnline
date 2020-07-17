package tj.rs.pharmacyonline.ui.shopping_cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.data.model.MedicineCart
import tj.rs.pharmacyonline.data.model.Price
import tj.rs.pharmacyonline.data.repository.shopping_cart.ShoppingCartRepository
import tj.rs.pharmacyonline.ui_commons.RecyclerViewItemClickCallback
import tj.rs.pharmacyonline.utils.live_data.Event

/**
 * Created by Rustam Safarov (RS) on 14.07.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class ShoppingCartViewModel : ViewModel() {

    val shoppingCartRepository = ShoppingCartRepository.getInstance()

    val list = shoppingCartRepository.list

    fun add(medicine: Medicine, price: Price) {
        shoppingCartRepository.add(medicine, price)
    }

    val openShoppingCardFragment = MutableLiveData<Event<Unit>>()

    fun openShoppingCart() {
        openShoppingCardFragment.postValue(Event(Unit))
    }

    fun deleteLastItem() {
        list.removeAt(list.size - 1)
    }

    val sum = shoppingCartRepository.sum

    val notifyItemRemoved = MutableLiveData<Event<Int>>()

    val listener = object : RecyclerViewItemClickCallback {
        override fun onRecyclerViewItemClick(any: Any) {
            //do nothing
        }

        override fun onRecyclerViewItemRemoveClick(any: Any) {
            val position = shoppingCartRepository.remove(any as MedicineCart)
            notifyItemRemoved.postValue(Event(position))
        }
    }

    val openAddressFragment = MutableLiveData<Event<Unit>>()

    fun onBuyBtnClick() {
        openAddressFragment.postValue(Event(Unit))
    }

}