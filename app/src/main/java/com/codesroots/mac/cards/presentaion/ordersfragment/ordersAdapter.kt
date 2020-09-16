package com.codesroots.mac.cards.presentaion.ordersfragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.OrdersItemAdapterBindingImpl
import com.codesroots.mac.cards.databinding.ReportItemBinding
import com.codesroots.mac.cards.models.Myorders
import com.codesroots.mac.cards.models.Report
import com.codesroots.mac.cards.presentaion.ClickHandler
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel

class ordersAdapter (var viewModel: MainViewModel, var context : Context?, var data: List<Myorders>?) : RecyclerView.Adapter<CustomViewHolder>() {

    private var myClipboard: ClipboardManager? = null
    private var myClip: ClipData? = null
    override fun getItemCount(): Int {

        return  data!!.size

    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {

        p0.bind(viewModel,context,data!![p1],this)

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {

        val  binding: OrdersItemAdapterBindingImpl = DataBindingUtil.inflate (
            LayoutInflater.from(p0.context),
            R.layout.orders_item_adapter,p0,false)
        myClipboard = context!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?;

        binding.tvUnpaid.setOnClickListener {
            myClip = ClipData.newPlainText("text", binding.tvUnpaid.text);
            myClipboard?.setPrimaryClip(myClip!!);

            Toast.makeText(context, "تم النسخ", Toast.LENGTH_SHORT).show();
        }
        return  CustomViewHolder(binding)
    }


}
class CustomViewHolder (
    private val binding: OrdersItemAdapterBindingImpl
) : RecyclerView.ViewHolder(binding.root){

    fun bind(
        viewModel: MainViewModel,
        context: Context?,
        data: Myorders,adapter: ordersAdapter
    ) {


        binding.listener = ClickHandler()
binding.viewmodel = viewModel
        binding.data = data
        binding.context = context as MainActivity?
    }
}

