package com.codesroots.mac.cards.presentaion.mainfragment.viewmodel

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.codesroots.mac.cards.DataLayer.helper.PreferenceHelper
import com.codesroots.mac.cards.models.*
import com.codesroots.mac.firstkotlon.DataLayer.Repo.DataRepo

import io.reactivex.disposables.CompositeDisposable

@BindingAdapter("app:imageResource")
fun setImageResource(imageView: AppCompatImageView, resource: String?) {
    Glide.with(imageView.context).load(resource).into(imageView)
}
@BindingAdapter("app:imageResourcee")
fun setImageResourcee(imageView: AppCompatImageView, resource: String?) {
    Glide.with(imageView.context).load(resource).into(imageView)
}
class MainViewModel : ViewModel() {

    var DateRepoCompnay: DataRepo = DataRepo()
     var mCompositeDisposable = CompositeDisposable()

    var CompanyResponseLD : MutableLiveData<List<CompanyDatum>>? = null
    var MyBalanceResponseLD : MutableLiveData<MyBalance>? = null
    var SliderDataResponseLD : MutableLiveData<List<SliderElement>>? = null
    var BuyPackageResponseLD : MutableLiveData<Buypackge>? = null
    var ReportDailyResponseLD : MutableLiveData<List<Report>>? = null
    var ReportHistroyResponseLD : MutableLiveData<List<ReportDaily>>? = null

    init {
        CompanyResponseLD = MutableLiveData()
        BuyPackageResponseLD = MutableLiveData()
        MyBalanceResponseLD = MutableLiveData()
        SliderDataResponseLD = MutableLiveData()
        ReportDailyResponseLD = MutableLiveData()
        ReportHistroyResponseLD = MutableLiveData()
        mCompositeDisposable  = CompositeDisposable()
    }


    fun getMyBalance(){
        DateRepoCompnay.GetMyBalance(MyBalanceResponseLD)
    }

    fun getcompanyData(){
        DateRepoCompnay.GetData(CompanyResponseLD)
                    }

    fun getPackageDetails(id:String){
        DateRepoCompnay.GetPackageDetails(id,CompanyResponseLD)
    }


    fun BuyPackage(id:String,phone:String){

        DateRepoCompnay.BuyPackage(id,PreferenceHelper.getUserId().toString(),phone,BuyPackageResponseLD,mCompositeDisposable)

    }
    fun PrintReport(oopo:String,auth:String){
        DateRepoCompnay.PrintReport(oopo,auth,BuyPackageResponseLD)
    }
    fun GetMyDeialyReport(auth:String){
        DateRepoCompnay.GetMyDeialyReport(auth,ReportDailyResponseLD)
    }
    fun GetMyDatesReport(auth:String,from:String,to:String){
        DateRepoCompnay.GetMyMonthReport(auth,from,to,ReportDailyResponseLD)
    }
    fun GetMyImages(auth:String){
        DateRepoCompnay.GetMyImages(SliderDataResponseLD)
    }

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.dispose()
        mCompositeDisposable.clear()

    }
}