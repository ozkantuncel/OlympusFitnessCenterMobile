package com.ozkan.olympusfitnesscenter.view.anaEkran

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.ozkan.olympusfitnesscenter.R
import com.ozkan.olympusfitnesscenter.viewmodel.ProgramViewModel

@Composable
fun AntrenmanProgaramiEkrani(navController: NavController) {

    val scaffoldState = rememberScaffoldState()
    val aramaYapiliyor = remember { mutableStateOf(false) }
    val tfArama = remember { mutableStateOf("") }

    val viewModel: ProgramViewModel = viewModel()
    val programListesi = viewModel.programListesi.observeAsState(listOf())


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
                                    viewModel.Ara(it)
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
                            Text(text = "Antrenman Listesi")
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
                        count = programListesi.value!!.count(),
                        itemContent = {
                            val program = programListesi.value!![it]
                            Card(
                                modifier = Modifier
                                    .padding(all = 5.dp)
                                    .fillMaxWidth()
                            ) {
                                Row(modifier = Modifier.clickable {
                                    val programJson = Gson().toJson(program)
                                    navController.navigate("antremanDetay/${programJson}")
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
                                            Text(text = "Kulanıcı Adı:${program.kisi_email}")
                                            Spacer(modifier = Modifier.size(30.dp))
                                            Text(text = "Programın Adı:${program.program_ad}")
                                        }
                                        IconButton(onClick = {
                                            viewModel.Sil(program.kisi_id!!)
                                        }) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.del_pic),
                                                contentDescription = "", tint = Color.Gray
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



