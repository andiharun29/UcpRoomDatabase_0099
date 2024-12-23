package com.example.ucp2.ui.ViewModel.Barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.Data.Entity.Barang
import com.example.ucp2.Repository.Barang.RepositoryBarang
import kotlinx.coroutines.launch

class BarangViewModel(private  val repositoryBarang: RepositoryBarang): ViewModel() {
    var uiState by mutableStateOf(BarangUIState())

    //memperbaiki state berdasarkan input
    fun updateState(barangEvent: BarangEvent) {
        uiState = uiState.copy(
            barangEvent = barangEvent,
        )
    }

    //validasi data input pengguna
    private fun validateFields(): Boolean {
        val event = uiState.barangEvent
        val errorstate = FormErrorBarangstate(
            id = if (event.id.isNotEmpty()) null else "id tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "nama tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "deskripsi tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "harga tidak boleh kosong",
            stok = if (event.stok.isNotEmpty()) null else "stok tidak boleh kosong",
            nama_supplier =  if (event.nama_supplier.isNotEmpty()) null else "nama supplier tidak boleh kosong",
        )

        uiState = uiState.copy(
            isEntryValid = errorstate
        )
        return errorstate.isValid()
    }

    fun saveData() {
        val currentEvent = uiState.barangEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryBarang.insertBarang(currentEvent.toBarangEntity())
                    uiState = uiState.copy(
                        snackbarMessage = "Data berhasil disimpan",
                        barangEvent = BarangEvent(),
                        isEntryValid = FormErrorBarangstate()
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackbarMessage = "data gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackbarMessage = "input tidak valid periksa kembali data anda"
            )
        }
    }
    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackbarMessage = null)
    }
}

data class BarangUIState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormErrorBarangstate = FormErrorBarangstate(),
    val snackbarMessage: String? = null,
)

data class FormErrorBarangstate(
    val id: String? = null,
    val nama: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val nama_supplier: String? = null
){
    fun isValid(): Boolean{
        return id== null && nama == null && deskripsi == null &&
                harga == null && stok == null && nama_supplier == null
    }
}

fun BarangEvent.toBarangEntity(): Barang = Barang(
    id = id.toIntOrNull() ?: 0,
    nama = nama,
    deskripsi = deskripsi,
    harga = harga.toIntOrNull() ?: 0,
    stok = stok.toIntOrNull() ?: 0,
    nama_supplier = nama_supplier
)

data class BarangEvent(
    val id: String = "",
    val nama: String = "",
    val deskripsi: String = "",
    val harga: String = "",
    val stok: String = "",
    val nama_supplier: String = ""
)
