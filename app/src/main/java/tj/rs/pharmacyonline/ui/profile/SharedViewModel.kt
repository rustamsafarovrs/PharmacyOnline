package tj.rs.pharmacyonline.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tj.rs.pharmacyonline.utils.live_data.Event

/**
 * Created by Rustam Safarov (RS) on 03.07.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class SharedViewModel : ViewModel() {
    val updateEvent = MutableLiveData(Event(false))

}