package com.example.ucp2.ui.ViewModel.Supplier

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.Data.Entity.Supplier
import com.example.ucp2.Repository.Supplier.RepositorySupplier
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class SupplierHomeViewModel(
    private val repositorySupplier: RepositorySupplier
) : ViewModel() {
    val homeUIStateSupplier: StateFlow<HomeUIStateSupplier> = repositorySupplier.getAllSupplier()
        .filterNotNull()
        .map {
            HomeUIStateSupplier(
                listSupplier = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeUIStateSupplier(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                HomeUIStateSupplier(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = HomeUIStateSupplier(
                isLoading = true
            )
        )
}

data class HomeUIStateSupplier(
    val listSupplier: List<Supplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = " "
)