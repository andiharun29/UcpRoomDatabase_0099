package com.example.ucp2.ui.ViewModel.Supplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.Data.Entity.Supplier
import com.example.ucp2.Repository.Supplier.RepositorySupplier
import kotlinx.coroutines.launch

class SupplierViewModel(private val repositorySupplier: RepositorySupplier) : ViewModel() {
    var uiState by mutableStateOf(SupplierUIState())

    fun updateState(supplierEvent: SupplierEvent) {
        uiState = uiState.copy(
            supplierEvent = supplierEvent,
        )
    }
    private fun validateFields(): Boolean {
        val event = uiState.supplierEvent
        val errorstate = FormErrorSupplier(
            nama = if (event.nama.isNotEmpty()) null else "nama tidak boleh kosong",
            kontak = if (event.kontak.isNotEmpty()) null else "kontak tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "alamat tidak boleh kosong"
        )
        uiState = uiState.copy(
            isEntryValid = errorstate
        )
        return errorstate.isValid()
    }

    fun saveDataSupplier(){
        val currentEvent = uiState.supplierEvent

        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositorySupplier.insertSupplier(currentEvent.toSupplierEntity())
                    uiState = uiState.copy(
                        snackbarMessage = "Data berhasil disimpan",
                        supplierEvent = SupplierEvent(),
                        isEntryValid = FormErrorSupplier()
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackbarMessage = "data gagal disimpan")
                }
            }
        } else {
            uiState = uiState.copy(
                snackbarMessage = "input tidak valid periksa kembali data anda")
        }
    }
    fun resetSnackBarSupplierMessage() {
        uiState = uiState.copy(snackbarMessage = null)
    }
}


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