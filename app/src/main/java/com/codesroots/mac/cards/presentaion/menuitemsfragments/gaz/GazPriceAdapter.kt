package com.codesroots.mac.cards.presentaion.menuitemsfragments.gaz

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.GazPriceItemBinding
import com.codesroots.mac.cards.databinding.MainAdapterBinding
import com.codesroots.mac.cards.databinding.SalaryNewsItemBinding
import com.codesroots.mac.cards.models.Gold_Salary_News_Data
import com.codesroots.mac.cards.models.SliderDat
import com.codesroots.mac.cards.models.StockData
import com.codesroots.mac.cards.presentaion.ClickHandler
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel

class GazPriceAdapter (var viewModel: MainViewModel, var context : Context?, var data:List<Gold_Salary_News_Data>) : RecyclerView.Adapter<CustomViewHolder>() {
    override fun getItemCount(): Int {

        return  data.size
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        p0.bind(viewModel,context,data.get(p1))

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {

        val  binding: GazPriceItemBinding = DataBindingUtil.inflate (
            LayoutInflater.from(p0.context), R.layout.gaz_price_item,p0,false)
        return  CustomViewHolder(binding)

    }


}
class CustomViewHolder (
    private val binding: GazPriceItemBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(viewModel: MainViewModel, context: Context?, data: Gold_Salary_News_Data) {

        binding.data = data
        binding.context = context as MainActivity?
    }

}