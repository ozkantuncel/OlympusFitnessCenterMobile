package com.ozkan.olympusfitnesscenter.entity

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Alet(var alet_id:String?="",var alet_ismi:String?="",var alet_resmi:String?="",var alet_kullanim:String?="",var alet_video:String? ="") {
}