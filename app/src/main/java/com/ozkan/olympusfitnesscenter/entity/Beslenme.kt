package com.ozkan.olympusfitnesscenter.entity

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Beslenme(var yemek_id:String?="",var program_ad:String?="",var yemek_tarif:String?="",var yemek_resim:String?="") {

}