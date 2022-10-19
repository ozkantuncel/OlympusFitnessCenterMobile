package com.ozkan.olympusfitnesscenter.view.anaEkran

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ozkan.olympusfitnesscenter.R
import com.ozkan.olympusfitnesscenter.viewmodel.AletViewModel
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun SporAletleriEkrani(navController: NavController) {

    val scaffoldState = rememberScaffoldState()

    val aramaYapiliyor = remember { mutableStateOf(false) }
    val tfArama = remember { mutableStateOf("") }

    val viewModel: AletViewModel = viewModel()
    val aletListesi = viewModel.aletListesi.observeAsState(listOf())

    Box {

        Scaffold(
            scaffoldState = scaffoldState,
            backgroundColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = {
                        if (aramaYapiliyor.value) {
                            TextField(
                                value = tfArama.value,
                                onValueChange = {
                                    tfArama.value = it
                                    viewModel.ara(it)
                                },
                                label = { Text(text = "Ara") },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.Transparent,
                                    focusedLabelColor = Color.White,
                                    focusedIndicatorColor = Color.White,
                                    unfocusedLabelColor = Color.White,
                                    unfocusedIndicatorColor = Color.White
                                )
                            )
                        } else {
                            Text(text = "Spor Aletleri")
                        }
                    },
                    backgroundColor = colorResource(id = R.color.orengeAna), actions = {
                        if (aramaYapiliyor.value) {
                            IconButton(onClick = {
                                aramaYapiliyor.value = false
                                tfArama.value = ""
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.close_pic),
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                        } else {
                            IconButton(onClick = { aramaYapiliyor.value = true }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.searc_pic),
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                )

            },

            content = {
                LazyColumn(modifier = Modifier.background(color = Color.LightGray)) {
                    items(
                        count = aletListesi.value!!.count(),
                        itemContent = {
                            val alet = aletListesi.value!![it]
                            Card(
                                modifier = Modifier
                                    .padding(all = 5.dp)
                                    .fillMaxWidth()
                            ) {
                                Row(modifier = Modifier.clickable {


                                    navController.navigate("aletDetay/${alet.alet_kullanim}")

                                }) {
                                    Row(
                                        modifier = Modifier
                                            .padding(all = 10.dp)
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {

                                        Column(
                                            verticalArrangement = Arrangement.SpaceEvenly,
                                            modifier = Modifier.fillMaxHeight()
                                        ) {
                                            GlideImage(
                                                imageModel = "${alet.alet_resmi}",
                                                modifier = Modifier.size(120.dp)
                                            )

                                        }
                                        Text(text = "Spor Aletinin Adı:${alet.alet_ismi}")
                                        IconButton(onClick = {

                                        }) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.play_pic),
                                                contentDescription = "",
                                                tint = Color.Black
                                            )
                                        }

                                    }

                                }
                            }
                        }
                    )
                }
            }
        )
    }
}