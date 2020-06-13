package tj.rs.pharmacyonline.data.signup

import tj.rs.pharmacyonline.data.preferences.Preferences

/**
 * @author Rustam Safarov (RS)
 * created at 13.06.2020
 */
class AuthRepository(private val preferences: Preferences) {
    fun setAuthorized(isAuthorized: Boolean) {
        preferences.setAuthorized(isAuthorized)
    }

    fun getAuthorized() = preferences.getAuthorized()

    fun setPhoneNumber(phoneNumber: String) {
        preferences.setPhoneNumber(phoneNumber)
    }

    fun getPhoneNumber() = preferences.getPhoneNumber()
}