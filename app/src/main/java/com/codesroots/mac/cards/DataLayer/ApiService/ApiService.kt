package com.codesroots.mac.firstkotlon.DataLayer.ApiService

import com.codesroots.mac.cards.models.*

import io.reactivex.Observable
import retrofit2.http.*


interface APIServices {



@GET("iqws.php?o=3")/*{company_id}*/
    fun GetBorsaData(@Query("k") identfer_key: String):
        Observable<List<StockData>>



    ///////// Salary //////////////
    @GET("iqws.php?o=4&val=kjhkjhkj,1")/*{company_id}*/
    fun GetSalaryData():
            Observable<List<Gold_Salary_News_Data>>

///// / GetTextSliderData
    @GET("iqws.php?o=4&val=kjhkjhkj,2")/*{company_id}*/
    fun GetTextSliderData():
            Observable<List<SliderDat>>

    ///////// Gold Price //////////////
    @GET("iqws.php?o=4&val=kjhkjhkj,3")/*{company_id}*/
    fun GetGoldPriceData():
            Observable<List<Gold_Salary_News_Data>>

    ///////// Gaz Price //////////////
    @GET("iqws.php?o=4&val=kjhkjhkj,4")/*{company_id}*/
    fun GetGazPriceData():
            Observable<List<Gold_Salary_News_Data>>

    ///////// Currency //////////////
    @GET("iqws.php?o=13")/*{company_id}*/
    fun GetCurrency(@Query("k") identfer_key: String):
            Observable<ArrayList<Currency>>



}


