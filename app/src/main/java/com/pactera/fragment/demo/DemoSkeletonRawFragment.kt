package com.pactera.fragment.demo

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.kaopiz.kprogresshud.KProgressHUD
import com.pactera.R
import com.pactera.adapter.recyclerviewadapter.GoodsAdapter
import com.pactera.bean.Goods
import com.pactera.fragment.base.BaseFragment
import com.pactera.viewmodel.fragment.HomeFragmentViewModel
import com.pactera.widget.ViewHScroll
import kotlinx.android.synthetic.main.fragment_weitao.*


/**
 *  有loading条，没有骨架屏
 */
open class DemoSkeletonRawFragment : BaseFragment() {

    protected lateinit var mRecyclerView: RecyclerView

    protected var goodsSkeletonAdapter: BaseQuickAdapter<Goods, BaseViewHolder> = GoodsAdapter(arrayListOf<Goods>(Goods(),Goods(),Goods(),Goods(),Goods(),Goods(),Goods(),Goods(),Goods(),Goods(),Goods(),))

    protected var goodsAdapter: BaseQuickAdapter<Goods, BaseViewHolder> = GoodsAdapter(ArrayList<Goods>())

    lateinit var homeFragmentViewModel: HomeFragmentViewModel

    lateinit var headerView: ViewHScroll

    protected var hud: KProgressHUD? = null

    override fun initView(): View {
        homeFragmentViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        return View.inflate(mActivity, R.layout.fragment_recyclerview, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        Handler(mActivity.mainLooper).postDelayed({
            homeFragmentViewModel.getList()
        },15000)

        hud = KProgressHUD.create(mActivity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)

        mRecyclerView = view.findViewById(R.id.fragment_recyclerview_recycler_view)

//        mLinearLayout = view.findViewById(R.id.frame_layout_frame_layout_ll_empty)
//        mProgressBar = view.findViewById(R.id.frame_layout_progress_bar)
        headerView = ViewHScroll(mActivity, null)

//        if(base2Adapter.headerLayoutCount==0){
//            base2Adapter.addHeaderView(headerView)
//        }


        goodsAdapter.loadMoreModule.isEnableLoadMore = true
        goodsAdapter.loadMoreModule.preLoadNumber = 5


        fragment_weitao_refreshlayout.setOnRefreshListener {
            goodsAdapter.setNewData(ArrayList<Goods>())
            homeFragmentViewModel.reSetPage()
            homeFragmentViewModel.getList()
        }

        dingzhi()


    }

    override fun onResume() {
        super.onResume()
        println("isStateSaved $isStateSaved")

    }

    open fun dingzhi(){

        mRecyclerView.adapter = goodsSkeletonAdapter

        goodsAdapter.loadMoreModule.setOnLoadMoreListener {
            homeFragmentViewModel.setPagePlus()
            homeFragmentViewModel.getList()
        }

        with(mRecyclerView){
            mRecyclerView.layoutManager = LinearLayoutManager(mActivity)

            homeFragmentViewModel.list2.observe(viewLifecycleOwner){
                if(fragment_weitao_refreshlayout.isRefreshing){
                    fragment_weitao_refreshlayout.isRefreshing = false
                }
                if(mRecyclerView.adapter == goodsSkeletonAdapter){
                    goodsAdapter.addData(it)
                    mRecyclerView.adapter = goodsAdapter
                }else{
                    if(it.isEmpty()){
                        goodsAdapter.loadMoreModule.loadMoreEnd()
                    }else{
                        goodsAdapter.addData(it)
                        goodsAdapter.loadMoreModule.loadMoreComplete()
                    }
                }
            }

        }

    }

}
