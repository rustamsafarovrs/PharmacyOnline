package tj.rs.pharmacyonline.db

import androidx.room.*
import tj.rs.pharmacyonline.data.model.Medicine


/**
 * Created by Rustam Safarov (RS) on 13.07.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
@Dao
interface PharmacyDao {
    @Insert
    fun insert(vararg medicine: Medicine)

    @Update
    fun update(vararg medicine: Medicine)

    @Delete
    fun delete(vararg medicine: Medicine)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: List<Medicine>)

    @Query("SELECT * FROM Medicine")
    fun getAll(): List<Medicine>

}