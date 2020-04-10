package tj.rs.pharmacyonline.modules

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Rustam Safarov (RS) on 02.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */

class NetManager(private var applicationContext: Context) {
    val isConnectedToInternet: Boolean?
        get() {
            val conManager =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ni = conManager.activeNetworkInfo
            return ni != null && ni.isConnected
        }
}