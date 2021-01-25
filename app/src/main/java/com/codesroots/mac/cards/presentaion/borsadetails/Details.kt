package com.codesroots.mac.cards.presentaion.borsadetails

import android.graphics.Typeface
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.codesroots.mac.cards.databinding.BorsaDetailsBinding
import com.codesroots.mac.cards.databinding.DetailsBinding
import com.codesroots.mac.cards.models.StockData
import com.codesroots.mac.cards.presentaion.ClickHandler
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import android.widget.CompoundButton
import android.widget.Switch
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.codesroots.mac.cards.presentaion.mainfragment.Adapter.MainAdapter
import com.codesroots.mac.cards.presentaion.menuitemsfragments.goldprice.GoldPriceAdapter
import com.google.android.gms.analytics.internal.zzy.e
import kotlinx.android.synthetic.main.details.*
import java.lang.Exception


class Details  : Fragment(){


    lateinit var viewModel: MainViewModel

    private var pager: ViewPager? = null
    var  text : TextView? = null
    var data : StockData? =null
    var price: Float? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view: DetailsBinding =
            DataBindingUtil.inflate(inflater,
                com.codesroots.mac.cards.R.layout.details, container,false)
        val typeface = Typeface.createFromAsset(getContext()!!.assets, "fonts/DroidKufi_Regular.ttf")
        val android_id = Settings.Secure.getString(getContext()?.getContentResolver(), Settings.Secure.ANDROID_ID);

        //      MainActivity().appBarLayout2.visibility = View.GONE
        data  = arguments?.getParcelable("dataa")
        view.context = context as MainActivity
        view.listener = ClickHandler()
        viewModel =   ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.BorsaResponseLD?.observe(this , Observer {

        })
        view.data = data



        view.switch1.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                switch1.text = "طلب"
                view.price.text  = data!!.SPrice.toFloat().toString()
            }else{
                switch1.text = "عرض"
                view.price.text  = (data!!.Bprice).toFloat().toString()


            }
        })


         val dinar = (data!!.Sqty).toDouble()

        view.calculateBtn.setOnClickListener {

            try{
                if (view.IQValue.text.isEmpty()){
                    (view.IQValue as TextView).text = (Integer.valueOf(view.number.text.toString().toInt()) * dinar).toString()
                    (view.USDValue as TextView).text = ((view.number.text.toString().toFloat() * view.price.text.toString().toDouble()) / dinar ).toString()

                     view.number.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {

                        }
                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        }
                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                            if (view.number.text.isEmpty()){
                                view.IQValue.text.clear()
                                view.USDValue.text.clear()
                            }

                        }
                    })

                }
                if (view.number.text.isEmpty())
                {
                    (view.number as TextView).text = (Integer.valueOf(view.IQValue.text.toString().toInt()) / dinar).toString()
                    (view.USDValue as TextView).text = ((dinar / view.price.text.toString().toDouble())  ).toString()


                    view.IQValue.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {
                            if (view.IQValue.text.isEmpty()){
                                view.number.text.clear()
                                view.USDValue.text.clear()
                            }
                        }
                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        }
                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                            if (view.IQValue.text.isEmpty()){
                                view.number.text.clear()
                                view.USDValue.text.clear()
                            }
                        }
                    })
                }


            } catch  (e: Exception) {
                // handler
            }

/*
لو كتبت في الدولار


 */

        }




        return view.root;


    }




}