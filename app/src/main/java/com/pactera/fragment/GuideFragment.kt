package com.pactera.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

//import com.cjj.MaterialRefreshLayout
//import com.cjj.MaterialRefreshListener
import com.pactera.R
import com.pactera.adapter.viewpageradapter.GuideAdapter
import kotlinx.android.synthetic.main.fragment_guide.*
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *  
 */
open class GuideFragment : Fragment() {

    protected var lastRefreshTime: Long = android.os.SystemClock.uptimeMillis()

    protected var offset: Int = 0 // 偏移量

    val map = HashMap<String,String>()

    val banners = ArrayList<com.pactera.bean.Banner>()
    val guides = ArrayList<Int>()

    init {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_guide, container, false)
    }

//    override fun initView(): View {
//
//        return View.inflate(mActivity, R.layout.fragment_ads1, null)
//
//    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val banner1 = com.pactera.bean.Banner()
//        banner1.urlBig = resources.getDrawable(R.mipmap.tabbar_home).toString()

        banners.add(banner1)

        val banner2 = com.pactera.bean.Banner()
//        banner2.urlBig = mActivity.resources.getDrawable(R.mipmap.guanggao1, null)
        banners.add(banner2)

        val banner3 = com.pactera.bean.Banner()
//        banner3.urlBig = resources.getResourceName(R.mipmap.tabbar_home)
        banners.add(banner3)

        val temp1 = R.mipmap.guanggao1
        val temp2 = R.mipmap.guanggao2
        val temp3 = R.mipmap.guanggao3
        guides.add(temp1)
        guides.add(temp2)
        guides.add(temp3)

        fragment_guide_banner.addBannerLifecycleObserver(this)//添加生命周期观察者
                .setAdapter(GuideAdapter(guides))
                .scrollTime = 2
//                .indicator = CircleIndicator(mActivity)


    }


//    fun refreshParamsMap(page: Int){
//        this.page = page
//        map["page"] = page.toString()
//        map["offset"] = getOffset(page)
//        map["limit"] = limit.toString()
//    }



}
