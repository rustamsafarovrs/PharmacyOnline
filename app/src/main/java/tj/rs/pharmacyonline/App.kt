package tj.rs.pharmacyonline

import android.app.Application
import androidx.room.Room
import tj.rs.pharmacyonline.db.AppDatabase


/**
 * Created by Rustam Safarov (RS) on 13.07.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        database =
            Room.databaseBuilder(this, AppDatabase::class.java, "database.db")
                .allowMainThreadQueries()
                .build()
    }


    companion object {

        private var instance: App? = null

        private var database: AppDatabase? = null

        fun getInstance(): App {
            return instance!!
        }

        fun getDatabase(): AppDatabase {
            return database!!
        }
    }

}