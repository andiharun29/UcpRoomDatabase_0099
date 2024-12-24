package com.example.ucp2.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.ui.View.Barang.DetailBarangView
import com.example.ucp2.ui.View.Barang.HomeBarangView
import com.example.ucp2.ui.View.Barang.InsertBarangView
import com.example.ucp2.ui.View.Barang.UpdateBarangView
import com.example.ucp2.ui.View.HalamanHome
import com.example.ucp2.ui.View.Supplier.HomesupplierView
import com.example.ucp2.ui.View.Supplier.InsertSupplierView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route
    ){
        composable(
            route = DestinasiHome.route
        ){
            HalamanHome(
                onItemClick = {item ->
                    when (item){
                        "Supplier" -> navController.navigate(DestinasiHomeSupplier.route)
                        "Barang" -> navController.navigate(DestinasiHomeBarang.route)
                        else -> {}
                    }
                }
            )
        }
        composable(
            route = DestinasiHomeSupplier.route
        ){
            HomesupplierView(
                onAddSupplier = {
                    navController.navigate(DestinasiInsertSupplier.route)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = DestinasiInsertSupplier.route
        ) {
            InsertSupplierView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = Modifier
            )
        }
        composable(
            route = DestinasiHomeBarang.route
        ){
            HomeBarangView(
                onAddBarangClick = {
                    navController.navigate(DestinasiInsertBarang.route)
                },
                onDetailBarangClick = {
                    navController.navigate("${DestinasiDetailBarang.route}/$id")
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = DestinasiInsertBarang.route
        ){
            InsertBarangView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = Modifier
            )
        }
        composable(
            DestinasiDetailBarang.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailBarang.id){
                    type = NavType.StringType
                }
            )
        ){
            val id = it.arguments?.getString(DestinasiDetailBarang.id)
            id?.let {id ->
                DetailBarangView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateBarang.route}/$it")
                    },
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdateBarang.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiUpdateBarang.id) {
                    type = NavType.StringType
                }
            )
        ){
            UpdateBarangView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                }
            )
        }
    }
}