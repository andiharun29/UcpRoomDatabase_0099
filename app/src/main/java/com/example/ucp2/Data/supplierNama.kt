package com.example.ucp2.Data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.ViewModel.PenyediaViewModel
import com.example.ucp2.ui.ViewModel.Supplier.SupplierHomeViewModel

object NamaSupplier{
    @Composable
    fun options(
        supplierHomeViewModel: SupplierHomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<String>{
        val dataNama by supplierHomeViewModel.homeUIStateSupplier.collectAsState()
        val supplierNama = dataNama.listSupplier.map { it.nama }
        return supplierNama
    }
}