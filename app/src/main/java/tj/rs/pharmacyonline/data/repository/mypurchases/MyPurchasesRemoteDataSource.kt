package tj.rs.pharmacyonline.data.repository.mypurchases

import android.os.Handler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.data.model.Purchases
import tj.rs.pharmacyonline.modules.NetworkService

/**
 * Created by Rustam Safarov (RS) on 16.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class MyPurchasesRemoteDataSource private constructor() {

    companion object {
        val instance = MyPurchasesRemoteDataSource()
    }

    private val myPurchases = ArrayList<Medicine>()

    fun getMyPurchases(onMyPurchasesRemoteReadyCallback: OnMyPurchasesRemoteReadyCallback) {
        myPurchases.clear()
        NetworkService.instance().purchases().enqueue(object : Callback<Purchases> {
            override fun onFailure(call: Call<Purchases>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Purchases>, response: Response<Purchases>) {
                response.body()?.items?.forEach {
                    if (it != null)
                        myPurchases.add(it)
                }
            }
        })

        Handler().postDelayed({
            onMyPurchasesRemoteReadyCallback.onRemoteDataReadyCallback(myPurchases)
        }, 1200)
    }

}

interface OnMyPurchasesRemoteReadyCallback {
    fun onRemoteDataReadyCallback(arrayList: ArrayList<Medicine>)
}