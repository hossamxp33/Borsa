package com.codesroots.mac.cards.presentaion.menuitemsfragments.internationalcurrencies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.GoldPriceItemBinding
import com.codesroots.mac.cards.databinding.InternationalCurrencyItemBinding
import com.codesroots.mac.cards.models.Currency
import com.codesroots.mac.cards.models.Gold_Salary_News_Data
import com.codesroots.mac.cards.presentaion.ClickHandler
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import com.codesroots.mac.cards.presentaion.transferfragment.TransferFragment
import com.romellfudi.ussdlibrary.contains
import java.security.AlgorithmConstraints
import java.util.*
import kotlin.collections.ArrayList

class CurrencyAdapter (var viewModel: MainViewModel, var context : Context?, var data:ArrayList<Currency>) : RecyclerView.Adapter<CustomViewHolder>() {

    internal var filteringListResult: ArrayList<Currency>

    init {
        this.filteringListResult = data
    }



    override fun getItemCount(): Int {

        return filteringListResult.size
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        p0.bind(viewModel, context, data.get(p1))

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {

        val binding: InternationalCurrencyItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(p0.context),
            R.layout.international_currency_item,
            p0,
            false
        )
        return CustomViewHolder(binding)

    }

}


class CustomViewHolder (
    private val binding: InternationalCurrencyItemBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(viewModel: MainViewModel, context: Context?, data: Currency) {

        binding.data = data
        binding.context = context as MainActivity?
        binding.viewmodel = viewModel
        binding.listener = ClickHandler()
    }

}


