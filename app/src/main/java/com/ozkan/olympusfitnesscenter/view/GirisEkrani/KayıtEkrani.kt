package com.ozkan.olympusfitnesscenter.view.GirisEkrani

import android.widget.Toast
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.ozkan.olympusfitnesscenter.R
import kotlinx.coroutines.launch

@Composable
fun KayitEkrani(auth: FirebaseAuth,navController: NavController){
    val tfEmail = remember { mutableStateOf("") }
    val tfPass = remember { mutableStateOf("") }
    val tfPass2 = remember { mutableStateOf("") }

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
                         Text(text = "Üyelik Oluşturma", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                     })  
            },
            backgroundColor = Color.Transparent,
            content = {
                Spacer(modifier = Modifier.size(200.dp))
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    OutlinedTextField(value = tfEmail.value,
                        leadingIcon = { Icon(painter = painterResource(id = R.drawable.person_pic), contentDescription = "") },
                        onValueChange ={tfEmail.value = it},
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        placeholder = { Text(text = "E-Mail") }
                    )

                    OutlinedTextField(value = tfPass.value,
                        leadingIcon = { Icon(painter = painterResource(id = R.drawable.lock_pic), contentDescription = "") },
                        onValueChange ={tfPass.value = it},
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        placeholder = { Text(text = "Sifre") }
                    )

                    OutlinedTextField(value = tfPass2.value,
                        leadingIcon = { Icon(painter = painterResource(id = R.drawable.lock_pic), contentDescription = "") },
                        onValueChange ={tfPass2.value = it},
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        placeholder = { Text(text = "Sifreyi tekrar giriniz") }
                    )
                    Button(onClick = {
                        if(tfEmail.value.equals("")||tfPass.value.equals("")||(!tfPass.value.equals(tfPass2.value))){
                           scope.launch {
                               scaffoldState.snackbarHostState.showSnackbar(message = "Hatalı Deneme")
                           }
                        }else{
                            auth.createUserWithEmailAndPassword(tfEmail.value,tfPass.value).
                                    addOnSuccessListener {
                                        scope.launch {
                                            scaffoldState.snackbarHostState.showSnackbar(message = "Üye oluşturuldu")
                                            navController.navigate("mainPage")
                                        }
                                    }.addOnFailureListener{
                                    scope.launch {
                                        Toast.makeText(context,it.localizedMessage,Toast.LENGTH_LONG).show()
                                    }
                            }
                        }
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.buutonG)),
                        shape = CutCornerShape(10),
                        modifier = Modifier.size(width = 275.dp, height = 60.dp)
                    ) {
                        Text(text = "KAYIT OL", fontSize = 35.sp, color = Color.White)
                    }
                }
            }
        )
    }
}