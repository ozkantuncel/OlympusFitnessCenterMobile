package com.ozkan.olympusfitnesscenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ozkan.olympusfitnesscenter.entity.Program
import com.ozkan.olympusfitnesscenter.repo.ProgramDaoRepository

class ProgramViewModel:ViewModel() {

    var programListesi = MutableLiveData<List<Program>>()
    var proKayit = ProgramDaoRepository()

    init {
        progmlariAl()
        programListesi = proKayit.progamiGetir()
    }

    fun progmlariAl(){
        proKayit.tümProgramiAl()
    }

    fun ara(aramaKelimesi:String){
        proKayit.programAra(aramaKelimesi)
    }

    fun sil(kisi_id:String){
        proKayit.programSil(kisi_id)
    }

    fun güncelle(kisi_id:String,kisi_program:String,program_ad:String){
        proKayit.programGüncele(kisi_id,kisi_program,program_ad)
    }
}