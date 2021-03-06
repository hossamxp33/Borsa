package com.codesroots.mac.cards.presentaion.mainfragment.viewmodel

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.codesroots.mac.cards.models.*
import com.codesroots.mac.cards.models.Currency
import com.codesroots.mac.firstkotlon.DataLayer.Repo.DataRepo
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BaseObservable
import android.graphics.Color


class BindedValue : BaseObservable() {

    var value: Double = 0.toDouble()


    var setValue: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

     override   fun afterTextChanged(s: Editable) {
            value = java.lang.Double.parseDouble(s.toString())
        }
    }
}
/////// set Stock image statue /////
@BindingAdapter("app:imageStock")

fun setimageStock(imageView: AppCompatImageView, resource: String?) {
    when (resource){
        "-1" ->   imageView.setImageDrawable(
            ContextCompat.getDrawable(
                imageView.context!!, // Context
                com.codesroots.mac.cards.R.drawable.download // Drawable
            )
        )
        "1" ->   imageView.setImageDrawable(
            ContextCompat.getDrawable(
                imageView.context!!, // Context
                com.codesroots.mac.cards.R.drawable.upload // Drawable
            )
        )
        else -> { // Note the block
            // Display an image on image view from drawable
            imageView.setImageDrawable(
                ContextCompat.getDrawable(
                    imageView.context!!, // Context
                    com.codesroots.mac.cards.R.drawable.refresh // Drawable
                )
            )   }
    }

}



@BindingAdapter("text_color")
/////// set Stock image statue /////
fun setTextStock(text: TextView, color: String?) {
    when (color){
        "-1" ->
         text.setTextColor(Color.parseColor("#ef1919"))


        "1" ->           text.setTextColor(Color.parseColor("#1E75DE"))

        else -> { // Note the block
            // Display an image on image view from drawable
            text.setTextColor(Color.parseColor("#9C9898"))
        }
    }

}
@BindingAdapter("app:imageResource")
fun setImageResource(imageView: AppCompatImageView, resource: String?) {
    Glide.with(imageView.context).load(resource).into(imageView)
}
@BindingAdapter("app:imageResourcee")
fun setImageResourcee(imageView: AppCompatImageView, resource: String?) {
    Glide.with(imageView.context).load(resource).into(imageView)
}
@BindingAdapter("app:datetext")
fun setDatetext(text:TextView,resource: String?) {

    val myFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    val dateObj: Date

    dateObj = myFormat.parse(resource)
    val timestamp = dateObj.getTime().toString()//  //Example -> in ms
    val fromServer = SimpleDateFormat("yyyy-MM-dd")
    val dateString = fromServer.format(Date(java.lang.Long.parseLong(timestamp)))


    text.text = dateString

}

class MainViewModel : ViewModel() {

    var DateRepoCompnay: DataRepo = DataRepo()
     var mCompositeDisposable = CompositeDisposable()

    var SpinnerData : Currency? = null

    var BorsaResponseLD : MutableLiveData<List<StockData>>? = null
    var TextSliderDataResponseLD: MutableLiveData<List<SliderDat>>? = null
       var GoldResponseLD : MutableLiveData<List<Gold_Salary_News_Data>>? = null
    var CurrencyResponseLD : MutableLiveData<ArrayList<Currency>>? = null
    var AdsResponseLD : MutableLiveData<List<Result>>? = null
    var SendMassageLD : MutableLiveData<Int>? = null
    var LoginLD : MutableLiveData<Int>? = null
    var ValidationLD : MutableLiveData<Int>? = null
    var  MazadResponseLD :MutableLiveData<List<Result>>? = null

    init {

        BorsaResponseLD = MutableLiveData()
        TextSliderDataResponseLD = MutableLiveData()
        GoldResponseLD = MutableLiveData()
        CurrencyResponseLD = MutableLiveData()
        AdsResponseLD = MutableLiveData()
        SendMassageLD= MutableLiveData()
        LoginLD= MutableLiveData()
        ValidationLD= MutableLiveData()
        MazadResponseLD= MutableLiveData()
    }



  fun  get_borca_data(key:String){
    DateRepoCompnay.Get_Borsa_Data(key,BorsaResponseLD)
}
    //SendMassage
    fun  post_massage(key:String ,auth:String ,name:String ,number:String ,title:String ,massage:String){
        DateRepoCompnay.SendMassage(key,auth,name,number,title,massage,SendMassageLD)
    }

    //Login
    fun  Login(key:String ,card_number : String,password: String ){
        DateRepoCompnay.Login(key,card_number,password,LoginLD)
    }


    ////////GetValidation
    fun  GetValidation(key:String ,auth : String ){
        DateRepoCompnay.GetValidation(key,auth,ValidationLD)
    }
//Get_SliderTextData/////////////////////////////////
fun  get_TextSliderData(){
    DateRepoCompnay.Get_TextSliderData(TextSliderDataResponseLD)
}
    fun  Get_AdsDataData(){
        DateRepoCompnay.Get_AdsData(AdsResponseLD)
    }

   ////// GetMazad
   fun  GetMazadData(key:String){
       DateRepoCompnay.GetMazad(key,MazadResponseLD)
   }

///////////// gold
fun  get_GoldData(){
    DateRepoCompnay.Get_Gold_Data(GoldResponseLD)
}

    ////salary
    fun  get_SalaryData(){
        DateRepoCompnay.Get_Salary_Data(GoldResponseLD)
    }





    ////Gaz
    fun  get_GazData(){
        DateRepoCompnay.Get_GazPriceData(GoldResponseLD)
    }
    fun  Get_Currency_Data(key:String){
        DateRepoCompnay.Get_Currency_Data(key,CurrencyResponseLD)
    }

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.dispose()
        mCompositeDisposable.clear()

    }
}