package com.codesroots.mac.cards.presentaion.mytrans



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.MytransactivityBinding
import com.codesroots.mac.cards.presentaion.ClickHandler
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.orders_fragment.*

class mytranActivty : Fragment() {


    lateinit var MainAdapter: mytransAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: MytransactivityBinding =
            DataBindingUtil.inflate(inflater,R.layout.mytransactivity, container,false)
        view.listener =  ClickHandler()
        view.context =  context as MainActivity?

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        view.viewModel =  viewModel

        viewModel.GetMyTrans();

        viewModel.mytransResponseLD?.observe(this, androidx.lifecycle.Observer {
            MainAdapter = mytransAdapter(viewModel, context, it)
            recylere.layoutManager = LinearLayoutManager(context);
            recylere.adapter = MainAdapter;


            MainAdapter.notifyDataSetChanged()

        })





        return view.root;

    }

}

