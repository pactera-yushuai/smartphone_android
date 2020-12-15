package com.pactera.viewmodel.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pactera.bean.base.BaseArrayBean
import com.pactera.utils.MyOkHttpUtils
import java.io.IOException
import java.util.HashMap

/**
 *  带有 RecyclerView 的 Activity或Fragment 的 ViewModel
 */
abstract class RecyclerViewViewModel<M> : BaseViewModel() {

    open var model = MutableLiveData<M>()

    open var type = MutableLiveData<BaseArrayBean<M>>()

    open fun getDataFromNet(map : HashMap<String, String>){

        MyOkHttpUtils.getReturnWithParamsGeneric(getGetUrl(), map ){ res, ioe ->

            if(ioe != null){
                parseRealFailure(ioe)
            }else if(res != null){
                val responseGson = res.body()!!.string()

                println("原生数据：$responseGson")

                if(res.isSuccessful && responseGson.isNotBlank()){
                    try {
//            val deviceArray: BaseArrayBean<M> = Gson().fromJson(responseGson)
//            Log.d("正常数据：",deviceArray.toString())
//            type.postValue(deviceArray)
                        parseGetJson(responseGson)
                    }catch (e: Exception){
                        println("转换错误 $e")
                        type.postValue(null)
                    }

                }else{
                    val statusCode = res.code().toString()
                    Log.e("状态码：", statusCode)
                    parseFailure(responseGson)
                }
            }

        }

    }

    open fun parseGetJson(responseGson: String) {

    }

    open fun parseFailure(responseGson:String){}

    open fun parseRealFailure(e: IOException){
        Log.e("请求失败",e.toString())
        type.postValue(null)
        realFailure.postValue(e)
    }

    open fun getDataWithUrlFromNet(url: String, map : HashMap<String, String>){}

    open fun getGetUrl(): String{
        return ""
    }

}
