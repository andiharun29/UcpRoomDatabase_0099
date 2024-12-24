package com.example.ucp2.ui.Navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiHome : AlamatNavigasi {
    override val route = "home"
}

object DestinasiHomeSupplier : AlamatNavigasi {
    override val route = "supplier"
}

object DestinasiInsertSupplier : AlamatNavigasi {
    override val route = "insertSupplier"
}

object DestinasiHomeBarang : AlamatNavigasi {
    override val route = "barang"
}

object DestinasiInsertBarang : AlamatNavigasi {
    override val route = "insertBarang"
}

object DestinasiUpdateBarang : AlamatNavigasi {
    override val route = "updateBarang"
    const val id = "Id"
    val routeWithArgs = "$route/{$id}"
}

object DestinasiDetailBarang : AlamatNavigasi {
    override val route = "detailBarang"
    const val id = "Id"
    val routeWithArgs = "$route/{$id}"
}
