package tj.rs.pharmacyonline.data.lastmedicine

import android.util.Log
import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.modules.NetManager

/**
 * Created by Rustam Safarov (RS) on 06.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class LastMedicineRepository(val netManager: NetManager) {
    val localDataSource = LastMedicineLocalDataSource()
    val remoteDataSource = LastMedicineRemoteDataSource()

    fun getLastMedicines(onLastMedicineReadyCallback: OnLastMedicineReadyCallback) {
        netManager.isConnectedToInternet?.let {
            if (it) {
                remoteDataSource.getLastMedicines(object : OnLastMedicineRemoteReadyCallback {
                    override fun onRemoteDataReadyCallback(data: ArrayList<Medicine>) {
                        onLastMedicineReadyCallback.onDataReady(data)
                        localDataSource.saveLastMedicines(data)
                        Log.i("LastMedicineRepository", "getLastMedicines: onDataReadyRemoteDataSource loaded")

                    }
                })
            } else {
                localDataSource.getLastMedicine(object : OnLastMedicineLocalDataReadyCallback {
                    override fun onLocalDataReadyCallback(data: ArrayList<Medicine>) {
                        onLastMedicineReadyCallback.onDataReady(data)
                        Log.i("LastMedicineRepository", "getLastMedicines: onDataReadyLocalDataSource loaded")

                    }
                })
            }
        }

    }

    interface OnLastMedicineReadyCallback {
        fun onDataReady(data: ArrayList<Medicine>)
    }
}
