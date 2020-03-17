package com.codesroots.mac.cards.models

import com.google.gson.annotations.SerializedName


data class CompanyData (
    val success: Boolean? = null,
    val data: List<CompanyDatum>? = null
)


data class CompanyDatum (
    val id: String? = null,
    val name: String? = null,
    @SerializedName("logo")
    val src: String? = null,
    val sprice: String? = null,
    val code: String? = null,
    val created: String? = null
)
