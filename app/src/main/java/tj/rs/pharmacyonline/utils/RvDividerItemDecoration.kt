package tj.rs.pharmacyonline.utils

import android.content.Context
import android.graphics.drawable.InsetDrawable
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.DividerItemDecoration
import tj.rs.pharmacyonline.R

fun getDividerItemDecoration(
    context: Context,
    @DimenRes left: Int = R.dimen.dp_0,
    @DimenRes top: Int = R.dimen.dp_0,
    @DimenRes right: Int = R.dimen.dp_0,
    @DimenRes bottom: Int = R.dimen.dp_0
): DividerItemDecoration {

    val attrs = intArrayOf(android.R.attr.listDivider)

    val a = context.obtainStyledAttributes(attrs)
    val divider = a.getDrawable(0)

    val insetLeft = context.resources.getDimensionPixelSize(left)
    val insetTop = context.resources.getDimensionPixelSize(top)
    val insetRight = context.resources.getDimensionPixelSize(right)
    val insetBottom = context.resources.getDimensionPixelSize(bottom)

    val insetDivider = InsetDrawable(divider, insetLeft, insetTop, insetRight, insetBottom)
    a.recycle()

    val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
    itemDecoration.setDrawable(insetDivider)

    return itemDecoration
}