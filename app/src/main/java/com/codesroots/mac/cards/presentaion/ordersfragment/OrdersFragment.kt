package com.codesroots.mac.cards.presentaion.ordersfragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.codesroots.mac.cards.DataLayer.helper.PreferenceHelper
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import com.codesroots.mac.cards.presentaion.reportsFragment.adapters.report_adapters
import kotlinx.android.synthetic.main.main_fragment.view.*
import kotlinx.android.synthetic.main.orders_fragment.*
import kotlinx.android.synthetic.main.orders_fragment.view.*
import kotlinx.android.synthetic.main.report_fragment.*
import kotlinx.android.synthetic.main.report_fragment.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.to

class OrdersFragment : Fragment() {

    lateinit var MainAdapter: ordersAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.orders_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.GetMyorders();
        
        viewModel.OrdersResponseLD?.observe(this , androidx.lifecycle.Observer {
            MainAdapter = ordersAdapter(viewModel, context, it)
           recylere.layoutManager = LinearLayoutManager(context);
           recylere.adapter = MainAdapter;
            MainAdapter.notifyDataSetChanged()

        })





        return view;


    }

}

