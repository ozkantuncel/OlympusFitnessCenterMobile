package com.ozkan.olympusfitnesscenter.view.GirisEkrani


import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.ozkan.olympusfitnesscenter.R
import kotlinx.coroutines.launch

@Composable
fun SifreKurtarmaEkrani(auth: FirebaseAuth,navController: NavController){
    val tfEmail = remember { mutableStateOf("") }
    //val tfPass = remember { mutableStateOf("") }
    //val tfPassVis = remember { mutableStateOf(false) }


    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Box {
        Image(painter = painterResource(id = R.drawable.fts),
            contentDescription ="" , modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds)
        Scaffold(
            scaffoldState=scaffoldState,
            topBar = {
                TopAppBar(backgroundColor = colorResource(id = R.color.orengeAna),
                    title = {
                        Text(text = "Şifre Sıfırlama", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    })
            },
            backgroundColor = Color.Transparent,
            content = {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.size(200.dp))
                    OutlinedTextField(value = tfEmail.value,
                    leadingIcon = { Icon(painter = painterResource(id = R.drawable.person_pic), contentDescription = "") },
                    onValueChange ={tfEmail.value = it},
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    placeholder = { Text(text = "E-Mail Adresinizi Giriniz") }
                )

                    Button(onClick = {
                        if(tfEmail.value.equals("")){
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(message = "Hatalı işlem")
                            }
                        }else{
                            auth.sendPasswordResetEmail(tfEmail.value).
                                    addOnCompleteListener{
                                        if(it.isSuccessful){
                                            scope.launch {
                                                scaffoldState.snackbarHostState.showSnackbar(message = "${tfEmail.value} - e kurtama adresi yolandi")
                                            }
                                        }else{
                                            scope.launch {
                                                scaffoldState.snackbarHostState.showSnackbar(message = "Bir sorun var")
                                            }
                                        }
                                    }
                        }
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.buutonG)),
                        shape = CutCornerShape(10),
                        modifier = Modifier.size(width = 275.dp, height = 60.dp),
                    ) {
                        Text(text = "Sıfırla", fontSize = 35.sp, color = Color.White)
                    }
                    Spacer(modifier = Modifier.size(200.dp))
                }
            }
        )
    }
}