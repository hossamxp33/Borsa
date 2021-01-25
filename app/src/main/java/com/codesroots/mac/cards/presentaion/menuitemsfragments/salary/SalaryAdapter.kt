package com.codesroots.mac.cards.presentaion.menuitemsfragments.salary

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.MainAdapterBinding
import com.codesroots.mac.cards.databinding.SalaryNewsItemBinding
import com.codesroots.mac.cards.models.Gold_Salary_News_Data
import com.codesroots.mac.cards.models.SliderDat
import com.codesroots.mac.cards.models.StockData
import com.codesroots.mac.cards.presentaion.ClickHandler
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel

class SalaryAdapter (var viewModel: MainViewModel, var context : Context?, var data:List<Gold_Salary_News_Data>) : RecyclerView.Adapter<CustomViewHolder>() {
    override fun getItemCount(): Int {

        return  data.size
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        p0.bind(viewModel,context,data.get(p1))

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {

        val  binding: SalaryNewsItemBinding = DataBindingUtil.inflate (
            LayoutInflater.from(p0.context),
            R.layout.salary_news_item,p0,false)
        val typeface = Typeface.createFromAsset(context!!.assets, "fonts/DroidKufi_Regular.ttf")



        return  CustomViewHolder(binding)

    }


}
class CustomViewHolder (
    private val binding: SalaryNewsItemBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(viewModel: MainViewModel, context: Context?, data: Gold_Salary_News_Data) {

        binding.data = data
        binding.context = context as MainActivity?
    }

}