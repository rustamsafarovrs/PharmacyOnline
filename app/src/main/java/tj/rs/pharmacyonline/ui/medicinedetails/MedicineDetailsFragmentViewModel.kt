package tj.rs.pharmacyonline.ui.medicinedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tj.rs.pharmacyonline.data.model.Medicine

/**
 * Created by Rustam Safarov (RS) on 08.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class MedicineDetailsFragmentViewModel : ViewModel() {
    val id = MutableLiveData<Int>()

    fun setArgs(id: Int) {
        this.id.postValue(id)
    }
}