package com.ozkan.olympusfitnesscenter.repo

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.ozkan.olympusfitnesscenter.entity.Program


class ProgramDaoRepository {
    var programListe = MutableLiveData<List<Program>>()
    var refProgram:DatabaseReference

    init {
        val db = FirebaseDatabase.getInstance()
        refProgram = db.getReference("Programs")
        programListe = MutableLiveData()
    }

    fun progamiGetir():MutableLiveData<List<Program>>{
        return programListe
    }

    fun tümProgramiAl(){
        refProgram.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val liste = ArrayList<Program>()
                for(d in snapshot.children){
                    val program = d.getValue(Program::class.java)
                    if(program !=null){
                        program.kisi_id = d.key
                        liste.add(program)
                    }
                }
                programListe.value = liste
            }

            override fun onCancelled(error: DatabaseError) {
                println("T SENİ BULACAM!")
            }

        })
    }

    fun programAra(aramaKelimesi:String){
        refProgram.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val liste = ArrayList<Program>()

                for(d in snapshot.children){
                    val program = d.getValue(Program::class.java)
                    if(program !=null){
                        if(program.program_ad!!.lowercase().contains(aramaKelimesi.lowercase())){
                            program.kisi_id = d.key
                            liste.add(program)
                        }
                    }
                }
                programListe.value = liste
            }

            override fun onCancelled(error: DatabaseError) {
                println("T SENİ BULACAM!")
            }

        })
    }

    fun programKayit(kisi_email:String,kisi_program:String,program_ad:String){
        val yeniProgram = Program("",kisi_email,kisi_program,program_ad)
        refProgram.push().setValue(yeniProgram)
    }

    fun programGüncele(kisi_id:String,kisi_program:String,program_ad: String){
        val bilgiler = HashMap<String,Any>()
        bilgiler["kisi_program"]=kisi_program
        bilgiler["program_ad"] = program_ad
        refProgram.child(kisi_id).updateChildren(bilgiler)
    }

    fun programSil(kisi_id: String){
        refProgram.child(kisi_id).removeValue()
    }
}