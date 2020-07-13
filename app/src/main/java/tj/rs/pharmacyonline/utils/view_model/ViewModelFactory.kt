package tj.rs.pharmacyonline.utils.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import tj.rs.pharmacyonline.App
import tj.rs.pharmacyonline.ui.lastmedicine.LastMedicineViewModel
import tj.rs.pharmacyonline.ui.medicinedetails.MedicineDetailsFragmentViewModel


/**
 * Created by Rustam Safarov (RS) on 13.07.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class ViewModelFactory(private var application: App) :
    AndroidViewModelFactory(application as Application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LastMedicineViewModel::class.java)) {
            return LastMedicineViewModel(application) as T
        } else if (modelClass.isAssignableFrom(MedicineDetailsFragmentViewModel::class.java)) {
            return MedicineDetailsFragmentViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}