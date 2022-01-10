package com.thiru.cleanarchisample.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var firstName: String = "",
    var lastName:String = "",
    var password:String = "",
    var email:String = ""
):Parcelable
