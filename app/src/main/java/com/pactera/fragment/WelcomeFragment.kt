package com.pactera.fragment

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.pactera.R
import com.pactera.fragment.base.BaseFragment
import com.pactera.viewmodel.HomeActivityViewModel

/**
 *  WelcomeFragment
 */
class WelcomeFragment : BaseFragment() {

    private lateinit var homeActivityViewModel: HomeActivityViewModel // by viewModels

    override fun initView(): View {
        homeActivityViewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)
        homeActivityViewModel.setData("WelcomeFragment oh, yeah~")
        println("第一次取出 "+homeActivityViewModel.getData())
        return View.inflate(mActivity, R.layout.fragment_welcome, null)
    }



}


//class PigeonSaleFragment : RecyclerViewFragment<PigeonSale, PigeonSaleAdapter.PigeonSaleViewHolder>() {
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        recyclerViewFragmentViewModel = ViewModelProviders.of(this).get(PigeonSaleViewModel::class.java)
//        super.onViewCreated(view, savedInstanceState)
//    }
//
//
//}
