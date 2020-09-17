package com.codesroots.mac.cards.presentaion.companydetails.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.CompanyDetailsBinding
import com.codesroots.mac.cards.databinding.MainFragmentBinding

import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import com.codesroots.mac.cards.presentaion.reportsFragment.adapters.CompanyDetailsAdapter
import kotlinx.android.synthetic.main.main_fragment.view.*


class CompanyDetails  : Fragment() {

    lateinit var MainAdapter: CompanyDetailsAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        var view: CompanyDetailsBinding =
            DataBindingUtil.inflate(inflater,R.layout.company_details, container,false)


        if (arguments != null) {
            val packageId = arguments?.getString("packageId")
            viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
            viewModel.getPackageDetails(packageId!!)

            viewModel.CompanyDetailsResponseLD?.observe(this, Observer {
                MainAdapter = CompanyDetailsAdapter(viewModel, context, it.data!!)
                view.recyler.layoutManager = LinearLayoutManager(context)
                view.recyler.adapter = MainAdapter;

            })

        }
        return  view.root
    }

}