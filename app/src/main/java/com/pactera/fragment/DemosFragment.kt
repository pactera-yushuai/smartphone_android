package com.pactera.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.pactera.R
import com.pactera.activity.demo.DemowuganjiazaiActivity
import com.pactera.activity.demo.SkeletonActivity
import com.pactera.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_case.*


/**
 *  案例集合
 */
class DemosFragment : BaseFragment(){

//    var pigeonDetialFragment: PigeonDetailFragment? = null

    override fun initView(): View {
        return View.inflate(mActivity, R.layout.fragment_case, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        fragment_case_button1.setOnClickListener {
            mActivity.startActivity(Intent(mActivity, DemowuganjiazaiActivity::class.java))
        }

        fragment_case_button3.setOnClickListener {
            mActivity.startActivity(Intent(mActivity, SkeletonActivity::class.java))
        }

        initFragment()


//        with(activity_search_pigeon_ys_search_view){
//
//            getSearchButton().setOnClickListener {
//
//                val tempText = getEditText().text.toString().trim()
//
//                if (tempText.isEmpty()) {
//                    Common.showToast(mActivity, "输入内容不能为空")
//                    return@setOnClickListener
//                }
//
////                if(!pigeonDetialFragment!!.isAdded){
////                    fragmentManager?.beginTransaction()?.add(R.id.activity_search_pigeon_frame_layout, pigeonDetialFragment!!)?.commit()
////                }else{
////
////                    val hashMap = HashMap<String,String>()
////                    hashMap["filter.ring_id"] = tempText
////                    hashMap["filter.user_id"] = user_id
////
////                    println(hashMap)
////
////                    MyOkHttpUtils.getReturnWithParamsGeneric(Urls.SEARCH_PIGEON_URL,hashMap){ res, ioe ->
////
////                        if(res!=null && res.isSuccessful) {
////
////                            val pigeonArray: ArrayList<Pigeon> = Gson().fromJson<ArrayList<Pigeon>>(res.body()!!.string(), object : TypeToken<ArrayList<Pigeon>>() {}.type)
////
////                            mActivity.runOnUiThread {
////
////                                if(pigeonArray.isNotEmpty()){
////                                    val bundle = Bundle()
////                                    bundle.putSerializable(getString(R.string.Pigeon),pigeonArray[0])
////                                    pigeonDetialFragment!!.arguments = bundle
////                                    if(pigeonDetialFragment!!.isHidden){
////                                        fragmentManager?.beginTransaction()?.show(pigeonDetialFragment!!)?.commit()
////                                    }
////                                }else{
////                                    fragmentManager?.beginTransaction()?.hide(pigeonDetialFragment!!)?.commit()
////                                    Common.showToast(mActivity,"没有匹配的赛鸽")
////                                }
////
////                            }
////
////                        }
////
////                    }
////
////                }
//
////                dismissKeyBoard()
//
//            }
//
//        }

    }

    fun initFragment(){

//        if(pigeonDetialFragment == null){
////            pigeonDetialFragment = PigeonDetailFragment()
//        }
//
//        if(!pigeonDetialFragment!!.isAdded){
//            fragmentManager?.beginTransaction()?.add(R.id.activity_search_pigeon_frame_layout, pigeonDetialFragment!!)?.hide(pigeonDetialFragment!!)?.commit()
//        }

    }



}
