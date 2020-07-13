package tj.rs.pharmacyonline.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import tj.rs.pharmacyonline.data.model.Price

/**
 * Created by Rustam Safarov (RS) on 13.07.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class ListPriceValueTypeConverter {

    @TypeConverter
    fun fromString(value: String?): List<Price>? {
        val gson = Gson()
        return value?.let {
            val listType = object : TypeToken<List<Price>>() {}.type
            return gson.fromJson(it, listType)
        }
    }

    @TypeConverter
    fun fromList(list: List<Price>?): String? {
        val gson = Gson()
        return list?.let {
            gson.toJson(it)
        }
    }
}