package com.codesroots.mac.firstkotlon.DataLayer.ApiService

import com.codesroots.mac.cards.DataLayer.helper.PreferenceHelper
import com.codesroots.mac.cards.models.*

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory



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

    @POST("packages/GetPackgesByCompanyId/1.json")/*{company_id}*/
    fun GetPackageDetails(@Query("val") packageid: String,@Query("auth") auth: String):
            Observable<CompanyData>




    @POST("wserv?page=5")/*{company_id}*/
    fun BuyPackage(@Query("val") packageid: String,@Query("auth") auth: String,@Query("mount") amount: String):
            Observable<Buypackge>

    @POST("wserv?page=18")/*{company_id}*/
    fun PrintReport(@Query("val") packageid: String,@Query("auth") auth: String):
            Observable<Buypackge>
    @POST("sliders.json")/*{company_id}*/
    fun SliderData():
            Observable<SliderData>
    @POST("wserv?page=13")/*{company_id}*/
    fun GetMyDeialyReport(@Query("auth") auth: String):
            Observable<List<ReportDaily>>

    @POST("wserv?page=13")/*{company_id}*/
    fun GetMyDeialyReport(@Query("auth") auth: String,@Query("val") fromto:String):
            Observable<List<ReportDaily>>
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