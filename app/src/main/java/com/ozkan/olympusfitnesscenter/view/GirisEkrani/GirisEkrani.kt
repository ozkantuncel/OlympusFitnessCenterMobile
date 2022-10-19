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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.ozkan.olympusfitnesscenter.R
import kotlinx.coroutines.launch

@Composable
fun GirisEkrani(auth: FirebaseAuth, navController: NavController) {
    val tfK_Ad = remember { mutableStateOf("") }
    val tfPass = remember { mutableStateOf("") }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Box {
        Image(
            painter = painterResource(id = R.drawable.fts),
            contentDescription = "", modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Scaffold(
            scaffoldState = scaffoldState,
            backgroundColor = Color.Transparent,
            content = {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.size(150.dp))
                    OutlinedTextField(value = tfK_Ad.value,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.person_pic),
                                contentDescription = ""
                            )
                        },
                        onValueChange = { tfK_Ad.value = it },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        placeholder = { Text(text = "Kullanıcı Adınızı Giriniz") }
                    )

                    OutlinedTextField(value = tfPass.value,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.lock_pic),
                                contentDescription = ""
                            )
                        },
                        onValueChange = { tfPass.value = it },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        placeholder = { Text(text = "Sifrenizi Giriniz") }
                    )


                    Column(
                        modifier = Modifier.padding(top = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                auth.signInWithEmailAndPassword(tfK_Ad.value, tfPass.value)
                                    .addOnCompleteListener {
                                        if (it.isSuccessful) {
                                            navController.navigate("anaEkran")
                                        } else {
                                            scope.launch {
                                                scaffoldState.snackbarHostState.showSnackbar(message = "E-Mail ya da şifre yanlış")
                                            }
                                        }
                                    }

                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.buutonG)),
                            shape = CutCornerShape(10),
                            modifier = Modifier.size(width = 275.dp, height = 60.dp)
                        ) {
                            Text(text = "GİRİŞ YAP", fontSize = 35.sp, color = Color.White)
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Spacer(modifier = Modifier.size(40.dp))
                            TextButton(onClick = {
                                navController.navigate("kayitEkrani")
                            }) {
                                Text(
                                    text = "Kayıt Ol",
                                    style = TextStyle(textDecoration = TextDecoration.Underline),
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            }
                            TextButton(onClick = {
                                navController.navigate("sifreSifirlama")
                            }) {
                                Text(
                                    text = "Sifremi Unuttum",
                                    style = TextStyle(textDecoration = TextDecoration.Underline),
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            }
                            Spacer(modifier = Modifier.size(40.dp))
                        }
                    }

                    Spacer(modifier = Modifier.size(125.dp))
                }

            }
        )
    }

}