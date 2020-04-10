package tj.rs.pharmacyonline.data.lastmedicine

import android.util.Log
import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.modules.NetManager

/**
 * Created by Rustam Safarov (RS) on 06.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class MedicineRepository(val netManager: NetManager) {
    val localDataSource = MedicineLocalDataSource.instance
    val remoteDataSource = MedicineRemoteDataSource.instance


    fun getLastMedicines(onLastMedicineReadyCallback: OnLastMedicineReadyCallback) {
        netManager.isConnectedToInternet?.let {
            if (it) {
                remoteDataSource.getLastMedicines(object : OnLastMedicineRemoteReadyCallback {
                    override fun onRemoteDataReadyCallback(data: ArrayList<Medicine>) {
                        onLastMedicineReadyCallback.onDataReady(data)
                        localDataSource.saveLastMedicines(data)
                        Log.i(
                            "MedicineRepository",
                            "getLastMedicines: onDataReadyRemoteDataSource loaded"
                        )

                    }
                })
            } else {
                localDataSource.getLastMedicine(object : OnLastMedicineLocalDataReadyCallback {
                    override fun onLocalDataReadyCallback(data: ArrayList<Medicine>) {
                        onLastMedicineReadyCallback.onDataReady(data)
                        Log.i(
                            "MedicineRepository",
                            "getLastMedicines: onDataReadyLocalDataSource loaded"
                        )

                    }
                })
            }
        }

    }

    fun getMedicine(id: Int, onMedicineReadyCallback: OnMedicineReadyCallback) {
        netManager.isConnectedToInternet?.let {
            if (it) {
                remoteDataSource.getMedicine(id, object : OnMedicineRemoteReadyCallBack {
                    override fun onRemoteMedicineDataReadyCallback(data: Medicine?) {
                        onMedicineReadyCallback.onMedicineDataReady(data)
                    }
                })
            } else {
                localDataSource.getMedicine(id, object : OnMedicineLocalDataReadyCallback {
                    override fun onLocalMedicineReadyCallback(data: Medicine?) {
                        onMedicineReadyCallback.onMedicineDataReady(data)
                    }
                })
            }
        }
    }

    interface OnLastMedicineReadyCallback {
        fun onDataReady(data: ArrayList<Medicine>)
    }

    interface OnMedicineReadyCallback {
        fun onMedicineDataReady(data: Medicine?)
    }
}
