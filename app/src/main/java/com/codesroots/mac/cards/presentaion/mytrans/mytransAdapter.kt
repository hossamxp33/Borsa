package com.codesroots.mac.cards.presentaion.mytrans


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.*

import com.codesroots.mac.cards.models.Datatrans
import com.codesroots.mac.cards.presentaion.ClickHandler
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel


class mytransAdapter (var viewModel: MainViewModel, var context : Context?, var data: List<Datatrans>?) : RecyclerView.Adapter<CustomViewHolder>() {
    override fun getItemCount(): Int {

        return  data!!.size

    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {

        p0.bind(viewModel,context,data!![p1],this)

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {

        val  binding: MytransItemBinding = DataBindingUtil.inflate (
            LayoutInflater.from(p0.context),
            R.layout.mytrans_item,p0,false)

        return  CustomViewHolder(binding)
    }


}
class CustomViewHolder (
    private val binding: MytransItemBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(
        viewModel: MainViewModel,
        context: Context?,
        data: Datatrans, adapter: mytransAdapter
    ) {


        binding.listener = ClickHandler()
        binding.viewmodel = viewModel
        binding.data = data
        binding.context = context as MainActivity?
    }
}

