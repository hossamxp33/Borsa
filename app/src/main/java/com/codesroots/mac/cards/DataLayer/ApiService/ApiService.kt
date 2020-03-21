package com.codesroots.mac.firstkotlon.DataLayer.ApiService

import com.codesroots.mac.cards.models.*

import io.reactivex.Observable
import retrofit2.http.*


interface APIServices {

    @FormUrlEncoded
    @POST("users/token.json")
    abstract fun userlogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<LoginModel>

    @POST("companies.json")/*{company_id}*/
    fun GetCompanyData(@Query("auth") auth: String):
            Observable<CompanyData>

    @POST("wserv?page=12")/*{company_id}*/
    fun GetMyBlanceData(@Query("auth") auth: String):
            Observable<MyBalance>

    @GET("packages/GetPackgesByCompanyId/{company_id}.json")/*{company_id}*/
    fun GetPackageDetails(@Path("company_id") company_id: String):
            Observable<CompanyData>



    @FormUrlEncoded
    @POST("users/adds.json")/*{company_id}*/
    fun BuyPackage(
        @Field("user_id") useris: String,
        @Field("package_id") packageid: String,
        @Field("mobile") phone: String

        ):
            Observable<Buypackge>

    @POST("wserv?page=18")/*{company_id}*/
    fun PrintReport(@Query("val") packageid: String,@Query("auth") auth: String):
            Observable<Buypackge>
    @POST("sliders.json")/*{company_id}*/
    fun SliderData():
            Observable<SliderData>
    @GET("orders/dailyreportproducts.json")/*{company_id}*/
    fun GetMyDeialyReport():
            Observable<Packagess>

    @GET("orders/dailyreportproducts.json")/*{company_id}*/
    fun GetMyDeialyReport(@Query("auth") auth: String, @Query("val") fromto:String):
            Observable<Packagess>
    @GET("wserv?page=15")/*{company_id}*/
    fun GetTerms():
            Observable<Terms>
    @GET("wserv?page=14")/*{company_id}*/
    fun GetContactData():
            Observable<Terms>

    @GET("wserv?page=16")/*{company_id}*/
    fun GetPartnersData():
            Observable<List<PartnersModel>>

}