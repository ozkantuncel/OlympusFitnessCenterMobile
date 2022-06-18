package com.ozkan.olympusfitnesscenter.repo

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.ozkan.olympusfitnesscenter.entity.Alet
import com.ozkan.olympusfitnesscenter.entity.Beslenme

class BeslenmeDaoRepository {

    var beslenmeListe = MutableLiveData<List<Beslenme>>()
    var refBesle: DatabaseReference
    init {
        val db = FirebaseDatabase.getInstance()
        refBesle = db.getReference("Foods")
        beslenmeListe = MutableLiveData()
    }

    fun yemekGetir(): MutableLiveData<List<Beslenme>> {
        return beslenmeListe
    }

    fun tümYemekleriAL(){
        refBesle.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val liste = ArrayList<Beslenme>()
                for(d in snapshot.children){
                    val besle = d.getValue(Beslenme::class.java)
                    if(besle !=null){
                        besle.yemek_id = d.key
                        liste.add(besle)
                    }
                }
                beslenmeListe.value = liste
            }
            override fun onCancelled(error: DatabaseError) {
                println("T SENİ BULACAM AD!")
            }
        })
    }

    fun yemekAra(aramaKelimesi:String){
        refBesle.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val liste = ArrayList<Beslenme>()
                for(d in snapshot.children){
                    val besle = d.getValue(Beslenme::class.java)
                    if(besle !=null){
                        besle.yemek_id = d.key
                        if(besle.program_ad!!.lowercase().contains(aramaKelimesi.lowercase())){
                            besle.yemek_id = d.key
                            liste.add(besle)
                        }
                    }
                }
                beslenmeListe.value = liste
            }
            override fun onCancelled(error: DatabaseError) {
                println("T SENİ BULACAM AD!")
            }
        })
    }
}