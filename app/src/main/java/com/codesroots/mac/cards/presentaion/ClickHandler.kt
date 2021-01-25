package com.codesroots.mac.cards.presentaion

import android.content.Context
import android.net.wifi.p2p.nsd.WifiP2pDnsSdServiceRequest.newInstance
import android.os.Bundle
import com.codesroots.mac.cards.models.Currency
import com.codesroots.mac.cards.models.StockData
import com.codesroots.mac.cards.presentaion.borsadetails.BorsaDetailsFragment
import com.codesroots.mac.cards.presentaion.borsadetails.Details
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import com.codesroots.mac.cards.presentaion.massagefragment.MassageFragment
import com.codesroots.mac.cards.presentaion.menufragment.MenuFragment
import com.codesroots.mac.cards.presentaion.menuitemsfragments.LoginFragment
import com.codesroots.mac.cards.presentaion.menuitemsfragments.gaz.GazPriceFragment
import com.codesroots.mac.cards.presentaion.menuitemsfragments.goldprice.GoldPriceFragment
import com.codesroots.mac.cards.presentaion.menuitemsfragments.internationalcurrencies.internationalCurrency
import com.codesroots.mac.cards.presentaion.menuitemsfragments.news.NewsFragment
import com.codesroots.mac.cards.presentaion.menuitemsfragments.salary.SalaryFragment
import com.codesroots.mac.cards.presentaion.register.RegisterFragment
import com.codesroots.mac.cards.presentaion.transferfragment.TransferFragment




class ClickHandler {

    fun SwitchToPackages(context: Context) {

        val bundle = Bundle()
        //  bundle.putParcelable("cliObj" ,clients[position] )
        val frag = MenuFragment()
        frag.arguments = bundle
        ( context as MainActivity).supportFragmentManager!!.beginTransaction().setCustomAnimations(
            com.codesroots.mac.cards.R.anim.ttb,0, 0,0)
            .replace(com.codesroots.mac.cards.R.id.main_frame, frag).addToBackStack(null).commit()

    }
    fun SwitchToSalaryNews(context: Context) {
        val bundle = Bundle()
        //  bundle.putParcelable("cliObj" ,clients[position] )
        val frag = SalaryFragment()
        frag.arguments = bundle
        ( context as MainActivity).supportFragmentManager!!.beginTransaction().setCustomAnimations(
            com.codesroots.mac.cards.R.anim.ttb,0, 0,0)
            .replace(com.codesroots.mac.cards.R.id.main_frame, frag).addToBackStack(null).commit()
    }
    fun SwitchToGazPrice(context: Context) {

        val bundle = Bundle()
        //  bundle.putParcelable("cliObj" ,clients[position] )
        val frag = GazPriceFragment()
        frag.arguments = bundle
        ( context as MainActivity).supportFragmentManager!!.beginTransaction().setCustomAnimations(
            com.codesroots.mac.cards.R.anim.ttb,0, 0,0)
            .replace(com.codesroots.mac.cards.R.id.main_frame, frag).addToBackStack(null).commit()
    }

