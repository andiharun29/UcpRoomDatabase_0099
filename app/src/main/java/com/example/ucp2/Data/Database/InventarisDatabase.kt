package com.example.ucp2.Data.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.Data.Entity.Barang
import com.example.ucp2.Data.Entity.Supplier
import com.example.ucp2.Data.dao.BarangDao
import com.example.ucp2.Data.dao.SupplierDao

@Database(entities = [Barang::class, Supplier::class], version = 1, exportSchema = false)
abstract class InventarisDatabase : RoomDatabase(){
    abstract fun barangDao(): BarangDao
    abstract fun supplierDao() : SupplierDao
    companion object{
        @Volatile
        private var Instance: InventarisDatabase? = null

        fun getDatabase(context: Context): InventarisDatabase{
            return Instance?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    InventarisDatabase::class.java,
                    "InventarisDatabase"
                )
                    .build().also { Instance = it }
            }
        }
    }
}