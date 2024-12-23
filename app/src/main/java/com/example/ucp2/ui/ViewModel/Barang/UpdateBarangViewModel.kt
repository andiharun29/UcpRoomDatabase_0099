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

}

fun Barang.toUIStateBarang(): BarangUIState = BarangUIState(
    barangEvent = this.toDetailUiEvent(),
)