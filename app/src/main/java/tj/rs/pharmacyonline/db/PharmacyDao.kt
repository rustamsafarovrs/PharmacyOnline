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
    fun insert(medicine: Medicine)

    @Update
    fun update(medicine: Medicine)

    @Delete
    fun delete(medicine: Medicine)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data: List<Medicine>)

    @Query("SELECT * FROM Medicine")
    fun getAll(): List<Medicine>

    @Query("SELECT * FROM Medicine WHERE id=:id")
    fun getMedicine(id: Int): Medicine?

}