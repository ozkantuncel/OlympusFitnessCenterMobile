package com.ozkan.olympusfitnesscenter.view.anaEkran

import androidx.compose.foundation.*
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
import com.ozkan.olympusfitnesscenter.viewmodel.AletViewModel
import com.ozkan.olympusfitnesscenter.viewmodel.ProgramKayitViewModel

@Composable
fun ProgramYaratmaEkrani(auth: FirebaseAuth,navController: NavController){

    val tfYazi = remember { mutableStateOf("")}
    val tfProgramAdi = remember{ mutableStateOf("")}
    val tfresim = remember{ mutableStateOf("")}

    val viewModel:ProgramKayitViewModel = viewModel()


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

                TopAppBar(title = { Text(text = "Antrenman Programı Oluştur")},
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
                        Text(text = "Oluşturan Kişi:${auth.currentUser!!.email}", modifier = Modifier
                            .size(height = 30.dp, width = 350.dp)
                            .background(color = Color.LightGray).padding(10.dp))
                        TextField(value = tfProgramAdi.value,
                            onValueChange = {tfProgramAdi.value = it},
                            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.LightGray),
                            placeholder = { Text(text = "Progam Adını Giriniz")}, modifier = Modifier.padding(10.dp)
                            )




                    }
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        val kisi_email = auth.currentUser!!.email.toString()
                        val kisi_program = tfYazi.value
                        val program_ad = tfProgramAdi.value
                        viewModel.kayit(kisi_email,kisi_program,program_ad)
                        navController.navigate("anaEkran")

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