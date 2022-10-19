package com.ozkan.olympusfitnesscenter.view.anaEkran

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.ozkan.olympusfitnesscenter.R
import kotlinx.coroutines.launch


@Composable
fun AnaEkran(navController: NavController, auth: FirebaseAuth) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    val selectedItem = remember { mutableStateOf(-1) }

    Box {
        Image(
            painter = painterResource(id = R.drawable.fts),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Scaffold(
            backgroundColor = Color.Transparent,
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "",
                            modifier = Modifier.padding(start = 50.dp)
                        )
                    },
                    backgroundColor = colorResource(id = R.color.orengeAna),
                    navigationIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.menu_pic),
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            })
                    }
                )
            },
            content = {
                if (selectedItem.value == 0) {
                    navController.navigate("profil")
                }
                if (selectedItem.value == 1) {
                    navController.navigate("randevuEkrani")
                }
                if (selectedItem.value == 2) {
                    navController.navigate("canliDestek")
                }
                if (selectedItem.value == 3) {
                    navController.navigate("istekveÖneri")
                }
                if (selectedItem.value == 4) {
                    navController.navigate("iletisimveHakinde")
                }



                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    //Spacer(modifier = Modifier.height(50.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Spacer(modifier = Modifier.width(10.dp))

                        Card(

                            elevation = 10.dp,
                            backgroundColor = colorResource(id = R.color.cardAna),
                            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                            border = BorderStroke(1.5.dp, Color.Yellow)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.clickable {
                                    navController.navigate("antrenman")
                                    Log.e("Card", "Tiklendi")
                                }) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,

                                    ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.card1),
                                        contentDescription = ""
                                    )

                                }
                            }
                        }

                        Card(

                            elevation = 10.dp,
                            backgroundColor = colorResource(id = R.color.cardAna),
                            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                            border = BorderStroke(1.5.dp, Color.Yellow)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.clickable {
                                    navController.navigate("beslenme")
                                    Log.e("Card", "Tiklendi")
                                }) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,

                                    ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.card2),
                                        contentDescription = ""
                                    )

                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))


                    }

                    Card(

                        elevation = 10.dp,
                        backgroundColor = colorResource(id = R.color.cardAna),
                        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                        border = BorderStroke(1.5.dp, Color.Yellow)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.clickable {
                                navController.navigate("programYaratma")

                            }) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,

                                ) {
                                Image(
                                    painter = painterResource(id = R.drawable.card3),
                                    contentDescription = ""
                                )

                            }
                        }
                    }
                    Card(

                        elevation = 10.dp,
                        backgroundColor = colorResource(id = R.color.cardAna),
                        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                        border = BorderStroke(1.5.dp, Color.Yellow)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.clickable {
                                navController.navigate("sporAletleri")
                                Log.e("Card", "Tiklendi")

                            }) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,

                                ) {
                                Image(
                                    painter = painterResource(id = R.drawable.card4),
                                    contentDescription = ""
                                )

                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(50.dp))
                    Row() {
                        Text(
                            text = "Salondaki kişi sayısı: ",
                            color = Color.Yellow,
                            fontSize = 20.sp
                        )
                        Text(text = "23", color = Color.Green, fontSize = 20.sp)
                    }
                }
            },
            drawerContent = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.drawAna))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(50.dp)
                            .background(colorResource(id = R.color.orengeAna)),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.End
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu_pic),
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                }
                            })
                    }
                    DropdownMenuItem(onClick = {
                        selectedItem.value = 0
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }) {
                        Text(text = "PROFİL", color = colorResource(id = R.color.orengeAna))
                    }

                    DropdownMenuItem(onClick = {
                        selectedItem.value = 1
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }) {
                        Text(text = "RANDEVU", color = colorResource(id = R.color.orengeAna))
                    }

                    DropdownMenuItem(onClick = {
                        selectedItem.value = 2
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }) {
                        Text(text = "CANLI DESTEK", color = colorResource(id = R.color.orengeAna))
                    }

                    DropdownMenuItem(onClick = {
                        selectedItem.value = 3
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }) {
                        Text(
                            text = "İSTEK VE ÖNERİLER",
                            color = colorResource(id = R.color.orengeAna)
                        )
                    }

                    DropdownMenuItem(onClick = {
                        selectedItem.value = 4
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }) {
                        Text(
                            text = "İLETŞİM&HAKKINDA",
                            color = colorResource(id = R.color.orengeAna)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxHeight(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        /*Icon(painter = painterResource(id = R.drawable.exit_pic72), contentDescription = "", modifier = Modifier.clickable {
                            auth.signOut()
                            navController.navigate("mainpage")
                        })*/

                        IconButton(onClick = {
                            auth.signOut()
                            navController.navigate("mainpage")
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.exit_pic72),
                                contentDescription = "",
                                tint = Color.Red
                            )

                        }

                    }
                }
            }
        )
        val activity = (LocalContext.current as Activity)
        BackHandler(onBack = {
            if (scaffoldState.drawerState.isOpen) {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            } else {
                activity.finish()
            }
        })
    }
}



