package tj.rs.pharmacyonline.data.mypurchases

import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.modules.NetManager

/**
 * Created by Rustam Safarov (RS) on 16.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class MyPurchasesRepository(val netManager: NetManager) {
    val remoteDataSource = MyPurchasesRemoteDataSource.instance

    fun getMyPurchases(onMyPurchasesReadyCallback: OnMyPurchasesReadyCallback) {
        netManager.isConnectedToInternet?.let {
            if (it) {
                remoteDataSource.getMyPurchases(object : OnMyPurchasesRemoteReadyCallback {
                    override fun onRemoteDataReadyCallback(arrayList: ArrayList<Medicine>) {
                        onMyPurchasesReadyCallback.onDataReady(arrayList)
                    }
                })
            }
        }
    }

    interface OnMyPurchasesReadyCallback {
        fun onDataReady(data: ArrayList<Medicine>)
    }
}