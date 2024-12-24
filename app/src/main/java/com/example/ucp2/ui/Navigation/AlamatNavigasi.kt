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


