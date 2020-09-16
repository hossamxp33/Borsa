package com.codesroots.mac.cards.presentaion.myoffices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.ActivityPaymentBinding
import com.codesroots.mac.cards.databinding.MainFragmentBinding
import com.codesroots.mac.cards.databinding.MyofficeLayoutBinding
import com.codesroots.mac.cards.presentaion.ClickHandler
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.orders_fragment.*

class myofficesFragment : Fragment() {


    lateinit var MainAdapter: myofficesadapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: MyofficeLayoutBinding =
            DataBindingUtil.inflate(inflater,R.layout.myoffice_layout, container,false)
        view.listener =  ClickHandler()
        view.context =  context as MainActivity?

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        view.viewModel =  viewModel

        viewModel.GetMyOffices();

        viewModel.LoginResponseLD?.observe(this, androidx.lifecycle.Observer {
            MainAdapter = myofficesadapter(viewModel, context, it)
            recylere.layoutManager = LinearLayoutManager(context);
            recylere.adapter = MainAdapter;
            if (it.size > 0)
            view.data = it.first()

            MainAdapter.notifyDataSetChanged()

        })

        return view.root;

    }

}

