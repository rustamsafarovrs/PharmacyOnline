package tj.rs.pharmacyonline.data.repository.shopping_cart

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.data.model.MedicineCart
import tj.rs.pharmacyonline.data.model.Price

/**
 * Created by Rustam Safarov (RS) on 15.07.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class ShoppingCartRepository private constructor() {
    companion object {
        private var instance: ShoppingCartRepository? = null

        fun getInstance(): ShoppingCartRepository {
            if (instance == null) {
                instance = ShoppingCartRepository()
            }
            return instance!!
        }
    }

    val list = ObservableArrayList<MedicineCart>()

    val sum = MutableLiveData(0)

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
        calculateSum()
    }

    private fun calculateSum() {
        var tmp = 0
        list.forEach {
            tmp += it.price.price
        }

        sum.postValue(tmp)
    }

    fun remove(medicineCart: MedicineCart): Int {
        for (i in 0 until list.size) {
            if (medicineCart === list[i]) {
                list.removeAt(i)
                calculateSum()
                return i
            }
        }
        return -1
    }


}
