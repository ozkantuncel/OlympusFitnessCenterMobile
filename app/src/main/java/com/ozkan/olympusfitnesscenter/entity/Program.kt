package com.ozkan.olympusfitnesscenter.entity

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Program(var kisi_id:String?="",var kisi_email:String?="", var kisi_program:String?="",var program_ad:String?="") {
}