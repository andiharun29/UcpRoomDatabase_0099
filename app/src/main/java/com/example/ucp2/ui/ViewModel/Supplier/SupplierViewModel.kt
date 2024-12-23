package com.example.ucp2.ui.ViewModel.Supplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.Data.Entity.Supplier
import com.example.ucp2.Repository.Supplier.RepositorySupplier
import kotlinx.coroutines.launch



fun SupplierEvent.toSupplierEntity(): Supplier = Supplier(
    id = id,
    nama = nama,
    kontak = kontak,
    alamat = alamat
)

data class SupplierEvent(
    val id: Int = 0,
    val nama: String = "",
    val kontak: String = "",
    val alamat: String = ""
)