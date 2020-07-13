package tj.rs.pharmacyonline.data.repository.lastmedicine

import android.os.Handler
import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.db.AppDatabase

/**
 * Created by Rustam Safarov (RS) on 06.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class MedicineLocalDataSource private constructor(private val appDatabase: AppDatabase) {

    companion object {
        fun getInstance(appDatabase: AppDatabase): MedicineLocalDataSource {
            if (instance == null) {
                instance = MedicineLocalDataSource(appDatabase)
            }
            return instance!!
        }

        private var instance: MedicineLocalDataSource? = null
    }

    var arrayList = ArrayList<Medicine>()
    fun getLastMedicine(onLastMedicineLocalDataReadyCallback: OnLastMedicineLocalDataReadyCallback) {
        arrayList.clear()

        arrayList.addAll(appDatabase.pharmacyDao().getAll())

        Handler().postDelayed(
            { onLastMedicineLocalDataReadyCallback.onLocalDataReadyCallback(arrayList) },
            1000
        )
    }

    fun saveLastMedicines(data: ArrayList<Medicine>) {
        appDatabase.pharmacyDao().insert(data)
    }

    fun getMedicine(id: Int, onMedicineLocalDataReadyCallback: OnMedicineLocalDataReadyCallback) {
        var data: Medicine? = null
        for (medicine in arrayList) {
            if (medicine.id == id) {
                data = medicine
            }
        }
        Handler().postDelayed(
            { onMedicineLocalDataReadyCallback.onLocalMedicineReadyCallback(data) },
            600
        )
    }
}

interface OnLastMedicineLocalDataReadyCallback {
    fun onLocalDataReadyCallback(data: ArrayList<Medicine>)
}

interface OnMedicineLocalDataReadyCallback {
    fun onLocalMedicineReadyCallback(data: Medicine?)
}