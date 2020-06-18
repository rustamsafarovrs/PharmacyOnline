package tj.rs.pharmacyonline.data.model

import com.google.gson.annotations.SerializedName


/**
 * @author Rustam Safarov (RS)
 * created at 16.06.2020
 */
class Banner(
    @SerializedName("id")
    val id: Int,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("image_url")
    var imageUrl: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("desc")
    val desc: String = ""
)