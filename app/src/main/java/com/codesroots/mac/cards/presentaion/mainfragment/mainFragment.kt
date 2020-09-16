package com.codesroots.mac.cards.presentaion.mainfragment

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.transition.Fade
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.codesroots.mac.cards.DataLayer.helper.PreferenceHelper
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.MainFragmentBinding
import com.codesroots.mac.cards.presentaion.mainfragment.Adapter.MainAdapter
import com.codesroots.mac.cards.presentaion.mainfragment.Adapter.SliderAdapter
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_adapter.*
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*
import android.view.Window as Window1

class mainFragment  : Fragment(){

    lateinit var MainAdapter: MainAdapter
    lateinit var viewModel: MainViewModel
    private var currentPage = 0
    private var NUM_PAGES = 0
    private var pager: ViewPager? = null
    var  text : TextView? = null
    var  recyclerView : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view:MainFragmentBinding =
            DataBindingUtil.inflate(inflater,R.layout.main_fragment, container,false)
        val typeface = Typeface.createFromAsset(getContext()!!.assets, "fonts/DroidKufi_Regular.ttf")

        pager = view.pagerr
        recyclerView = view.recyler
        viewModel =   ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getcompanyData()
       // viewModel.getMyBalance()
        viewModel.GetMyImages(PreferenceHelper.getToken())
        viewModel.CompanyResponseLD?.observe(this , Observer {
            MainAdapter = MainAdapter( viewModel,context,it.companies!!)
            view.recyler.layoutManager = GridLayoutManager(context,2)

            view.recyler.adapter = MainAdapter;
            //lastvalue.append(it.usercredit.toString())
            lastvalue.text =  it.usercredit.toString()
            namevalue.text = PreferenceHelper.getUsername()

        })

        viewModel.SliderDataResponseLD?.observe(this , Observer {

            pager!!.offscreenPageLimit = 3
          //  pager!!.pageMargin = 20
            pager!!.clipChildren = false
            pager!!.clipToPadding = false
         //   pager!!.setPadding(100, 0, 50, 0)

            view.pagerr.adapter = it?.let { it1 -> SliderAdapter(activity!!, it1) }
            indicator.setViewPager(view.pagerr)

            it?.size?.let { it1 -> init(it1) }

        })


        animation()
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
        val ttb = AnimationUtils.loadAnimation(context, R.anim.ttb)
        pager!!.animation = ttb

    }


}


