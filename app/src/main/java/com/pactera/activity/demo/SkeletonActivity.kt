package com.pactera.activity.demo

import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pactera.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.pactera.activity.base.BaseActivity
import com.pactera.adapter.recyclerviewadapter.WeiTao1Adapter
import com.pactera.adapter.recyclerviewadapter.WeiTao1SkeletonAdapter
import com.pactera.adapter.recyclerviewadapter.WeiTao2Adapter
import com.pactera.adapter.recyclerviewadapter.WeiTao2SkeletonAdapter
import com.pactera.bean.WeiTao1
import com.pactera.bean.WeiTao2
import com.pactera.viewmodel.fragment.WeitaoFragmentViewModel
import com.pactera.widget.ViewHScroll
import kotlinx.android.synthetic.main.fragment_weitao.*
import kotlinx.android.synthetic.main.fragment_wuganjiazai_detail.*


/**
 *  骨架屏
 */
class SkeletonActivity: BaseActivity() {

    lateinit var weitaoFragmentViewModel: WeitaoFragmentViewModel

    var weiTao1SkeletonAdapter: BaseQuickAdapter<WeiTao1, BaseViewHolder> = WeiTao1Adapter(arrayListOf<WeiTao1>(WeiTao1(),WeiTao1(),WeiTao1(),WeiTao1(),WeiTao1(),WeiTao1(), ))

    var weiTao1Adapter: BaseQuickAdapter<WeiTao1, BaseViewHolder> = WeiTao1SkeletonAdapter(arrayListOf<WeiTao1>())

    var weiTao2SkeletonAdapter: BaseQuickAdapter<WeiTao2, BaseViewHolder> = WeiTao2SkeletonAdapter(arrayListOf<WeiTao2>(WeiTao2(),WeiTao2(),WeiTao2(),WeiTao2(),WeiTao2(),WeiTao2(),WeiTao2(),WeiTao2(),WeiTao2(),WeiTao2(), ))

    var weiTao2Adapter: BaseQuickAdapter<WeiTao2, BaseViewHolder> = WeiTao2Adapter(ArrayList<WeiTao2>())

    protected lateinit var mRecyclerView: RecyclerView

    protected lateinit var headerViewSkeleton: ViewHScroll

    protected lateinit var headerViewNormal: ViewHScroll

    override fun initView(savedInstanceState: Bundle?) {
        weitaoFragmentViewModel = ViewModelProvider(this).get(WeitaoFragmentViewModel::class.java)
        setContentView(R.layout.activity_skeleton)
        mRecyclerView = fragment_weitao_recyclerview
        headerViewSkeleton = ViewHScroll(this, null)
        headerViewNormal = ViewHScroll(this, null)
    }

    override fun initData() {
        Handler(mainLooper).postDelayed({
            weitaoFragmentViewModel.getList()
        }, 2000)

        val lm = LinearLayoutManager(this)
        lm.orientation = LinearLayoutManager.HORIZONTAL

        val lm2 = LinearLayoutManager(this)
        lm2.orientation = LinearLayoutManager.HORIZONTAL

        headerViewSkeleton.getRecyclerView().layoutManager = lm
        headerViewNormal.getRecyclerView().layoutManager = lm2

        headerViewSkeleton.getRecyclerView().adapter = weiTao1SkeletonAdapter
        headerViewNormal.getRecyclerView().adapter = weiTao1Adapter

//        if(weiTao2Adapter.headerLayoutCount==0){
//            weiTao2Adapter.addHeaderView(headerViewNormal)
//            headerViewNormal.getRecyclerView().adapter = weiTao1Adapter
//        }
//        if(weiTao2SkeletonAdapter.headerLayoutCount==0){
//            weiTao2SkeletonAdapter.addHeaderView(headerViewSkeleton)
//            headerViewSkeleton.getRecyclerView().adapter = weiTao1SkeletonAdapter
//        }

        mRecyclerView.adapter = weiTao2SkeletonAdapter

        with(mRecyclerView){
            mRecyclerView.layoutManager = LinearLayoutManager(this@SkeletonActivity)

            weitaoFragmentViewModel.list2.observe(this@SkeletonActivity){
                if(fragment_weitao_refreshlayout.isRefreshing){
                    fragment_weitao_refreshlayout.isRefreshing = false
                }

                if(mRecyclerView.adapter == weiTao2SkeletonAdapter){
                    weiTao2Adapter.addData(it)
                    mRecyclerView.adapter = weiTao2Adapter
                }else{
                    if(it.isEmpty()){
                        weiTao2Adapter.loadMoreModule.loadMoreEnd()
                    }else{
                        weiTao2Adapter.addData(it)
                        weiTao2Adapter.loadMoreModule.loadMoreComplete()
                    }
                }
            }

        }

        weitaoFragmentViewModel.list1.observe(this@SkeletonActivity){
            println("weitao1 "+it)
            weiTao1Adapter.addData(it)
            weiTao1SkeletonAdapter.addData(it)
            headerViewNormal.getRecyclerView().adapter = weiTao1Adapter
            if(fragment_weitao_refreshlayout.isRefreshing){
                fragment_weitao_refreshlayout.isRefreshing = false
            }else{

            }


        }

        fragment_weitao_refreshlayout.setOnRefreshListener {
            mRecyclerView.adapter = weiTao2SkeletonAdapter
            weiTao2Adapter.setNewData(ArrayList<WeiTao2>())
            weitaoFragmentViewModel.reSetPage()
            Handler(mainLooper).postDelayed({
                weitaoFragmentViewModel.getList()
            }, 2000)
        }


    }

    override fun onResume() {
        super.onResume()

        weiTao2Adapter.loadMoreModule.isEnableLoadMore = true

        // 设置加载更多监听事件
        weiTao2Adapter.loadMoreModule.setOnLoadMoreListener{
            weitaoFragmentViewModel.setPagePlus()
            weitaoFragmentViewModel.getList()
        }

    }

}
