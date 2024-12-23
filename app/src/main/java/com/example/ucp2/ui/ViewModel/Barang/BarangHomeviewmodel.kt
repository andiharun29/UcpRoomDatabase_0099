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



data class HomeUIStateBarang(
    val listBarang: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)