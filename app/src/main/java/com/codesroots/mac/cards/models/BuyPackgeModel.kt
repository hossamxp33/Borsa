package com.codesroots.mac.cards.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

data class Buypackge (

    val center: Center? = null


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
    val orders: List<Report>
)


data class Report (
    val id: Long,
    @SerializedName("package_id")
    val packageID: Long,
    @SerializedName("user_id")
    val userID: Long,
    @SerializedName("center_id")
    val centerID: Long,
    val created: String,
    val modified: String,
    val approve: Long,
    val mobile: String,
    val center: Centers,
    @SerializedName("package")
    val productPackage: Package
)

data class Centers (
    val id: Long,
    val username: String
)




data class Terms (
    val headline: String,
    val details: String,
    val mobile: String,
    val email: String,
    val fb: String
)