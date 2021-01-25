package com.codesroots.mac.cards.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class Currency(
    val CId: String,
    val CName: String,
    val CPrice: String,
    val Flag: String,
    val Senctime: String,
    val Sympol: String
): Parcelable {

}

/*
   {
        "CId": "1",
        "Sympol": "USD",
        "CName": "دولار أمريكي",
        "CPrice": "1",
        "Flag": "http://iqborsa.com/img/flages/USD.png",
        "Senctime": "2018-08-13 02:08:35"
    },
 */