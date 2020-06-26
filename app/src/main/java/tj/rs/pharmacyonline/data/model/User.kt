package tj.rs.pharmacyonline.data.model

import com.google.gson.annotations.SerializedName
import tj.rs.pharmacyonline.data.const.UserParams
import java.util.*

/**
 * @author Rustam Safarov (RS)
 * created at 18.06.2020
 */

data class User(
    @SerializedName(UserParams.PHONE_NUMBER)
    var phoneNumber: String
) {
    var id: Int? = 0

    @SerializedName(UserParams.USERNAME)
    var username: String? = null

    @SerializedName(UserParams.SURNAME)
    var surname: String? = null

    @SerializedName(UserParams.ADDRESS)
    var address: String? = null

    @SerializedName(UserParams.EMAIL)
    var email: String? = null

    @SerializedName(UserParams.CREATED)
    var created: Date? = null

    fun getUsernameAndSurname(): String {
        var res = if (username.isNullOrEmpty()) "" else "$username "
        res += if (surname.isNullOrEmpty()) "" else surname
        return res
    }
}