package com.ozkan.olympusfitnesscenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ozkan.olympusfitnesscenter.entity.Beslenme
import com.ozkan.olympusfitnesscenter.repo.BeslenmeDaoRepository

class BeslenmeViewModel:ViewModel() {

    var besleListe = MutableLiveData<List<Beslenme>>()
    var yemekAl = BeslenmeDaoRepository()

    init {
        yemekleriAl()
        besleListe = yemekAl.yemekGetir()
    }

    fun yemekleriAl(){
        yemekAl.tümYemekleriAL()
    }

    fun ara(aramaKelimesi:String){
        yemekAl.yemekAra(aramaKelimesi)
    }
}