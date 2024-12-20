package com.example.ucp2.DependenciesInjection

import android.content.Context
import com.example.ucp2.Data.Database.InventarisDatabase
import com.example.ucp2.Repository.Barang.LocalRepositoryBarang
import com.example.ucp2.Repository.Barang.RepositoryBarang
import com.example.ucp2.Repository.Supplier.LocalRepositorySupplier

import com.example.ucp2.Repository.Supplier.RepositorySupplier

interface InterfaceContainerApp{
    val repositoryBarang : RepositoryBarang
    val repositorySupplier : RepositorySupplier
}

class ContainerApp (private val context: Context) : InterfaceContainerApp{
    override val repositoryBarang: RepositoryBarang by lazy {
        LocalRepositoryBarang(InventarisDatabase.getDatabase(context).barangDao())
    }
    override val repositorySupplier: RepositorySupplier by lazy {
        LocalRepositorySupplier(InventarisDatabase.getDatabase(context).supplierDao())
    }
}