package com.pactera.viewmodel.base

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.pactera.bean.Goods
import java.util.HashMap

/**
 *
 */
abstract class RecyclerViewViewModel2 : BaseViewModel() {

    var list = MutableLiveData<List<Goods>>()

    var page = 1

    var hashMap = HashMap<String, String>()

    val limit = 10

    fun initData() {
        // 将page重置为1
        page = 1
        refreshParamsMap(page)
        println(hashMap)
        this.getDataFromNet(hashMap)
    }


    fun loadMoreData() {
        page += 1
        refreshParamsMap(page)
        println(hashMap)
        this.getDataFromNet(hashMap)
    }


    /**
     *  生成各个参数
     */
    fun refreshParamsMap(page: Int){
        this.page = page
        hashMap["page"] = page.toString()
        hashMap["offset"] = getOffset(page)
        hashMap["limit"] = limit.toString()
    }


    /**
     *  得到偏移量
     */
    protected fun getOffset(page: Int): String{
        return ((page-1)* limit).toString()
    }

    abstract fun getDataFromNet(hashMap: HashMap<String, String>);

}