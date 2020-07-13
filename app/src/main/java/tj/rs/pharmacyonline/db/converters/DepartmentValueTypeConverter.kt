package tj.rs.pharmacyonline.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import tj.rs.pharmacyonline.data.model.Department

/**
 * Created by Rustam Safarov (RS) on 13.07.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class DepartmentValueTypeConverter {

    @TypeConverter
    fun fromString(value: String?): Department? {
        val gson = Gson()
        return value?.let {
            return gson.fromJson(it, object : TypeToken<Department>() {}.type)
        }
    }

    @TypeConverter
    fun fromList(list: Department?): String? {
        val gson = Gson()
        return list?.let {
            gson.toJson(it)
        }
    }
}