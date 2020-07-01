package tj.rs.pharmacyonline.utils.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.jwang123.flagkit.FlagKit
import tj.rs.pharmacyonline.R

/**
 * @author Rustam Safarov (RS)
 * created at 23.06.2020
 */


@BindingAdapter("app:errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
    view.error = errorMessage ?: ""
}

@BindingAdapter("app:errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: Int?) {
    if (errorMessage == null
        || errorMessage == 0
    ) {
        view.error = null
    } else {
        view.error = view.context.getString(errorMessage)
    }
}

@BindingAdapter("app:errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: Any?) {
    if (errorMessage != null) {
        when (errorMessage) {
            is String -> {
                view.error = errorMessage
            }
            is Int -> {
                if (errorMessage == 0) {
                    view.error = null
                } else {
                    view.error = view.context.getString(errorMessage)
                }
            }
        }

    } else {
        view.error = null
    }
}

@BindingAdapter("visibleGone")
fun visibleGone(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleInvisible")
fun visibleInvisible(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("bindFlag")
fun bindFlag(imageView: ImageView, domain: String?) {
    domain?.let {
        try {
            val drawable = FlagKit.drawableWithFlag(
                imageView.context, it.toLowerCase()
            )
            imageView.setImageDrawable(drawable)

        } catch (e: Exception) {
            e.printStackTrace()
            imageView.setImageResource(R.color.transparent)
        }
    }
}
