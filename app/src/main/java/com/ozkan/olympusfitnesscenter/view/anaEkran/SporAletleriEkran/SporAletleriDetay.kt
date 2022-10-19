package com.ozkan.olympusfitnesscenter.view.anaEkran.SporAletleriEkran


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ozkan.olympusfitnesscenter.R


@Composable
fun SporAletiDetay(icerik: String) {

    val scaffoldState = rememberScaffoldState()
    Box {
        Image(
            painter = painterResource(id = R.drawable.fts), contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Scaffold(
            scaffoldState = scaffoldState,
            backgroundColor = Color.Transparent,
            topBar = {

                TopAppBar(
                    title = { Text(text = "Spor Aletleri Ve Kullanımı") },
                    backgroundColor = colorResource(id = R.color.orengeAna)
                )
            },
            content = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //Text(text = resim.toString())
                    Text(
                        text = icerik,
                        color = Color.Black,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .background(Color.White)
                            .border(2.dp, Color.Yellow)
                            .padding(10.dp)
                    )


                }
            }
        )
    }
}