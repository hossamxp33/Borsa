package com.codesroots.mac.cards.presentaion.menuitemsfragments.news

import android.os.Bundle
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
import com.codesroots.mac.cards.databinding.GazPriceFragmentBinding
import com.codesroots.mac.cards.databinding.GlobalNewsFragmentBinding
import com.codesroots.mac.cards.models.StockData
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import com.codesroots.mac.cards.presentaion.menuitemsfragments.gaz.GazPriceAdapter

class NewsFragment: Fragment(){

    lateinit var MainAdapter: NewsAdapter
    lateinit var viewModel: MainViewModel
    var text: TextView? = null
    var recyclerView: RecyclerView? = null
    var data: StockData? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: GlobalNewsFragmentBinding =
            DataBindingUtil.inflate(
                inflater, com.codesroots.mac.cards.R.layout.global_news_fragment, container, false
            )

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.get_TextSliderData()

        viewModel.TextSliderDataResponseLD?.observe(this , Observer {
            MainAdapter = NewsAdapter( viewModel,context, it!!)
            view.recyler.adapter = MainAdapter;
            view.recyler.layoutManager = LinearLayoutManager(context)

        })


        return view.root;


    }



}

