package tj.rs.pharmacyonline.ui.banner

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import tj.rs.pharmacyonline.data.banner.BannerRepository
import tj.rs.pharmacyonline.data.model.Banner
import tj.rs.pharmacyonline.modules.NetworkService

class BannerViewModel : ViewModel() {

    val bannerRepository = BannerRepository()
    val isLoading = MutableLiveData<Boolean>()
    val banner = MutableLiveData(Banner(0, false))
    val bitmap = MutableLiveData<Bitmap>()

    init {
        getBanner()
    }

    fun getBanner() {
        isLoading.postValue(true)
        bannerRepository.getBanner(object : BannerRepository.OnBannerReadyCallback {
            override fun onBannerReady(data: Banner) {
                banner.postValue(data)

                val url = NetworkService.BASE_URL + "banner/" + data.imageUrl
                Picasso.get().load(url).into(object : Target {
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                        //do nothing
                    }

                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                        //do nothing
                        e?.printStackTrace()
                    }

                    override fun onBitmapLoaded(_bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        if (_bitmap != null) {
                            bitmap.postValue(_bitmap)
                            isLoading.postValue(false)
                        }
                    }
                })

            }
        })

    }
}