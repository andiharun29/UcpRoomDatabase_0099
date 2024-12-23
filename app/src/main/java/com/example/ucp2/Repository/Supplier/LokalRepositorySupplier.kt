package com.example.ucp2.Repository.Supplier

import com.example.ucp2.Data.Entity.Supplier
import com.example.ucp2.Data.dao.SupplierDao
import kotlinx.coroutines.flow.Flow

class LocalRepositorySupplier (
    private val supplierDao: SupplierDao
) : RepositorySupplier {
    override suspend fun insertSupplier(supplier: Supplier) {
        supplierDao.insertSupplier(supplier)
    }

    override fun getAllSupplier(): Flow<List<Supplier>> {
        return supplierDao.getAllSupplier()
    }

    override fun getNamaSupplier(): Flow<Supplier> {
       return supplierDao.getNamaSupplier()
    }

}