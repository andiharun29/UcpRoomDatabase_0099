package com.example.ucp2.Repository.Barang

import com.example.ucp2.Data.Entity.Barang
import com.example.ucp2.Data.dao.BarangDao
import kotlinx.coroutines.flow.Flow

class LocalRepositoryBarang(
    private val barangDao: BarangDao
) : RepositoryBarang{
    override suspend fun insertBarang(barang: Barang) {
        barangDao.insertBarang(barang)
    }

    override suspend fun deleteBarang(barang: Barang) {
        barangDao.deleteBarang(barang)
    }

    override suspend fun updateBarang(barang: Barang) {
        barangDao.deleteBarang(barang)
    }

    override fun getAllBarang(): Flow<List<Barang>> {
        return barangDao.getAllBarang()
    }

    override fun getBarang(id: String): Flow<Barang> {
        return barangDao.getBarang(id)
    }
}