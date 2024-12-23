package com.example.ucp2.ui.ViewModel.Barang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.Data.Entity.Barang
import com.example.ucp2.Repository.Barang.RepositoryBarang
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class BarangHomeviewmodel(
    private val repositoryBarang: RepositoryBarang
) : ViewModel() {

    val homeUIStateBarang: StateFlow<HomeUIStateBarang> = repositoryBarang.getAllBarang()
        .filterNotNull()
        .map {
            HomeUIStateBarang(
                listBarang = it.toList(),
                isLoading = false
            )
        }
        .onStart {
            emit(HomeUIStateBarang(isLoading = true))
            delay(900)
        }
        .catch {
            HomeUIStateBarang(
                isError = true,
                isLoading = true,
                errorMessage = it.message?: "ada yang salah"
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUIStateBarang(
                isLoading = true
            )
        )
}

data class HomeUIStateBarang(
    val listBarang: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)