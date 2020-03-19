package com.codesroots.mac.cards.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

data class Buypackge (

    val center: Center? = null,
    @Ignore
    var pencode:List<Pencode>? = null

) {



}

data class Center (
    val packageID: Long? = null,
    val userID: Long? = null,
    val mobile: String? = null,
    val centerID: Long? = null,
    val created: String? = null,
    val modified: String? = null,
    val id: Long? = null,
    val err:String? = null
)

data class LoginModel (
    val success: Boolean? = null,
    val data: LoginData? = null
)

data class LoginData (
    val userid: Int? = null,
    val roomid: Long? = null,
    val email: String? = null,
    val mobile: Long? = null,
    val groupid: String? = null,
    val username: String? = null,
    val token: String? = null,
    val message: String? = null

    )

data class MyBalance (
    val account: String? = null,
    val commession: String? = null
)
data class ReportDaily (
    val opid: Long,
    val opno: String,
    val opdate: String,
    val strcase: String,
    val amount: String,
    val err: String? = null,
    val cardname: String? = null,
    val logo:String? = null

    )


@Entity(tableName = "pencode")
@Parcelize
data class Pencode (
    val pencode: String? = null,
    val expdate: String? = null,
    val serial: String? = null,
    var buypackageid: Int

):Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}

data class Terms (
    val headline: String,
    val details: String,
    val mobile: String,
    val email: String,
    val fb: String
)