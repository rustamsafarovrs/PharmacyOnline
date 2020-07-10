package tj.rs.pharmacyonline.ui.medicinedetails

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.data.preferences.Preferences
import tj.rs.pharmacyonline.data.repository.department.DepartmentRepository
import tj.rs.pharmacyonline.data.repository.lastmedicine.MedicineRepository
import tj.rs.pharmacyonline.data.repository.profile.ProfileRepository
import tj.rs.pharmacyonline.modules.NetManager

/**
 * Created by Rustam Safarov (RS) on 08.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class MedicineDetailsFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val medicineRepository = MedicineRepository(NetManager(getApplication()))
    val isLoading = ObservableField<Boolean>()
    val medicine = MutableLiveData<Medicine>()
    val departmentRepository =
        DepartmentRepository.instance(ProfileRepository(Preferences(application)))

    fun setArgs(id: Int) {
        loadMedicine(id)
    }

    private fun loadMedicine(id: Int) {
        Log.i("MedicineDetails", "loading medicine by id: $id")
        isLoading.set(true)
        medicineRepository.getMedicine(id, object : MedicineRepository.OnMedicineReadyCallback {
            override fun onMedicineDataReady(data: Medicine?) {
                isLoading.set(false)
                medicine.value = data
            }
        })
    }

    fun onFavoriteBtnClick() {

    }

}