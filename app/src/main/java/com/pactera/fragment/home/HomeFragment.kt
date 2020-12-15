package com.pactera.fragment.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider

import com.pactera.R
import com.pactera.fragment.base.BaseFragment
import com.pactera.common.Common
import com.pactera.viewmodel.fragment.HomeFragmentViewModel

import kotlinx.android.synthetic.main.fragment_main.*


/**
 * 首页
 */
class HomeFragment : BaseFragment(),View.OnClickListener {

    lateinit var homeFragmentViewModel: HomeFragmentViewModel

    val hashMap = HashMap<String,String>()

    init {
        hashMap["offset"] = getOffset(page)
        hashMap["limit"] = "300"
        hashMap["filter.owner_id"] = user_id
        hashMap["filter.owner_type"] = "person"
    }

    override fun initView(): View {
        homeFragmentViewModel = ViewModelProvider(requireActivity()).get(HomeFragmentViewModel::class.java)
        return View.inflate(mActivity, R.layout.fragment_main, null)
    }

    // 不能直接在onCreateView中用id的原因，你xml没有载入，会导致使用id的时候会报空指针，如果需要使用，在onCreateView return view后，
    // 在onViewCreated函数中使用Id直接调用，onViewCreated会在onCreateView后执行
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        homeFragmentViewModel.list2.observe(viewLifecycleOwner){
            println("oh,yeah~"+it)
        }

//        pigeonViewModel = ViewModelProviders.of(this).get(PigeonViewModel::class.java)
//
//        pigeonViewModel.getDataFromNet(hashMap)
//
//        pigeonViewModel.type.observe(this, Observer {banner->
//            if(banner==null){
//                Common.showToast(mActivity, getString(R.string.json_fail))
//            }else{
//                MyApplication.pigeonList = banner.list
//            }
//        })

//        fragment_main_yslinearlayout_jubanfang.setOnClickListener(this)
//        fragment_main_yslinearlayout_bisai.setOnClickListener(this)
//        fragment_main_yslinearlayout_saigechaxun.setOnClickListener(this)
//        fragment_main_yslinearlayout_saigepaimai.setOnClickListener(this)
//        fragment_main_yslinearlayout_saigeyiyuan.setOnClickListener(this)
//        fragment_main_yslinearlayout_saigeshangcheng.setOnClickListener(this)

    }


    override fun onClick(v: View?) {

        when(v!!.id){


//            (R.id.fragment_main_yslinearlayout_saigeshangcheng)->{
//                Common.showToast(mActivity,"即将上线，敬请期待")
//            }
//
//            (R.id.fragment_main_yslinearlayout_saigeyiyuan)->{
//                Common.showToast(mActivity,"即将上线，敬请期待")
//            }


        }

    }



}
