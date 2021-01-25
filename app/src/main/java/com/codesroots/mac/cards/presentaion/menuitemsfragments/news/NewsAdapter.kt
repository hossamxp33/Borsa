package com.codesroots.mac.cards.presentaion.menuitemsfragments.news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.GazPriceItemBinding
import com.codesroots.mac.cards.databinding.GlobalNewsItemBinding
import com.codesroots.mac.cards.models.SliderDat
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel


class NewsAdapter (var viewModel: MainViewModel, var context : Context?, var data:List<SliderDat>) : RecyclerView.Adapter<CustomViewHolder>() {
    override fun getItemCount(): Int {

        return  data.size
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        p0.bind(viewModel,context,data.get(p1))

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {

        val  binding: GlobalNewsItemBinding = DataBindingUtil.inflate (
            LayoutInflater.from(p0.context), R.layout.global_news_item,p0,false)
        return  CustomViewHolder(binding)

    }


}
class CustomViewHolder (
    private val binding: GlobalNewsItemBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(viewModel: MainViewModel, context: Context?, data: SliderDat) {

        binding.data = data
        binding.context = context as MainActivity?
    }

}