package com.codesroots.mac.cards.presentaion.menuitemsfragments.salary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.codesroots.mac.cards.databinding.SalaryNewsFragmentBinding
import com.codesroots.mac.cards.models.StockData
import com.codesroots.mac.cards.presentaion.mainfragment.Adapter.MainAdapter
import com.codesroots.mac.cards.presentaion.mainfragment.Adapter.TextSliderAdapter
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel

class SalaryFragment : Fragment() {

    lateinit var MainAdapter: SalaryAdapter
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

        var view: SalaryNewsFragmentBinding =
            DataBindingUtil.inflate(
                inflater, com.codesroots.mac.cards.R.layout.salary_news_fragment, container, false
            )

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.get_SalaryData()

          viewModel.GoldResponseLD?.observe(this , Observer {
            MainAdapter = SalaryAdapter( viewModel,context, it!!)
              view.recyler.adapter = MainAdapter;
              view.recyler.layoutManager = LinearLayoutManager(context)

        })


        return view.root;


    }



}



