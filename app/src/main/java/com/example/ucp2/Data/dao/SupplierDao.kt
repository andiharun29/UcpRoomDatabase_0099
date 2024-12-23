package com.example.ucp2.Data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2.Data.Entity.Supplier
import kotlinx.coroutines.flow.Flow

@Dao
interface SupplierDao {
    @Insert
    suspend fun insertSupplier(
        supplier: Supplier
    )
    @Query("SELECT * FROM Supplier ORDER BY nama ASC")
    fun getAllSupplier(): Flow<List<Supplier>>

    @Query("SELECT * FROM Supplier")
    fun getNamaSupplier(): Flow<Supplier>
}