package tj.rs.pharmacyonline.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tj.rs.pharmacyonline.data.model.Medicine
import tj.rs.pharmacyonline.data.model.Price
import tj.rs.pharmacyonline.db.converters.DepartmentValueTypeConverter
import tj.rs.pharmacyonline.db.converters.ListPriceValueTypeConverter

/**
 * Created by Rustam Safarov (RS) on 13.07.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
@Database(
    entities = [
        Medicine::class,
        Price::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListPriceValueTypeConverter::class, DepartmentValueTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pharmacyDao(): PharmacyDao

}