package com.example.ucp2.ui.View.Barang

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.R
import com.example.ucp2.ui.CustomWidget.AppBar
import com.example.ucp2.ui.Navigation.AlamatNavigasi
import com.example.ucp2.ui.ViewModel.Barang.BarangEvent
import com.example.ucp2.ui.ViewModel.Barang.BarangUIState
import com.example.ucp2.ui.ViewModel.Barang.BarangViewModel
import com.example.ucp2.ui.ViewModel.Barang.FormErrorBarangstate
import com.example.ucp2.ui.ViewModel.PenyediaViewModel
import kotlinx.coroutines.launch




@Composable
fun InsertBarangView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BarangViewModel = viewModel(factory = PenyediaViewModel.Factory) //Inisialisasi ViewModel
){
    val uiState = viewModel.uiState // Ambil UI State dari ViewModel
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Observasi perubahan snackbarMessage
    LaunchedEffect(uiState.snackbarMessage){
        uiState.snackbarMessage?.let {message ->
            coroutineScope.launch{
                //Tampilkan snackbar
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }
    Scaffold(
        modifier = Modifier,
        snackbarHost = {SnackbarHost(hostState = snackbarHostState)}
    ) { padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            AppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Mahasiswa",
                actionIcon = R.drawable.ka
            )
            //Isi Body
            InsertBodyBarang(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    // Update state di ViewModel
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    viewModel.saveData()
                    onNavigate()
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun FormBarang(
    barangEvent: BarangEvent = BarangEvent(),
    onValueChange: (BarangEvent) -> Unit = {},
    errorState: FormErrorBarangstate = FormErrorBarangstate(),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.id,
            onValueChange = { onValueChange(barangEvent.copy(id = it)) },
            label = { Text(text = "ID") },
            isError = errorState.id != null,
            placeholder = { Text(text = "Masukkan ID") }
        )
        Text(
            text = errorState.id ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.nama,
            onValueChange = { onValueChange(barangEvent.copy(nama = it)) },
            label = { Text(text = "Nama") },
            isError = errorState.nama != null,
            placeholder = { Text(text = "Masukkan Nama") }
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.deskripsi,
            onValueChange = { onValueChange(barangEvent.copy(deskripsi = it)) },
            label = { Text(text = "Deskripsi") },
            isError = errorState.deskripsi != null,
            placeholder = { Text(text = "Masukkan Deskripsi") }
        )
        Text(
            text = errorState.deskripsi ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.harga,
            onValueChange = { onValueChange(barangEvent.copy(harga = it)) },
            label = { Text(text = "Harga") },
            isError = errorState.harga != null,
            placeholder = { Text(text = "Masukkan Harga") }
        )
        Text(
            text = errorState.harga ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.stok,
            onValueChange = { onValueChange(barangEvent.copy(stok = it)) },
            label = { Text(text = "Stok") },
            isError = errorState.stok != null,
            placeholder = { Text(text = "Masukkan Stok") }
        )
        Text(
            text = errorState.stok ?: "",
            color = Color.Red
        )

        // Nama Supplier Dropdown
        var expanded by remember { mutableStateOf(false) }
        val supplierOptions = listOf("Supplier A", "Supplier B", "Supplier C")
        val selectedSupplier = barangEvent.nama_supplier

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedSupplier,
                onValueChange = {},
                readOnly = true,
                label = { Text("Nama Supplier") },
                isError = errorState.nama_supplier != null,
                placeholder = { Text(text = "Pilih Nama Supplier") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                supplierOptions.forEach { supplier ->
                    DropdownMenuItem(
                        text = { Text(supplier) },
                        onClick = {
                            expanded = false
                            onValueChange(barangEvent.copy(nama_supplier = supplier))
                        }
                    )
                }
            }
        }
        Text(
            text = errorState.nama_supplier ?: "",
            color = Color.Red
        )
    }
}

