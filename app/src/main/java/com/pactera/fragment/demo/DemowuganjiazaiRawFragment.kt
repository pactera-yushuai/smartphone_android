package com.pactera.fragment.demo

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kaopiz.kprogresshud.KProgressHUD
import com.pactera.R
import com.pactera.activity.HomeActivity
import com.pactera.adapter.recyclerviewadapter.GoodsAdapter
import com.pactera.bean.Goods
import com.pactera.fragment.base.BaseFragment
import com.pactera.viewmodel.fragment.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_wuganjiazai_detail.*


/**
 *  无感加载，没开
 */
open class DemowuganjiazaiRawFragment : BaseFragment() {

    protected lateinit var homeFragmentViewModel: HomeFragmentViewModel

    public lateinit var mRecyclerView: RecyclerView

    protected var baseAdapter: GoodsAdapter = GoodsAdapter(ArrayList<Goods>())

    protected lateinit var hud: KProgressHUD;

    override fun initView(): View {
        homeFragmentViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        return View.inflate(mActivity, R.layout.fragment_wuganjiazai_detail, null)
    }

    override fun initData() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        hud = KProgressHUD.create(mActivity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("読み込まれます")
//                .setDetailsLabel("请稍后")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)

        homeFragmentViewModel.getList()

        fragment_wuganjiazai_detail_swiperefreshlayout.setOnRefreshListener {
            baseAdapter.setNewData(ArrayList<Goods>())
            homeFragmentViewModel.reSetPage()
            homeFragmentViewModel.getList()
        }


        mRecyclerView = fragment_wuganjiazai_detail_recyclerview

        mRecyclerView.layoutManager = GridLayoutManager(mActivity, 2)


        // 设置点击事件
        baseAdapter.setOnItemClickListener { adapter, view, position ->
//            (mActivity as HomeActivity).switchFragment((mActivity as HomeActivity).DETAIL_FRAGMENT)
//                        childFragmentManager.beginTransaction().replace(R.layout.)
        }



        baseAdapter.loadMoreModule.isAutoLoadMore = true
        this.dingzhi()

        mRecyclerView.adapter = baseAdapter

        homeFragmentViewModel.list2.observe(viewLifecycleOwner) {
            hud.dismiss()
            if(fragment_wuganjiazai_detail_swiperefreshlayout.isRefreshing){
                fragment_wuganjiazai_detail_swiperefreshlayout.isRefreshing = false
            }

            if (mRecyclerView.adapter == null) {
                baseAdapter.addData(it)
            } else {
                if (it.isEmpty()) {
                    baseAdapter.loadMoreModule.loadMoreEnd()
                } else {
                    baseAdapter.addData(it)
                    baseAdapter.loadMoreModule.loadMoreComplete()
                }

            }

        }

    }

    open fun dingzhi(){
        // 设置加载更多监听事件
        baseAdapter.loadMoreModule.setOnLoadMoreListener {
            homeFragmentViewModel.setPagePlus()
            Handler(mActivity.mainLooper).postDelayed({
                homeFragmentViewModel.getList()
            }, 1500)
            hud.show()
        }
    }

}