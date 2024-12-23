package com.example.ucp2.Repository.Supplier


import com.example.ucp2.Data.Entity.Supplier
import kotlinx.coroutines.flow.Flow

interface RepositorySupplier {
    suspend fun insertSupplier(supplier: Supplier)

    fun getAllSupplier(): Flow<List<Supplier>>

    fun getNamaSupplier(): Flow<Supplier>
}