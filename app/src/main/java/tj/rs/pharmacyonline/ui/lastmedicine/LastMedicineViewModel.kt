package tj.rs.pharmacyonline.ui.lastmedicine

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import tj.rs.pharmacyonline.App
import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.data.preferences.Preferences
import tj.rs.pharmacyonline.data.repository.lastmedicine.MedicineRepository
import tj.rs.pharmacyonline.data.repository.profile.ProfileRepository
import tj.rs.pharmacyonline.modules.NetManager
import tj.rs.pharmacyonline.utils.live_data.Event

/**
 * Created by Rustam Safarov (RS) on 06.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class LastMedicineViewModel(application: App) : AndroidViewModel(application) {
    val netManager = NetManager(getApplication())
    val lastMedicineRepository = MedicineRepository(netManager, App.getDatabase())
    val isLoading = MutableLiveData<Boolean>()
    val repository = MutableLiveData<ArrayList<Medicine>>()
    val authRepository = ProfileRepository(Preferences(application))

    fun loadLastMedicine() {
        isLoading.postValue(true)
        lastMedicineRepository.getLastMedicines(
            authRepository.getPhoneNumber(),
            authRepository.getCode(),
            object :
                MedicineRepository.OnLastMedicineReadyCallback {
                override fun onDataReady(data: ArrayList<Medicine>) {
                    Log.i("LastMedicineViewModel", "getLastMedicine: onDataReady")
                    isLoading.postValue(false)
                    repository.value = data
                }
            })
    }

    val notifyItemChanged = MutableLiveData<Event<Int>>()

    fun isFavorite(medicine: Medicine) {
        medicine.isFavorite = !medicine.isFavorite
        for (i in 0 until repository.value!!.size) {
            if (medicine === repository.value!![i]) {
                notifyItemChanged.postValue(Event(i))
                break
            }
        }


        lastMedicineRepository.isFavorite(medicine)
    }

}