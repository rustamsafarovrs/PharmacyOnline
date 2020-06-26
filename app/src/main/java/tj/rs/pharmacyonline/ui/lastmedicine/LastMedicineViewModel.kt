package tj.rs.pharmacyonline.ui.lastmedicine

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.data.repository.lastmedicine.MedicineRepository
import tj.rs.pharmacyonline.modules.NetManager

/**
 * Created by Rustam Safarov (RS) on 06.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class LastMedicineViewModel(application: Application) : AndroidViewModel(application) {
    val netManager = NetManager(getApplication())
    val lastMedicineRepository = MedicineRepository(netManager)
    val isLoading = MutableLiveData<Boolean>()
    val repository = MutableLiveData<ArrayList<Medicine>>()

    init {
        loadLastMedicine()
    }

    fun loadLastMedicine() {
        isLoading.postValue(true)
        lastMedicineRepository.getLastMedicines(object :
            MedicineRepository.OnLastMedicineReadyCallback {
            override fun onDataReady(data: ArrayList<Medicine>) {
                Log.i("LastMedicineViewModel", "getLastMedicine: onDataReady")
                isLoading.postValue(false)
                repository.value = data
            }
        })
    }

}