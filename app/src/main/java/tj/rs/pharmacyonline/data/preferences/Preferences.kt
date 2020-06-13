package tj.rs.pharmacyonline.data.preferences

import android.content.Context
import android.content.SharedPreferences

/**
 * @author Rustam Safarov (RS)
 * created at 13.06.2020
 */
class Preferences(private val context: Context) {
    companion object {
        private const val IS_AUTHORIZED = "authorized"
        private const val PHONE_NUMBER = "phone_number"

    }

    private lateinit var shPr: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    init {
        shPr = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        editor = shPr.edit()
    }

    fun setAuthorized(isAuthorized: Boolean) {
        editor.putBoolean(IS_AUTHORIZED, isAuthorized)
        editor.commit()
    }

    fun getAuthorized(): Boolean {
        return shPr.getBoolean(IS_AUTHORIZED, false)
    }

    fun setPhoneNumber(phoneNumber: String) {
        editor.putString(PHONE_NUMBER, phoneNumber)
        editor.commit()
    }

    fun getPhoneNumber(): String {
        return shPr.getString(PHONE_NUMBER, "").toString()
    }
}