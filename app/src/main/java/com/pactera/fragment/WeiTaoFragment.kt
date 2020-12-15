package com.pactera.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pactera.R
import com.pactera.adapter.recyclerviewadapter.WeiTao2Adapter
import com.pactera.bean.WeiTao2
import com.pactera.fragment.base.BaseFragment
import com.pactera.viewmodel.fragment.WeitaoFragmentViewModel
import com.pactera.widget.ViewHScroll
import kotlinx.android.synthetic.main.fragment_weitao.*


/**
 *
 */
class WeiTaoFragment: BaseFragment() {

    lateinit var weiTaoFragmentViewModel: WeitaoFragmentViewModel

    override fun initView(): View {
        weiTaoFragmentViewModel = ViewModelProvider(requireActivity()).get(WeitaoFragmentViewModel::class.java)
        return View.inflate(mActivity, R.layout.fragment_weitao, null)
    }





}
