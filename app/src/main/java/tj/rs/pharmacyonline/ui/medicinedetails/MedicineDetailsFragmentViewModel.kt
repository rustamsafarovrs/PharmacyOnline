package tj.rs.pharmacyonline.ui.medicinedetails

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tj.rs.pharmacyonline.data.lastmedicine.MedicineRepository
import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.modules.NetManager

/**
 * Created by Rustam Safarov (RS) on 08.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class MedicineDetailsFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val medicineRepository = MedicineRepository(NetManager(getApplication()))
    val isLoading = ObservableField<Boolean>()
    val medicine = MutableLiveData<Medicine>()

    fun setArgs(id: Int) {
        loadMedicine(id)
    }

    private fun loadMedicine(id: Int) {
        Log.i("MedicineDetails", "loading medicine by id: " + id)
        isLoading.set(true)
        medicineRepository.getMedicine(id, object: MedicineRepository.OnMedicineReadyCallback{
            override fun onMedicineDataReady(data: Medicine?) {
                isLoading.set(false)
                medicine.value =  data
            }
        })
    }

}