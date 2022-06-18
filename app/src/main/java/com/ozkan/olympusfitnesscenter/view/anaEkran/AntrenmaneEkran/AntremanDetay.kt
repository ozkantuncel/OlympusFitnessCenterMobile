package com.ozkan.olympusfitnesscenter.view.anaEkran.AntrenmaneEkran

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.ozkan.olympusfitnesscenter.R
import com.ozkan.olympusfitnesscenter.entity.Program
import com.ozkan.olympusfitnesscenter.viewmodel.ProgramKayitViewModel
import com.ozkan.olympusfitnesscenter.viewmodel.ProgramViewModel

@Composable
fun AntremanDetay(program: Program){

    val tfYazi = remember { mutableStateOf("") }
    val tfProgramAdi = remember{ mutableStateOf("") }
    val tfEMail = remember{ mutableStateOf("")}

    val viewModel: ProgramViewModel = viewModel()

    LaunchedEffect(key1 = true){
        tfYazi.value = program.kisi_program!!
        tfProgramAdi.value = program.program_ad!!
        tfEMail.value = program.kisi_email!!
    }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Box{
        Image(painter = painterResource(id = R.drawable.fts), contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds)
        Scaffold(
            scaffoldState = scaffoldState,
            backgroundColor = Color.Transparent,
            topBar = {

                TopAppBar(title = { Text(text = "Antrenman Programı Detayı") },
                    backgroundColor = colorResource(id = R.color.orengeAna)
                )
            },
            content = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .size(height = 350.dp, width = 350.dp)
                            .padding(5.dp)
                            .background(color = Color.LightGray)
                            .border(
                                3.dp,
                                colorResource(id = R.color.orengeAna)
                            )
                    ) {

                        TextField(value = tfYazi.value, modifier = Modifier.fillMaxSize(), onValueChange ={tfYazi.value=it} )

                    }
                    Text(text = tfEMail.value, modifier = Modifier
                        .size(height = 25.dp, width = 250.dp)
                        .background(color = Color.White))
                    TextField(value = tfProgramAdi.value,
                        onValueChange = {tfProgramAdi.value = it},
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.LightGray),
                        placeholder = { Text(text = "Progam Adını Giriniz")})

                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    val kisi_program = tfYazi.value
                    val program_ad = tfProgramAdi.value
                    viewModel.güncelle(program.kisi_id!!,kisi_program,program_ad)

                }, shape = RoundedCornerShape(50), backgroundColor = colorResource(id = R.color.buttonP)) {
                    Icon(painter = painterResource(id = R.drawable.send_pic), contentDescription ="" )
                }
            },
            isFloatingActionButtonDocked = true,
            floatingActionButtonPosition = FabPosition.Center,

            bottomBar = {
                TopAppBar(title = {}, backgroundColor = colorResource(id = R.color.buttonPa))
                BottomAppBar(
                    backgroundColor = colorResource(id = R.color.orengeAna),
                    cutoutShape = RoundedCornerShape(50),
                    content = {
                    }
                )
            }
        )
    }
}