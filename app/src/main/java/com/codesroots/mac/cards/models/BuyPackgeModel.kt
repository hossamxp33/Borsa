package com.codesroots.mac.cards.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Entity(tableName = "Buypackge")
@Parcelize
data class Buypackge (
    var opno: Int? = null,
    var salor: String? = null,
    var time: String? = null,
    var device: String? = null,
    var name: String? = null,
    var src: String? = null,
    var price: String? = null,
    var notes: String? = null,
    var count: String? = null,
    var err: String? = null,
    var notesimg :String? = null,

    @Ignore
    var pencode:List<Pencode>? = null

):Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0


}

data class LoginModel (
    val success: Boolean? = null,
    val data: LoginData? = null
)

data class LoginData (
    val userid: Long? = null,
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
    val data: List<Product>
)


data class Product (
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
    val center: Center,
    @SerializedName("package")
    val productPackage: Package
)

data class Center (
    val id: Long,
    val username: String
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