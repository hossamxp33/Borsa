package com.codesroots.mac.cards.presentaion.borsadetails

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.codesroots.mac.cards.databinding.BorsaDetailsBinding
import com.codesroots.mac.cards.databinding.MainFragmentBinding
import com.codesroots.mac.cards.models.StockData
import com.codesroots.mac.cards.presentaion.ClickHandler
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.Adapter.MainAdapter
import com.codesroots.mac.cards.presentaion.mainfragment.Adapter.TextSliderAdapter
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import io.fabric.sdk.android.services.concurrency.AsyncTask.init
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*

class BorsaDetailsFragment  : Fragment(){


    lateinit var viewModel: MainViewModel

    private var pager: ViewPager? = null
    var  text : TextView? = null
    var data : StockData? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view: BorsaDetailsBinding =
            DataBindingUtil.inflate(inflater,
                com.codesroots.mac.cards.R.layout.borsa_details, container,false)
        val typeface = Typeface.createFromAsset(getContext()!!.assets, "fonts/DroidKufi_Regular.ttf")
        val android_id = Settings.Secure.getString(getContext()?.getContentResolver(), Settings.Secure.ANDROID_ID);

        //      MainActivity().appBarLayout2.visibility = View.GONE
        data  = arguments?.getParcelable("dataa")

        view.context = context as MainActivity
        view.listener = ClickHandler()

        viewModel =   ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.get_borca_data(android_id)

        view.data = data




        return view.root;


    }




}


