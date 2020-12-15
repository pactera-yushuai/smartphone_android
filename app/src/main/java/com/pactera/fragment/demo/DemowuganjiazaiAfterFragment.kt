package com.pactera.fragment.demo

import android.os.Bundle
import android.os.Handler
import android.view.View
import com.kaopiz.kprogresshud.KProgressHUD


/**
 * 无感加载 打开
 */
class DemowuganjiazaiAfterFragment : DemowuganjiazaiRawFragment(){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun dingzhi(){
        baseAdapter.loadMoreModule.setOnLoadMoreListener {
            homeFragmentViewModel.setPagePlus()
            homeFragmentViewModel.getList()
        }
        baseAdapter.loadMoreModule.isEnableLoadMore = true
        baseAdapter.loadMoreModule.preLoadNumber = 20
    }

}