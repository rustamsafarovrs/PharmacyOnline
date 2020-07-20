package tj.rs.pharmacyonline.ui.checkout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tj.rs.pharmacyonline.utils.live_data.Event
import java.util.*

/**
 * Created by Rustam Safarov (RS) on 20.07.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class AddressFragmentViewModel : ViewModel() {
    val onBuyStep2BtnClick = MutableLiveData<Event<Unit>>()

    val minDate = Date()
    val date = Date()

    fun onBuyStep2BtnClick() {
        onBuyStep2BtnClick.postValue(Event(Unit))
    }
}