    fun SwitchToNews(context: Context) {

        val bundle = Bundle()
        //  bundle.putParcelable("cliObj" ,clients[position] )
        val frag = NewsFragment()
        frag.arguments = bundle
        ( context as MainActivity).supportFragmentManager!!.beginTransaction().setCustomAnimations(
            com.codesroots.mac.cards.R.anim.ttb,0, 0,0)
            .replace(com.codesroots.mac.cards.R.id.main_frame, frag).addToBackStack(null).commit()
    }
    fun SwitchToLogin(context: Context) {

        val bundle = Bundle()
        //  bundle.putParcelable("cliObj" ,clients[position] )
        val frag = LoginFragment()
        frag.arguments = bundle
        ( context as MainActivity).supportFragmentManager!!.beginTransaction().setCustomAnimations(
            com.codesroots.mac.cards.R.anim.ttb,0, 0,0)
            .replace(com.codesroots.mac.cards.R.id.main_frame, frag).addToBackStack(null).commit()
    }
    fun SwitchToMassage(context: Context) {

        val bundle = Bundle()
        //  bundle.putParcelable("cliObj" ,clients[position] )
        val frag = MassageFragment()
        frag.arguments = bundle
        ( context as MainActivity).supportFragmentManager!!.beginTransaction().setCustomAnimations(
            com.codesroots.mac.cards.R.anim.ttb,0, 0,0)
            .replace(com.codesroots.mac.cards.R.id.main_frame, frag).addToBackStack(null).commit()
    }
    fun SwitchToRegister(context: Context) {
        val bundle = Bundle()
        //  bundle.putParcelable("cliObj" ,clients[position] )
        val frag = RegisterFragment()
        frag.arguments = bundle
        ( context as MainActivity).supportFragmentManager!!.beginTransaction().setCustomAnimations(
            com.codesroots.mac.cards.R.anim.ttb,0, 0,0)
            .replace(com.codesroots.mac.cards.R.id.main_frame, frag).addToBackStack(null).commit()
    }
    fun SwitchToGoldFragment(context: Context) {
        val bundle = Bundle()
        //  bundle.putParcelable("cliObj" ,clients[position] )
        val frag = GoldPriceFragment()
        frag.arguments = bundle
        ( context as MainActivity).supportFragmentManager!!.beginTransaction().setCustomAnimations(
            com.codesroots.mac.cards.R.anim.ttb,0, 0,0)
            .replace(com.codesroots.mac.cards.R.id.main_frame, frag).addToBackStack(null).commit()
    }
    fun SwitchToCurrency(context: Context) {
        val bundle = Bundle()
        //  bundle.putParcelable("cliObj" ,clients[position] )
        val frag = internationalCurrency()
        frag.arguments = bundle
        ( context as MainActivity).supportFragmentManager!!.beginTransaction().setCustomAnimations(
            com.codesroots.mac.cards.R.anim.ttb,0, 0,0)
            .replace(com.codesroots.mac.cards.R.id.main_frame, frag).addToBackStack(null).commit()
    }
    fun SwitchToTransferPage(context: Context , data : Currency, viewModel: MainViewModel ) {
        val bundle = Bundle()
        //  bundle.putParcelable("cliObj" ,clients[position] )
        val frag = TransferFragment()
        bundle.putParcelable("dataa" , data)
        bundle.putParcelable("first_data",viewModel.SpinnerData)
            print(viewModel.SpinnerData)

        frag.arguments = bundle
        ( context as MainActivity).supportFragmentManager!!.beginTransaction().setCustomAnimations(
            com.codesroots.mac.cards.R.anim.ttb,0, 0,0)
            .replace(com.codesroots.mac.cards.R.id.main_frame, frag).addToBackStack(null).commit()
    }

    fun SwitchToBorsaDetails(context: Context , data : StockData) {
        val bundle = Bundle()
        //  bundle.putParcelable("cliObj" ,clients[position] )
        val frag = BorsaDetailsFragment()
        bundle.putParcelable("dataa" , data)

        frag.arguments = bundle
        ( context as MainActivity).supportFragmentManager!!.beginTransaction().setCustomAnimations(
            com.codesroots.mac.cards.R.anim.ttb,0, 0,0)
            .replace(com.codesroots.mac.cards.R.id.main_frame, frag).addToBackStack(null).commit()
    }

    fun SwitchToDetails(context: Context , data : StockData) {
        val bundle = Bundle()
        //  bundle.putParcelable("cliObj" ,clients[position] )
        val frag = Details()
        bundle.putParcelable("dataa" , data)

        frag.arguments = bundle
        ( context as MainActivity).supportFragmentManager!!.beginTransaction().setCustomAnimations(
            com.codesroots.mac.cards.R.anim.ttb,0, 0,0)
            .replace(com.codesroots.mac.cards.R.id.main_frame, frag).addToBackStack(null).commit()
    }





}