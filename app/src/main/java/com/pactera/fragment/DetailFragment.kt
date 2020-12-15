package com.pactera.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pactera.R
import com.pactera.adapter.recyclerviewadapter.WeiTao2Adapter
import com.pactera.fragment.base.BaseFragment
import com.pactera.viewmodel.fragment.DetailFragmentViewModel


class DetailFragment: BaseFragment() {

    protected lateinit var mRecyclerView: RecyclerView

    lateinit var base2Adapter: WeiTao2Adapter

    lateinit var detailFragmentViewModel: DetailFragmentViewModel

    private val DETAIL_FRAGMENT = "detailFragment"

    override fun initView(): View {
        detailFragmentViewModel = ViewModelProvider(this).get(DetailFragmentViewModel::class.java)
        return View.inflate(mActivity, R.layout.fragment_detail, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        detailFragmentViewModel.getList()

        mRecyclerView = view.findViewById(R.id.fragment_detail_recyclerview)

//        mLinearLayout = view.findViewById(R.id.frame_layout_frame_layout_ll_empty)
//        mProgressBar = view.findViewById(R.id.frame_layout_progress_bar)

//        detailFragmentViewModel.list1.observe(viewLifecycleOwner){
//            fragment_detail_banner.addBannerLifecycleObserver(this)//添加生命周期观察者
//                    .setAdapter(ImageAdapter(it))
//                    .indicator = CircleIndicator(mActivity)
//        }

        with(mRecyclerView){
            mRecyclerView.layoutManager = LinearLayoutManager(mActivity)

            detailFragmentViewModel.list2.observe(viewLifecycleOwner){
                if(mRecyclerView.adapter == null){
                    base2Adapter = WeiTao2Adapter(it)
                    // 设置点击事件
                    base2Adapter.setOnItemClickListener { adapter, view, position ->
//                        (mActivity as HomeActivity).switchFragment((mActivity as HomeActivity).DETAIL_FRAGMENT)
                        println("childFragmentManager.backStackEntryCount: "+childFragmentManager.backStackEntryCount)
                        if(childFragmentManager.backStackEntryCount<=3){
//                            childFragmentManager.beginTransaction()
//                                    .add(R.layout.fragment_detail, this@DetailFragment, DETAIL_FRAGMENT)
//                                    .replace(R.id.fragment_detail, this@DetailFragment)
//                                    .commit()
                        }else{

                        }
//                        adapter.context.startActivity(Intent(), )
                    }
                    mRecyclerView.adapter = base2Adapter
                }else{
                    base2Adapter.addData(it)
                }
            }

        }

    }

}
