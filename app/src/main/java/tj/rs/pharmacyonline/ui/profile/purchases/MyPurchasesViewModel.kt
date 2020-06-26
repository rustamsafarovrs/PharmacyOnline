package tj.rs.pharmacyonline.ui.profile.purchases

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.data.repository.mypurchases.MyPurchasesRepository
import tj.rs.pharmacyonline.modules.NetManager

class MyPurchasesViewModel(application: Application) : AndroidViewModel(application) {
    val netManager = NetManager(getApplication())
    val myPurchasesRepository = MyPurchasesRepository(netManager)
    val isLoading = MutableLiveData<Boolean>()
    val noInternetConnection = MutableLiveData<Boolean>()
    val repository = MutableLiveData<ArrayList<Medicine>>()

    init {
        loadMyPurchases()
    }

    fun loadMyPurchases() {
        if (!netManager.isConnectedToInternet()) {
            noInternetConnection.postValue(true)
        } else {
            isLoading.postValue(true)
            myPurchasesRepository.getMyPurchases(object :
                MyPurchasesRepository.OnMyPurchasesReadyCallback {
                override fun onDataReady(data: ArrayList<Medicine>) {
                    isLoading.postValue(false)
                    repository.postValue(data)
                }
            })
        }
    }
}
