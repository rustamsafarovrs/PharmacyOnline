package tj.rs.pharmacyonline.ui_commons.base

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import tj.rs.pharmacyonline.R


/**
 * Created by Rustam Safarov (RS) on 06.07.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */

open class BaseFragment : Fragment() {


    /**
     * Progress dialog
     */
    private var alertDialog: AlertDialog? = null

    protected fun showProgressDialog() {
        if (alertDialog == null
            || !alertDialog!!.isShowing
        ) {
            val builder = AlertDialog.Builder(context)
            builder.setCancelable(false) // if you want user to wait for some process to finish,

            builder.setView(R.layout.view_progress)
            alertDialog = builder.create()

            alertDialog?.show()
        }
    }

    protected fun dismissProgressDialog() {
        alertDialog?.dismiss()
    }

}