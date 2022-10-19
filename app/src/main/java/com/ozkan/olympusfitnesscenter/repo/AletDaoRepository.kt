package com.ozkan.olympusfitnesscenter.repo

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.ozkan.olympusfitnesscenter.entity.Alet


class AletDaoRepository {
    var aletListe = MutableLiveData<List<Alet>>()
    var refAlet: DatabaseReference

    init {
        val db = FirebaseDatabase.getInstance()
        refAlet = db.getReference("Tools")
        aletListe = MutableLiveData()
    }

    fun aletiGetir(): MutableLiveData<List<Alet>> {
        return aletListe
    }

    fun tumAletleriAl() {
        refAlet.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val liste = ArrayList<Alet>()
                for (d in snapshot.children) {
                    val alet = d.getValue(Alet::class.java)
                    if (alet != null) {
                        alet.alet_id = d.key
                        liste.add(alet)
                    }
                }
                aletListe.value = liste
            }

            override fun onCancelled(error: DatabaseError) {
                //no opt
            }
        })
    }

    fun aletAra(aramaKelimesi: String) {
        refAlet.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val liste = ArrayList<Alet>()
                for (d in snapshot.children) {
                    val alet = d.getValue(Alet::class.java)
                    if (alet != null) {
                        if (alet.alet_ismi!!.lowercase().contains(aramaKelimesi.lowercase())) {
                            alet.alet_id = d.key
                            liste.add(alet)
                        }
                    }
                }
                aletListe.value = liste
            }

            override fun onCancelled(error: DatabaseError) {
                //no opt
            }
        })
    }
}