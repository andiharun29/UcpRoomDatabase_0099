package com.example.ucp2.ui.ViewModel.Barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.Data.Entity.Barang
import com.example.ucp2.Repository.Barang.RepositoryBarang
import com.example.ucp2.ui.Navigation.DestinasiUpdateBarang
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateBarangViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBarang: RepositoryBarang
) : ViewModel(){
    var updateUIState by mutableStateOf(BarangUIState())
        private set
    private val _id: String = checkNotNull(savedStateHandle[DestinasiUpdateBarang.id])

    init {
        viewModelScope.launch {
            updateUIState = repositoryBarang.getBarang(_id)
                .filterNotNull()
                .first()
                .toUIStateBarang()
        }
    }
    fun updateState(barangEvent: BarangEvent){
        updateUIState = updateUIState.copy(
            barangEvent = barangEvent,
        )
    }
    fun validateFields(): Boolean{
        val event = updateUIState.barangEvent
        val errorstate = FormErrorBarangstate(
            id = if (event.id.isNotEmpty()) null else "id tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "nama tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "deskripsi tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "harga tidak boleh kosong",
            stok = if (event.stok.isNotEmpty()) null else "stok tidak boleh kosong",
            nama_supplier = if (event.nama_supplier.isNotEmpty()) null else "nama supplier tidak boleh kosong",
        )
        updateUIState = updateUIState.copy(isEntryValid = errorstate)
        return errorstate.isValid()
    }

}

fun Barang.toUIStateBarang(): BarangUIState = BarangUIState(
    barangEvent = this.toDetailUiEvent(),
)