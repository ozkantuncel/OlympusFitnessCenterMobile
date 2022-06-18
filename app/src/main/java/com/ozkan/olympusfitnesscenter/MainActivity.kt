package com.ozkan.olympusfitnesscenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.ozkan.olympusfitnesscenter.entity.Alet
import com.ozkan.olympusfitnesscenter.entity.Program

import com.ozkan.olympusfitnesscenter.view.GirisEkrani.GirisEkrani
import com.ozkan.olympusfitnesscenter.view.GirisEkrani.KayitEkrani
import com.ozkan.olympusfitnesscenter.view.GirisEkrani.SifreKurtarmaEkrani
import com.ozkan.olympusfitnesscenter.view.anaEkran.*
import com.ozkan.olympusfitnesscenter.view.anaEkran.drawerEkran.*
import com.ozkan.olympusfitnesscenter.ui.theme.OlympusFitnessCenterTheme
import com.ozkan.olympusfitnesscenter.view.anaEkran.AntrenmaneEkran.AntremanDetay
import com.ozkan.olympusfitnesscenter.view.anaEkran.BeslenmeEkran.BeslenmeDetay
import com.ozkan.olympusfitnesscenter.view.anaEkran.SporAletleriEkran.SporAletiDetay

class MainActivity : ComponentActivity() {
    private val auth by lazy{
        Firebase.auth
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OlympusFitnessCenterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Pages(auth = auth)
                }
            }
        }
    }
}
@Composable
fun Pages(auth: FirebaseAuth){
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "mainpage"){
        composable("mainpage"){
            if(auth.currentUser != null){
                AnaEkran(navController,auth)
            }else{
                GirisEkrani(auth,navController)
            }
        }

        composable("antremanDetay/{program}", arguments = listOf(
            navArgument("program"){type = NavType.StringType}
        )){
            val json = it.arguments?.getString("program")
            val nesne = Gson().fromJson(json,Program::class.java)
            AntremanDetay(program = nesne)
        }

        composable("aletDetay/{icerik}", arguments = listOf(
            navArgument("icerik"){type = NavType.StringType},
        )){
            val icerik = it.arguments?.getString("icerik")!!
            SporAletiDetay(icerik)
        }

        composable("besleDetay/{icerik}", arguments = listOf(
            navArgument("icerik"){type = NavType.StringType},
        )){
            val icerik = it.arguments?.getString("icerik")!!
            BeslenmeDetay(icerik)
        }


        composable("kayitEkrani"){
            KayitEkrani(auth,navController)
        }

        composable("sifreSifirlama"){
            SifreKurtarmaEkrani(auth,navController)
        }
        composable("anaEkran"){
            AnaEkran(navController,auth)
        }
        composable("antrenman"){

            AntrenmanProgaramiEkrani(navController)
        }
        composable("beslenme"){
            BeslenmeEkrani(navController)
        }
        composable("programYaratma"){
            ProgramYaratmaEkrani(auth,navController)
        }
        composable("sporAletleri"){
            SporAletleriEkrani(navController)
        }
        composable("randevuEkrani"){
            RandevuEkrani()
        }
        composable("profil"){
            ProfilEkrani()
        }
        composable("istekveÖneri"){
            IstekveÖneriEkrani()
        }
        composable("iletisimveHakinde"){
            IletsimveHakkindeEkrani()
        }
        composable("canliDestek"){
            CanliDestekErkani()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OlympusFitnessCenterTheme {
        //ProgramYaratmaEkrani()
    }
}