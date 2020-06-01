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

    @GET("users/getmyoffices.json")
    abstract fun Myoffices(

    ): Observable<LoginModel>

    @GET("transactions/getAgencyTrans.json")
    abstract fun MyTrans(

    ): Observable<Trans>

    @POST("companies.json")/*{company_id}*/
    fun GetCompanyData(@Query("auth") auth: String):
            Observable<CompanyData>

    @POST("Companies.json")/*{company_id}*/
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
        @Field("mobile") phone: String,
        @Field("type") type: Int,
        @Field("name") name: String


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
            Observable<ReportDaily>
    @GET("orders/getOrderForCenter.json")/*{company_id}*/
    fun GetCenterOrder():
            Observable<ReportDaily>
    @GET("orders/dailyreportproducts.json")/*{company_id}*/
    fun GetMyDeialyReport(@Query("auth") auth: String, @Query("val") fromto:String):
            Observable<ReportDaily>


    @FormUrlEncoded
    @POST("orders/reportproducts.json")/*{company_id}*/
    fun GetDatesReport(       @Field("start") start: String,
                              @Field("end") end: String):
            Observable<ReportDaily>



    @GET("wserv?page=15")/*{company_id}*/
    fun GetTerms():
            Observable<Terms>
    @FormUrlEncoded
    @POST("orders/edit/{order_id}.json")/*{company_id}*/
    fun EditOrderConfirm(        @Field("confirmed") approve: String,
                          @Path("order_id") order_id: Long):
            Observable<EditOrder>

    @FormUrlEncoded
    @POST("orders/edit/{order_id}.json")/*{company_id}*/
    fun EditOrder(        @Field("approve") approve: String,
                          @Path("order_id") order_id: Long):
            Observable<EditOrder>
    @FormUrlEncoded
    @POST("users/changepassword/{id}.json")/*{company_id}*/
    fun ChangePassword(        @Field("password") password: String,
                          @Path("id") user_id: Long):
            Observable<EditOrder>

    @GET("orders/getOrderForEmployee.json")/*{company_id}*/
    fun GetMyorders():
            Observable<Myordersdata>



    @GET("wserv?page=16")/*{company_id}*/
    fun GetPartnersData():
            Observable<List<PartnersModel>>


    @FormUrlEncoded
    @POST("users/add.json")
    abstract fun userregister(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("mobile") mobile: String,
        @Field("user_group_id") groupid: String,
        @Field("agency_id") agency: String,
        @Field("active") active: String,
        @Field("email_verified") email_verified: String


    ): Observable<LoginModel>

    @FormUrlEncoded
    @POST("transactions/add.json")/*{company_id}*/
    fun Transactions(
        @Field("office_id") useris: String,
        @Field("value") value: String,
        @Field("mobile") mobile: String


    ):
            Observable<Trans>

}