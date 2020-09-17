package com.codesroots.mac.cards.presentaion.ordersfragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.codesroots.mac.cards.DataLayer.helper.PreferenceHelper
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.ServerFragmentBinding
import com.codesroots.mac.cards.models.CompanyData
import com.codesroots.mac.cards.models.CompanyDatum
import com.codesroots.mac.cards.presentaion.mainfragment.Adapter.SliderAdapter
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.indicator

import kotlinx.android.synthetic.main.server_fragment.view.*
import java.util.*


class OrdersFragment : Fragment() {
    private var currentPage = 0
    private var NUM_PAGES = 0
    private var pager: ViewPager? = null
    var CompanyList:List<CompanyDatum>? = null
    var Companydata:CompanyDatum? = null

    var CompanyDetailsList:List<CompanyDatum>? = null
    var package_id:String? = ""


    //    lateinit var MainAdapter: ordersAdapter
lateinit  var spinner: Spinner
    lateinit  var spinner_type: Spinner

    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment

        var view: ServerFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.server_fragment, container,false)

        pager = view.serverPager
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.GetMyorders();
        viewModel.GetMyImages(PreferenceHelper.getToken())
        view.data = Companydata
        viewModel.getcompanyData()

        viewModel.SliderDataResponseLD?.observe(this , Observer {
            pager!!.offscreenPageLimit = 3
            //  pager!!.pageMargin = 20
            pager!!.clipChildren = false
            pager!!.clipToPadding = false
            //   pager!!.setPadding(100, 0, 50, 0)
            view.serverPager.adapter = it?.let { it1 -> SliderAdapter(activity!!, it1) }
            indicator.setViewPager(view.serverPager)
            it?.size?.let { it1 -> init(it1) }

        })

        spinner = view.orderSpinner
        spinner_type = view.orderTypeSpinner


        viewModel.CompanyResponseLD?.observe(this, Observer {
            CompanyList = it.companies
            val arrayadapter =  CompanyList!!.
                filter { companyDatum -> companyDatum.id == "15" || companyDatum.id == "33" }
                spinner.adapter = activity?.applicationContext?.
                let { it1 -> ArrayAdapter(it1, R.layout.spinner, arrayadapter.map { it.name }) }
        })
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
             val arrayadapter =  CompanyList!!.
            filter { companyDatum -> companyDatum.id == "15" || companyDatum.id == "33" }
            var  com_id = arrayadapter!![position].id
            viewModel.getPackageDetails(com_id!!)

            }}

        viewModel.CompanyDetailsResponseLD?.observe(this , Observer {
             CompanyDetailsList = it.data
            spinner_type.adapter = activity?.applicationContext?.
                let { it1 -> ArrayAdapter(it1, R.layout.spinner, CompanyDetailsList!!.map { it.name }) }


        })
        spinner_type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {


            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                  package_id = CompanyDetailsList!![position].id
            }}
        view.presenter  =  object : Presenter {
            override fun AddClick() {
                showCustomDialog()
  //  Toast.makeText(context, "تم تسجيل البيانات", Toast.LENGTH_SHORT).show()
            }
        }

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

    private lateinit var alertDialog: AlertDialog
    private lateinit var alertDialog2: AlertDialog

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun showCustomDialog() {
        val inflater: LayoutInflater = this.getLayoutInflater()
        val dialogView: View = inflater.inflate(R.layout.dialog_custom_view, null)
        val dialogView2: View = inflater.inflate(R.layout.thanks_dialog, null)

        val cancel_button: Button = dialogView.findViewById(R.id.cancel_button)
        cancel_button.setOnClickListener {
            alertDialog.dismiss()     }

        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context!!,R.style.yourCustomDialog)

        dialogBuilder.setOnDismissListener(object : DialogInterface.OnDismissListener {
            override fun onDismiss(arg0: DialogInterface) {
            }
        })

        dialogBuilder.setView(dialogView)
        alertDialog = dialogBuilder.create();
        alertDialog.show()

        val accept_buy_now_button: Button = dialogView.findViewById(R.id.accept_buy_now)
        accept_buy_now_button.setOnClickListener {
            // Dismiss the popup window
            val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context!!,R.style.LLDialog)


            alertDialog.dismiss()
            dialogBuilder.setView(dialogView2)
            alertDialog2 = dialogBuilder.create();
            alertDialog2.show()

        }
        val done_button: Button = dialogView2.findViewById(R.id.done)
        done_button.setOnClickListener {
            alertDialog2.dismiss()     }
    }
}

