package tj.rs.pharmacyonline.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Rustam Safarov (RS)
 * created at 09.06.2020
 * (c) 2020 RS DevTeam
 */

class Response {
    @SerializedName("message")
    var message: String = ""

    @Expose(serialize = false, deserialize = false)
    var responseCode: Int = 0

}