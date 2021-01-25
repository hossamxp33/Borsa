package com.codesroots.mac.cards.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StockData(
    val BName: String,
    val CName: String,
    val BS: String,
    val Bid: String,
    val Bprice: String,
    val SPrice: String,
    val SS: String,
    val Statous: String,
    val Sqty : String,
    val Cid : String

): Parcelable {

}
