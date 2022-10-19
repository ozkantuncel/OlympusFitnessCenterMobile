package com.ozkan.olympusfitnesscenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ozkan.olympusfitnesscenter.entity.Alet
import com.ozkan.olympusfitnesscenter.repo.AletDaoRepository


class AletViewModel:ViewModel() {

    var aletListesi = MutableLiveData<List<Alet>>()
    var aletAl = AletDaoRepository()

    init {
        aletleriAl()
        aletListesi = aletAl.aletiGetir()
    }

    fun aletleriAl(){
        aletAl.tumAletleriAl()
    }

    fun ara(aramaKelimesi:String){
        aletAl.aletAra(aramaKelimesi)
    }

    /*fun aletKayit(alet_ismi:String,alet_resim:String,alet_kullanim:String){
        aletAl.aletKayıt(alet_ismi,alet_resim,alet_kullanim)
    }*/
}