package com.example.ucp2.ui.View.Barang

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.Data.Entity.Barang
import com.example.ucp2.R
import com.example.ucp2.ui.CustomWidget.AppBar
import com.example.ucp2.ui.ViewModel.Barang.DetailBarangViewModel
import com.example.ucp2.ui.ViewModel.Barang.DetailUiState
import com.example.ucp2.ui.ViewModel.Barang.toBarangEntity
import com.example.ucp2.ui.ViewModel.PenyediaViewModel



@Composable
fun BodyDetailMhs(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState = DetailUiState(),
    onDeleteClick: () -> Unit = { }

){
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    when {
        detailUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator() //Tampilan loading
            }
        }
        detailUiState.isUiEventNotEmpty -> {
            Column (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailBarang(
                    barang = detailUiState.detailUiEvent.toBarangEntity(),
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(onClick = {
                    deleteConfirmationRequired = true
                },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = "Delete")
                }

                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCencel = { deleteConfirmationRequired = false},
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
        detailUiState.isUiEventEmpty -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Data tidak ditemukan",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}


@Composable
fun ItemDetailBarang(
    modifier: Modifier = Modifier,
    barang: Barang

){
    Card (
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailBarang(judul = "id", isinya = barang.id.toString())
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBarang(judul = "Nama", isinya = barang.nama)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBarang(judul = "deskripsi", isinya = barang.deskripsi)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBarang(judul = "harga", isinya = barang.harga.toString())
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBarang(judul = "stok", isinya = barang.stok.toString())
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBarang(judul = "nama Supplier", isinya = barang.nama_supplier)
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}

@Composable
fun ComponentDetailBarang(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String

){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCencel: () -> Unit,
    modifier: Modifier = Modifier

){
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = { Text( "Delete Data")},
        text = { Text("Apakah anda yakin ingin mengahpus data?" )},
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCencel) {
                Text(text = "Cencel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}