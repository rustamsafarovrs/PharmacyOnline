package tj.rs.pharmacyonline.data.lastmedicine

import android.os.Handler
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.rs.pharmacyonline.data.model.LastMedicines
import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.modules.NetworkService

/**
 * Created by Rustam Safarov (RS) on 06.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */

class MedicineRemoteDataSource private constructor() {

    companion object {
        val instance = MedicineRemoteDataSource()
    }

    private val arrayList = ArrayList<Medicine>()
    fun getLastMedicines(onMedicineRemoteReadyCallback: OnLastMedicineRemoteReadyCallback) {
        arrayList.clear()
        NetworkService.instance().last.enqueue(object : Callback<LastMedicines> {
            override fun onFailure(call: Call<LastMedicines>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<LastMedicines>,
                response: Response<LastMedicines>
            ) {
                Log.i("NetworkService", "onResponse called")
                response.body()?.items?.forEach {
                    if (it != null)
                        arrayList.add(it)
                }
            }
        })

        Handler().postDelayed(
            { onMedicineRemoteReadyCallback.onRemoteDataReadyCallback(arrayList) },
            2000
        )
    }

    fun getMedicine(
        id: Int,
        onMedicineRemoteReadyCallback: OnMedicineRemoteReadyCallBack
    ) {
        var data: Medicine? = null
        for (medicine in arrayList) {
            if (medicine.id == id) {
                data = medicine
            }
        }

        Handler().postDelayed(
            { onMedicineRemoteReadyCallback.onRemoteMedicineDataReadyCallback(data) },
            1000
        )
    }
}

interface OnLastMedicineRemoteReadyCallback {
    fun onRemoteDataReadyCallback(data: ArrayList<Medicine>)
}

interface OnMedicineRemoteReadyCallBack {
    fun onRemoteMedicineDataReadyCallback(data: Medicine?)
}