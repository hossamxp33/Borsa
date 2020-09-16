package com.codesroots.mac.cards.presentaion.reportsFragment.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.ReportItemBinding
import com.codesroots.mac.cards.models.Packagess
import com.codesroots.mac.cards.models.Report
import com.codesroots.mac.cards.presentaion.ClickHandler
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import android.R.attr.label
import android.content.ClipData
import androidx.core.content.ContextCompat.getSystemService
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast


class report_adapters ( var viewModel: MainViewModel,var context :Context?,var data: List<Report>) : RecyclerView.Adapter<CustomViewHolder>()
{



    override fun getItemCount(): Int {

        return  data.size

    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {

        p0.bind(viewModel,context,data[p1])

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
//        val layoutInflater  = LayoutInflater.from(p0.context);
//        val cellforrow = layoutInflater.inflate(R.layout.main_adapter,p0,false);

//        val layoutParams = cellforrow.getLayoutParams()
//        layoutParams.height = (p0.getHeight() /  2).toInt()
//        layoutParams.width = (p0.getWidth() /  2.5).toInt()
//        cellforrow.setLayoutParams(layoutParams)

        val  binding: ReportItemBinding = DataBindingUtil.inflate (LayoutInflater.from(p0.context),R.layout.report_item,p0,false)

        return  CustomViewHolder(binding)
    }


}
class CustomViewHolder (
    private val binding:ReportItemBinding
) : RecyclerView.ViewHolder(binding.root)
{

    fun bind(
        viewModel:MainViewModel,
        context: Context?,
        data: Report
    ) {

        binding.listener = ClickHandler()
        binding.data = data
        binding.context = context as MainActivity?
    }

}