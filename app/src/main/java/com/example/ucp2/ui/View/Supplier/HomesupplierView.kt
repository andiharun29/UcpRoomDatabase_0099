package com.example.ucp2.ui.View.Supplier

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.Data.Entity.Supplier
import com.example.ucp2.R
import com.example.ucp2.ui.CustomWidget.AppBar
import com.example.ucp2.ui.CustomWidget.loadingState
import com.example.ucp2.ui.ViewModel.PenyediaViewModel
import com.example.ucp2.ui.ViewModel.Supplier.HomeUIStateSupplier
import com.example.ucp2.ui.ViewModel.Supplier.SupplierHomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomesupplierView(
    modifier: Modifier = Modifier,
    viewModel: SupplierHomeViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBack: () -> Unit,
    onAddSupplier: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            AppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Data Supplier",
                actionIcon = R.drawable.ka
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddSupplier,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        }
    ) {innerPadding ->
        val homeSupplierUiState by viewModel.homeUIStateSupplier.collectAsState()

        BodyHomeSupplierView(
            homeUIState = homeSupplierUiState,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeSupplierView(
    modifier: Modifier = Modifier,
    homeUIState: HomeUIStateSupplier,
){
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    when{
        homeUIState.isLoading -> {
            loadingState()
        }
        homeUIState.isError -> {
            LaunchedEffect(homeUIState.errorMessage){
                homeUIState.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }
        homeUIState.listSupplier.isEmpty() -> {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Data Tidak Ditemukan",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        else -> {
            ListSupplier(
                listsupplier = homeUIState.listSupplier,
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListSupplier(
    listsupplier: List<Supplier>,
    modifier: Modifier = Modifier,
){
    LazyColumn(
        modifier = modifier
    ){
        items(listsupplier,
            itemContent = { supplier ->
                CardSupplier(supplier = supplier
                )
            }
        )
    }
}

@Composable
fun CardSupplier(
    supplier: Supplier,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.su),
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .size(50.dp),
            tint = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = supplier.nama,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.width(10.dp))
            Row (verticalAlignment = Alignment.CenterVertically){
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "ID",
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "ID : ${supplier.id}",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.height(6.dp))

            Row (verticalAlignment = Alignment.CenterVertically){
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Alamat",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = supplier.alamat,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.height(6.dp))

            Row (verticalAlignment = Alignment.CenterVertically){
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Kontak",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = supplier.kontak,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.height(6.dp))

            HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
        }
    }
}