package tj.rs.pharmacyonline.utils

/**
 * @author Rustam Safarov (RS)
 * created at 04.06.2020
 * (c) 2020 RS DevTeam
 */


import androidx.navigation.NavOptions
import tj.rs.pharmacyonline.R

fun getSlideLeftAnimBuilder(): NavOptions.Builder {
    val navBuilder = NavOptions.Builder()

    navBuilder.setEnterAnim(R.anim.slide_enter_from_right_anim)
    navBuilder.setExitAnim(R.anim.nav_default_pop_exit_anim)
    navBuilder.setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
    navBuilder.setPopExitAnim(R.anim.slide_exit_to_right_anim)

    return navBuilder
}

fun getSlideUpAnimBuilder(): NavOptions.Builder {
    val navBuilder = NavOptions.Builder()

    navBuilder.setEnterAnim(R.anim.slide_up)
    navBuilder.setExitAnim(R.anim.nav_default_pop_exit_anim)
    navBuilder.setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
    navBuilder.setPopExitAnim(R.anim.slide_down)

    return navBuilder
}

fun getSlideUpDefaultAnimBuilder(): NavOptions.Builder {
    val navBuilder = NavOptions.Builder()

    navBuilder.setEnterAnim(R.anim.slide_up)
    navBuilder.setExitAnim(R.anim.nav_default_pop_exit_anim)
    navBuilder.setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
    navBuilder.setPopExitAnim(R.anim.nav_default_pop_exit_anim)

    return navBuilder
}
