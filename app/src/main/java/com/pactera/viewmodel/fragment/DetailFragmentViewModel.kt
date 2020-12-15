package com.pactera.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.pactera.bean.WeiTao1
import com.pactera.bean.WeiTao2
import com.pactera.bean.WeiTaoBean
import com.pactera.utils.MyOkHttpUtils

import com.pactera.utils.Urls
import com.pactera.viewmodel.base.BaseViewModel
import java.util.HashMap


class DetailFragmentViewModel: BaseViewModel(){

    val list1 = MutableLiveData<ArrayList<WeiTao1>>()
    val list2 = MutableLiveData<ArrayList<WeiTao2>>()


    fun getList() {

        val map = HashMap<String, String>()

        map["page"] = 1.toString()

        MyOkHttpUtils.getReturnWithParamsGeneric(Urls.HOME_URL, map){ res, ioe ->
            res?.let {
                if(res.isSuccessful){
                    val temp = res.body()?.string()
                    val result = Gson().fromJson<WeiTaoBean>(temp, WeiTaoBean::class.java)
                    println(result)
                    list1.postValue(result.array1)
                    list2.postValue(result.array2)
                }

            }
            ioe?.let {
                println(ioe.stackTrace.toString())
                println(ioe.message)
                println(ioe.localizedMessage)

            }
        }

    }

}