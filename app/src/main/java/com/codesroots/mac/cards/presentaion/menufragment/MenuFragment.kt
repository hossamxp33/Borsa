package com.codesroots.mac.cards.presentaion.menufragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.SelectionMenuBinding
import com.codesroots.mac.cards.presentaion.ClickHandler
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.Adapter.MainAdapter
import com.codesroots.mac.cards.presentaion.mainfragment.Adapter.SliderAdapter
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*

class MenuFragment : Fragment() {
    lateinit var viewModel: MainViewModel
    private var pager: ViewPager? = null
    private var currentPage = 0
    private var NUM_PAGES = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        var view: SelectionMenuBinding =
            DataBindingUtil.inflate(inflater, R.layout.selection_menu, container,false)
        view.context = context as MainActivity
        view.listener = ClickHandler()
        viewModel =   ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.Get_AdsDataData()
        pager = view.pagerr

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
        return  view.root
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
}