package com.codesroots.mac.firstkotlon.DataLayer.Repo

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.codesroots.mac.cards.DataLayer.ApiService.ApiClient
import com.codesroots.mac.cards.DataLayer.helper.PreferenceHelper
import com.codesroots.mac.cards.models.*
import com.codesroots.mac.firstkotlon.DataLayer.ApiService.APIServices

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class  DataRepo {

    @SuppressLint("CheckResult")
    fun Get_Borsa_Data(key:String ,livedata: MutableLiveData<List<StockData>>?) {

        getServergetway().GetBorsaData(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->
                    livedata?.postValue(books)
                },
                { error ->

                }
            )
    }


//GetTextSliderData
fun Get_TextSliderData(livedata: MutableLiveData<List<SliderDat>>?) {

    getServergetway().GetTextSliderData()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map { data -> data }
        .subscribe(
            { books ->
                livedata?.postValue(books)
            },
            { error ->

            }
        )
}

    //GetGoldData
    fun Get_Gold_Data(livedata: MutableLiveData<List<Gold_Salary_News_Data>>?) {

        getServergetway().GetGoldPriceData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->
                    livedata?.postValue(books)
                },
                { error ->

                }
            )
    }
    //GetGoldData
    fun Get_GazPriceData(livedata: MutableLiveData<List<Gold_Salary_News_Data>>?) {

        getServergetway().GetGazPriceData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->
                    livedata?.postValue(books)
                },
                { error ->

                }
            )
    }
/// salary
fun Get_Salary_Data(livedata: MutableLiveData<List<Gold_Salary_News_Data>>?) {

    getServergetway().GetSalaryData()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map { data -> data }
        .subscribe(
            { books ->
                livedata?.postValue(books)
            },
            { error ->

            }
        )
}
    /// Get_Currency_Data
    fun Get_Currency_Data(key:String ,livedata: MutableLiveData<ArrayList<Currency>>?) {

        getServergetway().GetCurrency(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->
                    livedata?.postValue(books)
                },
                { error ->

                }
            )
    }
    @SuppressLint("CheckResult")
    fun getServergetway () : APIServices
    {
        return ApiClient.getClient().create(APIServices::class.java)
    }
}

