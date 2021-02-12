package com.codesroots.mac.cards.presentaion.menuitemsfragments.internationalcurrencies

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.provider.Settings
import android.text.TextWatcher
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.codesroots.mac.cards.databinding.InternationalCurrencyFragmentBinding
import com.codesroots.mac.cards.models.Currency
import com.codesroots.mac.cards.models.StockData
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import com.codesroots.mac.cards.presentaion.menuitemsfragments.goldprice.GoldPriceAdapter
import android.text.Editable
import android.widget.*
import androidx.core.content.ContextCompat.getSystemService
import android.location.LocationManager
import android.view.*
import com.codesroots.mac.cards.R
import com.romellfudi.ussdlibrary.contains
import kotlinx.android.synthetic.main.international_currency_fragment.*
import kotlinx.android.synthetic.main.spinner.*
import org.jetbrains.anko.editText


class internationalCurrency: Fragment(){

    lateinit var MainAdapter: CurrencyAdapter
    lateinit var viewModel: MainViewModel
    var text: TextView? = null
    var recyclerView: RecyclerView? = null
      var dataaa: ArrayList <Currency>? = null
    var Cid : String ? = null
    var Cname : String ? = null
    var mExampleList: List <Currency>? = null
    internal  var filteringListResult: ArrayList<Currency>? = null
    var SpinnerData : Currency? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
var textView7 : TextView

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: InternationalCurrencyFragmentBinding =
            DataBindingUtil.inflate(
                inflater, com.codesroots.mac.cards.R.layout.international_currency_fragment, container, false
            )
        val android_id = Settings.Secure.getString(getContext()?.getContentResolver(), Settings.Secure.ANDROID_ID);

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.Get_Currency_Data(android_id)
        viewModel.CurrencyResponseLD?.observe(this , Observer {
            filteringListResult = ArrayList<Currency>()
            dataaa = ArrayList<Currency>()
            filteringListResult = it as ArrayList
            dataaa!!.addAll(filteringListResult!!)

            MainAdapter = CurrencyAdapter( viewModel,context, filteringListResult!!)
            view.recyler.adapter = MainAdapter;
            view.recyler.layoutManager = LinearLayoutManager(context)
            val currency = it.map { it.CName }
            val arrayadapter = ArrayAdapter(context!!, com.codesroots.mac.cards.R.layout.spinner, currency)
            view.spinner1.adapter = arrayadapter
             arrayadapter.addAll(currency)
            arrayadapter.notifyDataSetChanged()


        })

        view.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText == "") {
                    filteringListResult!!.addAll(dataaa!!)
                } else {
                    val resultList = ArrayList<Currency>()
                    for (row in filteringListResult!!) {
                        if (row.CName!!.contains(newText))
                            resultList.add(row)
                    }
                    filteringListResult!!.clear()
                    filteringListResult!!.addAll(resultList)
                      }
                    MainAdapter.notifyDataSetChanged()

                    return true

                }
            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE
                return false
            }
        })

        view.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

          public  override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

//                Cname= data!![position].CName
              viewModel.SpinnerData = dataaa!![position]
                print(position)
            }

        }
        return view.root;


    }



}