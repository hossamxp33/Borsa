package com.codesroots.mac.cards.presentaion.mainfragment.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.models.SliderDat
import com.codesroots.mac.cards.presentaion.loudImage
import kotlinx.android.synthetic.main.viewpagerslide.view.*





class TextSliderAdapter(activity: FragmentActivity, sliders: List<SliderDat>) : PagerAdapter() {

    private val context: Context
    private val slidersData: List<SliderDat> = sliders

    init {
        this.context = activity
    }

    override fun getCount(): Int {
        return  slidersData.size
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1 //To change body of created functions use File | Settings | File Templates.
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.viewpagerslide, container, false)
        view.Bdetails.text =slidersData.get(position).Details
        view.NDate.text =slidersData.get(position).NDate


        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
//    override fun getPageWidth(position: Int): Float {
//        return 0.93f
//    }
}
