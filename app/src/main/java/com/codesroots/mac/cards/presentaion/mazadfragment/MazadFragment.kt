package com.codesroots.mac.cards.presentaion.mazadfragment

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
import com.codesroots.mac.cards.databinding.MazadFragmentBinding
import com.codesroots.mac.cards.databinding.SalaryNewsFragmentBinding
import com.codesroots.mac.cards.models.Result
import com.codesroots.mac.cards.models.StockData
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.Adapter.MainAdapter
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import com.codesroots.mac.cards.presentaion.menuitemsfragments.salary.SalaryAdapter

class MazadFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    var text: TextView? = null
    var data:Result? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: MazadFragmentBinding =
            DataBindingUtil.inflate(
                inflater, com.codesroots.mac.cards.R.layout.mazad_fragment, container, false
            )
        val android_id = Settings.Secure.getString(getContext()?.getContentResolver(), Settings.Secure.ANDROID_ID);

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.GetMazadData(android_id)
        view.context = context as MainActivity

        viewModel.MazadResponseLD?.observe(this , Observer {

         view.data = data
         view.name.text = it.get(0).Headtxt
            view.price.text = it.get(0).Price1
            view.total.text = it.get(0).Price2
            view.totalsales.text = it.get(0).TPrice



        })


        return view.root;


    }



}



