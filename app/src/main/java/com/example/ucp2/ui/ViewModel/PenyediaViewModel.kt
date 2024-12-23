package com.example.ucp2.ui.ViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.TokoApp
import com.example.ucp2.ui.ViewModel.Barang.BarangHomeviewmodel
import com.example.ucp2.ui.ViewModel.Barang.BarangViewModel
import com.example.ucp2.ui.ViewModel.Barang.DetailBarangViewModel
import com.example.ucp2.ui.ViewModel.Barang.UpdateBarangViewModel
import com.example.ucp2.ui.ViewModel.Supplier.SupplierHomeViewModel
import com.example.ucp2.ui.ViewModel.Supplier.SupplierViewModel

object PenyediaViewModel{
    val Factory = viewModelFactory{
        initializer {
            SupplierHomeViewModel(
                TokoApp().containerApp.repositorySupplier
            )
        }
        initializer {
            SupplierViewModel(
                TokoApp().containerApp.repositorySupplier
            )
        }
    }
}

