package com.ozkan.olympusfitnesscenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ozkan.olympusfitnesscenter.entity.Program
import com.ozkan.olympusfitnesscenter.repo.ProgramDaoRepository

class ProgramViewModel:ViewModel() {

    var programListesi = MutableLiveData<List<Program>>()
    var proKayit = ProgramDaoRepository()

    init {
        ProgmlariAl()
        programListesi = proKayit.progamiGetir()
    }

    fun ProgmlariAl(){
        proKayit.tumProgramiAl()
    }

    fun Ara(aramaKelimesi:String){
        proKayit.programAra(aramaKelimesi)
    }

    fun Sil(kisi_id:String){
        proKayit.programSil(kisi_id)
    }

    fun GUncelle(kisi_id:String, kisi_program:String, program_ad:String){
        proKayit.programGuncele(kisi_id,kisi_program,program_ad)
    }
}