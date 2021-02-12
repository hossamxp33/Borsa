package com.codesroots.mac.firstkotlon.DataLayer.ApiService

import com.codesroots.mac.cards.models.*

import io.reactivex.Observable
import retrofit2.http.*


interface APIServices {



@GET("iqws.php?o=3")/*{company_id}*/
    fun GetBorsaData(@Query("k") identfer_key: String):
        Observable<List<StockData>>


    @GET("iqws.php?o=31")/*{company_id}*/
    fun GetAdsData(): Observable<Ads>


///////// Massages
@GET("iqws.php?o=15")
 fun SendMassage(@Query("k") identfer_key: String,
                 @Query("val")  auth  : String
         ): Observable<Int>



// https://www.iqborsa.com/iqws.php?k=jnm&o=1&val=0,832582043061,218248
    /*
    "?k=\(UIDevice.current.identifierForVendor!.uuidString)
    &o=15
    &val=\(defualts.integer(forKey: "auth")),
    \(nameText.text!),
    \(PhoneText.text!),
    \(HeadLineText.text!),
    \(message.text!)
    ,0"
     */
    ///////// Login
    @GET("iqws.php?o=1&i=1")
    fun Login(@Query("k") identfer_key: String,
              @Query("val") password : String ): Observable<Int>

///////////////////////// Check Validation
@GET("iqws.php?o=23")/*{company_id}*/
fun GetValidation(@Query("k") identfer_key: String,
                  @Query("val") auth : String  ): Observable<Int>

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

    /////////    // mazad //////////////
    @GET("iqws.php?o=27")/*{company_id}*/
    fun GetMazad(@Query("k") identfer_key: String):
            Observable<mazad>


}


