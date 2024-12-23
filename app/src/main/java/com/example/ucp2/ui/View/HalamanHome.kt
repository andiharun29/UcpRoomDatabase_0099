package com.example.ucp2.ui.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.R

@Preview(showBackground = true)
@Composable
fun HalamanHome(
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
            .background(color = Color.White)
    ) {
        HeaderSection()
        BodySection(onItemClick = onItemClick)
    }
}

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 48.dp))
            .background(color = colorResource(id = R.color.purple_500))
            .padding(bottom = 32.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 24.dp)
        ) {
            Column {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "Home Icon",
                    tint = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(Modifier.padding(3.dp))
                Text(
                    text = "Welcome",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "to Our Warehouse",
                    fontSize = 22.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        Box(
            Modifier.align(Alignment.CenterEnd)
                .padding(24.dp)
                .padding(top = 12.dp)
        )
        {
            Image(
                painter = painterResource(id = R.drawable.foto),
                contentDescription = "Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@Composable
fun BodySection(
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Selamat datang di aplikasi manajemen gudang kami!",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Gunakan fitur-fitur di bawah ini untuk mengelola Supplier dan Barang Anda dengan lebih efisien. " +
                    "Pastikan untuk selalu memantau stok agar operasional tetap lancar.",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ManageBox(
            title = "Manage Supplier",
            description = "Kelola data supplier di sini. Pantau dan atur informasi supplier dengan cepat.",
            backgroundColor = Color(0xFF7D5260),
            iconResource = R.drawable.su,
            onClick = { onItemClick("Supplier") }
        )

        ManageBox(
            title = "Manage Barang",
            description = "Kelola data barang di sini. Tambahkan, ubah, atau hapus data barang dengan mudah.",
            backgroundColor = Color(0xFF7D5260),
            iconResource = R.drawable.ka,
            onClick = { onItemClick("Barang") }
        )

        Spacer(modifier = Modifier.size(16.dp))

    }
}

@Composable
fun ManageBox(
    title: String,
    description: String,
    backgroundColor: Color,
    iconResource: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
            Image(
                painter = painterResource(id = iconResource),
                contentDescription = "$title Icon",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
        }
    }
}