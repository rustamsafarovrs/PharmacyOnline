package tj.rs.pharmacyonline.data.banner

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.rs.pharmacyonline.data.model.Banner
import tj.rs.pharmacyonline.modules.NetworkService

/**
 * @author Rustam Safarov (RS)
 * created at 16.06.2020
 */

class BannerRepository() {

    fun getBanner(onBannerReadyCallback: OnBannerReadyCallback) {
        NetworkService.instance().banner.enqueue(object : Callback<Banner> {
            override fun onFailure(call: Call<Banner>, t: Throwable) {
                onBannerReadyCallback.onBannerReady(Banner(0, false))
            }

            override fun onResponse(call: Call<Banner>, response: Response<Banner>) {
                if (response.body() == null) {
                    onBannerReadyCallback.onBannerReady(Banner(0, false))
                } else {
                    onBannerReadyCallback.onBannerReady(response.body()!!)
                }
            }
        })
    }

    interface OnBannerReadyCallback {
        fun onBannerReady(data: Banner)
    }
}