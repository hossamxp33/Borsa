package com.codesroots.mac.cards.presentaion.transferfragment

import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codesroots.mac.cards.databinding.GoldPriceFragmentBinding
import com.codesroots.mac.cards.databinding.TransferCurrencyFragmentBinding
import com.codesroots.mac.cards.models.Currency
import com.codesroots.mac.cards.models.StockData
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import com.codesroots.mac.cards.presentaion.menuitemsfragments.goldprice.GoldPriceAdapter

class TransferFragment  : Fragment(){

    lateinit var viewModel: MainViewModel
    var text: TextView? = null
    var recyclerView: RecyclerView? = null
    var data: Currency? = null
    var first_data : Currency ?=  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: TransferCurrencyFragmentBinding =
            DataBindingUtil.inflate(
                inflater, com.codesroots.mac.cards.R.layout.transfer_currency_fragment, container, false
            )
        val android_id = Settings.Secure.getString(getContext()?.getContentResolver(), Settings.Secure.ANDROID_ID);
        data  = arguments?.getParcelable("dataa")
        first_data = arguments?.getParcelable("first_data")
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.Get_Currency_Data(android_id)
        view.context = context as MainActivity

        viewModel.CurrencyResponseLD?.observe(this , Observer {

            view.context = context as MainActivity
            view.data = data
            view.firstdata = first_data


            val svalue = (data!!.CPrice).toFloat()

            view.calculateBtn.setOnClickListener {

                view.textView12.text = java.lang.Float.valueOf(Integer.valueOf(view.textView25.text.toString().toInt()) * svalue).toString()
            }


        })


        return view.root;


    }



}

