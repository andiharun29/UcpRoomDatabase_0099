package com.example.ucp2.Repository.Barang

import com.example.ucp2.Data.Entity.Barang
import kotlinx.coroutines.flow.Flow

interface RepositoryBarang {
    suspend fun insertBarang(barang: Barang)

    fun getAllBarang() : Flow<List<Barang>>

    fun getBarang(id : String): Flow<Barang>

    suspend fun deleteBarang(barang: Barang)

    suspend fun updateBarang(barang: Barang)
}
