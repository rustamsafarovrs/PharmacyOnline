package tj.rs.pharmacyonline.ui_commons.base

import android.app.ProgressDialog
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import tj.rs.pharmacyonline.R

/**
 * Created by Rustam Safarov (RS) on 30.06.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
open class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected fun <T : ViewModel> getViewModel(modelClass: Class<T>): T {
        return ViewModelProvider(
            this,
            viewModelFactory
        ).get(modelClass)
    }

    /*
    * Callback
    **/
    protected fun setBottomSheetCallback(v: View) {
        var mBottomSheetBehavior = BottomSheetBehavior.from(v.parent as View)
        mBottomSheetBehavior?.setBottomSheetCallback(mBottomSheetBehaviorCallback)
    }

    private val mBottomSheetBehaviorCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            //do nothing
        }

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == STATE_HIDDEN) {
                dismiss()
            }
        }
    }

    /**
     * Progress dialog
     */
    private var progressDialog: ProgressDialog? = null

    protected fun showProgressDialog() {
        if (progressDialog == null
            || !progressDialog!!.isShowing
        ) {
            progressDialog =
                ProgressDialog.show(context, "", getString(R.string.loading), true, false)
        }
    }

    protected fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }
}