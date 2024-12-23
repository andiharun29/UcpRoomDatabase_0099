package com.example.ucp2.ui.ViewModel.Barang

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.Data.Entity.Barang
import com.example.ucp2.Repository.Barang.RepositoryBarang
import com.example.ucp2.ui.Navigation.DestinasiDetailBarang
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch



data class DetailUiState(
    val detailUiEvent: BarangEvent = BarangEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = " "
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == BarangEvent()
    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != BarangEvent()
}

fun Barang.toDetailUiEvent(): BarangEvent{
    return BarangEvent(
        id = id.toString(),
        nama = nama,
        deskripsi = deskripsi,
        harga = harga.toString(),
        stok = stok.toString(),
        nama_supplier = nama_supplier
    )
}