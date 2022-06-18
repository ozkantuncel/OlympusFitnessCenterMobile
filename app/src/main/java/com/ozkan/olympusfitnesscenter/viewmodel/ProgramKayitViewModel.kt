package com.ozkan.olympusfitnesscenter.viewmodel

import androidx.lifecycle.ViewModel
import com.ozkan.olympusfitnesscenter.repo.ProgramDaoRepository

class ProgramKayitViewModel:ViewModel() {
    var proKayit = ProgramDaoRepository()

    fun kayit(kisi_email:String,kisi_program:String,program_ad:String){
        proKayit.programKayit(kisi_email,kisi_program,program_ad)
    }



}