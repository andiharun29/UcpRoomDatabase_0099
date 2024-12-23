package com.example.ucp2.ui.ViewModel.Supplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.Data.Entity.Supplier
import com.example.ucp2.Repository.Supplier.RepositorySupplier
import kotlinx.coroutines.launch




data class SupplierUIState(
    val supplierEvent: SupplierEvent = SupplierEvent(),
    val isEntryValid: FormErrorSupplier = FormErrorSupplier(),
    val snackbarMessage: String? = null,
)

data class FormErrorSupplier(
    val nama: String? = null,
    val kontak: String? = null,
    val alamat: String? = null
){
    fun isValid(): Boolean {
        return nama != null && kontak != null && alamat != null
    }
}

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