package com.pactera.fragment.base

import android.app.Activity
import android.widget.ProgressBar
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.pactera.utils.SharedPreferencesUtils
import com.pactera.utils.limit

/**
 * 所有的Fragment的基类
 * 上下文抽取
 * 初始化布局的方法抽取: 抽象
 * 初始化数据的方法抽取: 可选
 */
abstract class BaseFragment : Fragment() {

    protected var page = 1

    protected lateinit var mActivity: Activity // 上下文对象

    var user_id = SharedPreferencesUtils.getstring("user_id", "")

    var progressBar: ProgressBar? = null

    init {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            mActivity = context as Activity
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = initView()
        println("执行 Fragment onCreateView")
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        println("执行 Fragment onViewCreated")
    }


    override fun onResume() {
        super.onResume()
        println("执行 Fragment onResume")
    }


    override fun onStart() {
        super.onStart()
        println("执行 Fragment onStart")
    }

    /**
     * 初始化Fragment的布局
     */
    abstract fun initView(): View


    /**
     * 初始化数据, 子类覆盖此方法, 来实现自己的数据初始化操作
     */
    open fun initData() {}


    /**
     *  初始化数据的Observer
     */
    open fun initListener() {}

    /**
     *  得到偏移量
     */
    protected fun getOffset(page: Int) : String{
        return if(page==1){
            "0"
        }else{
            ((page-1) * limit).toString()
        }
    }

    // 隐藏软键盘
//    fun dismissKeyBoard() {
//        // 隐藏软键盘
//        val imm = systemS(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
//        imm?.hideSoftInputFromWindow(winde.getDecorView().getWindowToken(), 0)
//    }

}
