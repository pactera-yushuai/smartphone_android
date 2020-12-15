package com.pactera.fragment

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.view.marginBottom
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pactera.R
//import com.cooltechworks.views.shimmer.ShimmerRecyclerView
//import com.ethanhua.skeleton.Skeleton
//import com.ethanhua.skeleton.SkeletonScreen
import com.pactera.activity.HomeActivity
import com.pactera.adapter.recyclerviewadapter.GoodsAdapter
import com.pactera.adapter.viewpageradapter.ImageAdapter
import com.pactera.bean.Banner
import com.pactera.bean.Goods
import com.pactera.bean.WeiTao2
import com.pactera.fragment.base.BaseFragment
import com.pactera.viewmodel.HomeActivityViewModel
import com.pactera.viewmodel.fragment.HomeFragmentViewModel
import com.pactera.widget.ViewTenItem
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_home.*


/**
 *
 */
class HomeFragment1 : BaseFragment() {

    private lateinit var homeActivityViewModel: HomeActivityViewModel

    private lateinit var homeFragmentViewModel: HomeFragmentViewModel

    public lateinit var mRecyclerView: RecyclerView

    var baseAdapter: GoodsAdapter = GoodsAdapter(ArrayList<Goods>())

//    protected lateinit var skeletonScreen: SkeletonScreen


    protected lateinit var headerView: ViewTenItem

    protected lateinit var headerView2: com.youth.banner.Banner<Banner, ImageAdapter>


    /**
     * 初始化Fragment的布局
     */
    override fun initView(): View {
        homeFragmentViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        homeActivityViewModel = ViewModelProvider(requireActivity()).get(HomeActivityViewModel::class.java)
        return View.inflate(mActivity, R.layout.fragment_home, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        headerView = ViewTenItem(mActivity, null)

//        headerView2 = com.youth.banner.Banner<Banner, ImageAdapter>(mActivity)
        if(savedInstanceState==null){

        }
//        baseAdapter.addHeaderView(headerView,1)

        mRecyclerView = view.findViewById(R.id.fragment_home_recyclerview)
//        val shimmerRecycler = view.findViewById(R.id.shimmer_recycler_view) as ShimmerRecyclerView
//        shimmerRecycler.showShimmerAdapter()

        homeFragmentViewModel.list1.observe(viewLifecycleOwner){
//            fragment_home_banner.addBannerLifecycleObserver(this)//添加生命周期观察者
//                    .setAdapter(ImageAdapter(it))
//                    .indicator = CircleIndicator(mActivity);

//            headerView2.addBannerLifecycleObserver(this)//添加生命周期观察者
//                    .setAdapter(ImageAdapter(it))
//                    .indicator = CircleIndicator(mActivity);

//            val banners = ArrayList<Banner>(10)
//            banners.addAll(arrayListOf(Banner(),Banner(),Banner(),Banner(),Banner(),Banner(),Banner(),Banner(),Banner(),Banner(), ))

            headerView.setData(it)

        }


        with(mRecyclerView){

            mRecyclerView.layoutManager = GridLayoutManager(mActivity, 2)

            homeFragmentViewModel.list2.observe(viewLifecycleOwner){

                if(fragment_home_refreshlayout.isRefreshing){
                    fragment_home_refreshlayout.isRefreshing = false
                }

                if(mRecyclerView.adapter == null){
                    baseAdapter.addData(it)
                    mRecyclerView.adapter = baseAdapter
                }else{
                    if(it.isEmpty()){
                        baseAdapter.loadMoreModule.loadMoreEnd()
                    }else{
                        baseAdapter.addData(it)
                        baseAdapter.loadMoreModule.loadMoreComplete()
                    }

                    // 设置加载更多监听事件
//                    mRecyclerView.adapter = baseAdapter

                }

            }

        }

        fragment_home_refreshlayout.setOnRefreshListener {
            baseAdapter.setNewData(ArrayList<Goods>())
            homeFragmentViewModel.reSetPage()
            homeFragmentViewModel.getList()
        }

    }

    override fun onResume() {
        super.onResume()

        if(!baseAdapter.hasHeaderLayout()){
            baseAdapter.addHeaderView(headerView)
        }

        println("已经添加headerView: "+baseAdapter.hasHeaderLayout())


//        shimmer_view_container.startShimmer()

        homeFragmentViewModel.getList()
        println("成功了： " + homeActivityViewModel.getData())

        homeActivityViewModel.temp.observe(requireActivity()){
            println("成功了啊啊啊啊： " + it)
        }

        // 设置点击事件
        baseAdapter.setOnItemClickListener { adapter, view, position ->
//            (mActivity as HomeActivity).switchFragment((mActivity as HomeActivity).DETAIL_FRAGMENT)
        }

        // 设置加载更多监听事件
        baseAdapter.loadMoreModule.setOnLoadMoreListener{
            homeFragmentViewModel.setPagePlus()
            homeFragmentViewModel.getList()
        }

        baseAdapter.loadMoreModule.isAutoLoadMore = true
        baseAdapter.loadMoreModule.preLoadNumber = 10



    }

}
