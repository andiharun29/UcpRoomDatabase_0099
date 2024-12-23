package com.example.ucp2.ui.View.Supplier

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.R
import com.example.ucp2.ui.CustomWidget.AppBar
import com.example.ucp2.ui.ViewModel.PenyediaViewModel
import com.example.ucp2.ui.ViewModel.Supplier.FormErrorSupplier
import com.example.ucp2.ui.ViewModel.Supplier.SupplierEvent
import com.example.ucp2.ui.ViewModel.Supplier.SupplierUIState
import com.example.ucp2.ui.ViewModel.Supplier.SupplierViewModel
import kotlinx.coroutines.launch


@Composable
fun InsertBodySupplier(
    modifier: Modifier = Modifier,
    onValueChange: (SupplierEvent) -> Unit,
    uiState: SupplierUIState,
    onClick: () -> Unit
){
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormSupplier(
            supplierEvent = uiState.supplierEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                "Simpan",
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormSupplier(
    modifier: Modifier = Modifier,
    supplierEvent: SupplierEvent = SupplierEvent(),
    onValueChange: (SupplierEvent) -> Unit = {},
    errorState: FormErrorSupplier = FormErrorSupplier()
){
    Column(
        modifier = modifier.fillMaxWidth()
    ){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = supplierEvent.nama,
            onValueChange = { onValueChange(supplierEvent.copy(nama = it)) },
            label = { Text(text = "Masukkan Nama") },
            isError = errorState.nama != null,
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = supplierEvent.kontak,
            onValueChange = { onValueChange(supplierEvent.copy(kontak = it)) },
            label = { Text(text = "Masukkan Kontak") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = errorState.kontak != null,
        )
        Text(
            text = errorState.kontak ?: "",
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = supplierEvent.alamat,
            onValueChange = { onValueChange(supplierEvent.copy(alamat = it)) },
            label = { Text(text = "Masukkan Alamat") },
            isError = errorState.alamat != null,
        )
        Text(
            text = errorState.alamat ?: "",
            color = Color.Red)
    }
}