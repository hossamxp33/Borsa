package com.codesroots.mac.cards.presentaion.mainfragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.provider.Settings.Secure;

import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.MainFragmentBinding
import com.codesroots.mac.cards.models.Currency
import com.codesroots.mac.cards.models.StockData
import com.codesroots.mac.cards.presentaion.ClickHandler
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.Adapter.MainAdapter
import com.codesroots.mac.cards.presentaion.mainfragment.Adapter.SliderAdapter
import com.codesroots.mac.cards.presentaion.mainfragment.Adapter.TextSliderAdapter
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_fragment.*
import org.jetbrains.anko.view
import java.util.*



class mainFragment  : Fragment(){

    lateinit var MainAdapter: MainAdapter
    lateinit var TextPager : TextSliderAdapter
    lateinit var viewModel: MainViewModel
    private var currentPage = 0
    private var NUM_PAGES = 0
    private var pager: ViewPager? = null
    var  text : TextView? = null
    internal var timer = Timer()

    var  recyclerView : RecyclerView? = null
    var data : List<StockData> ? =null
    internal  var maincontext : Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view:MainFragmentBinding =
            DataBindingUtil.inflate(inflater,
                com.codesroots.mac.cards.R.layout.main_fragment, container,false)
        val typeface = Typeface.createFromAsset(getContext()!!.assets, "fonts/DroidKufi_Regular.ttf")
        val android_id = Secure.getString(getContext()?.getContentResolver(), Secure.ANDROID_ID);

    //      MainActivity().appBarLayout2.visibility = View.GONE
        val layout = (context as MainActivity).appBarLayout2
        layout.visibility = View.VISIBLE

        view.context = context as MainActivity
         view.listener = ClickHandler()
            maincontext = context as MainActivity
        pager = view.pagerr
        recyclerView = view.recyler
        viewModel =   ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.get_borca_data(android_id)
        viewModel.get_TextSliderData()
        viewModel.Get_AdsDataData()

        viewModel.TextSliderDataResponseLD?.observe(this , Observer {
            TextPager = TextSliderAdapter(activity!!,it)
            view.topPager.adapter = TextPager
          //  indicator.setViewPager(view.topPager)
            //  pager!!.pageMargin = 20
            pager!!.clipChildren = false
            pager!!.clipToPadding = false
            //   pager!!.setPadding(100, 0, 50, 0)
            view.topPager.adapter = it?.let { it1 -> TextSliderAdapter(activity!!, it1) }
          //  indicator.setViewPager(view.topPager)
            it?.size?.let { it1 -> init(it1) }

        })
        viewModel.BorsaResponseLD?.observe(this , Observer { it ->
            MainAdapter = MainAdapter( viewModel,context, it!!)
            view.recyler.layoutManager = LinearLayoutManager(context)
            view.recyler.adapter = MainAdapter;
            data = it

        })
        timer.scheduleAtFixedRate(
            object : TimerTask() {
                @SuppressLint("CheckResult")
                override fun run() {

    if (data != null) {
    viewModel.get_borca_data(android_id)
         }

                }
            },
            0, 5000
        )

        viewModel.AdsResponseLD?.observe(this , Observer {
            pager!!.offscreenPageLimit = 3
            view.pagerr.adapter = it?.let { it1 -> SliderAdapter(activity!!, it1) }
            indicator.setViewPager(view.pagerr)

            //  pager!!.pageMargin = 20
            pager!!.clipChildren = false
            pager!!.clipToPadding = false
         //   pager!!.setPadding(100, 0, 50, 0)

            view.pagerr.adapter = it?.let { it1 -> SliderAdapter(activity!!, it1) }

            indicator.setViewPager(view.pagerr)
            it?.size?.let { it1 -> init(it1) }

        })



        return view.root;


    }
    private fun init(size: Int) {
        val density = getResources().getDisplayMetrics().density
        indicator.setRadius(4 * density)
        NUM_PAGES = size
        val handler = Handler()
        val Update:Runnable = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            pager?.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 4000, 10000)
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(pos: Int) {}
        })
    }
    private fun animation(){
        val ttb = AnimationUtils.loadAnimation(context, com.codesroots.mac.cards.R.anim.ttb)
        pager!!.animation = ttb

    }


}


