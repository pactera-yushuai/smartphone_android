package com.pactera.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.pactera.bean.*
import com.pactera.utils.MyOkHttpUtils
import com.pactera.utils.Urls
import com.pactera.viewmodel.base.BaseViewModel
import java.util.HashMap

class HomeFragmentViewModel : BaseViewModel(){

    val list1 = MutableLiveData<ArrayList<Banner>>()
    val list2 = MutableLiveData<ArrayList<Goods>>()

    var page = 1

    fun setPagePlus(){
        page += 1
    }

    fun reSetPage(){
        page = 1
    }

    fun getList() {

        val map = HashMap<String, String>()

        map["page"] = page.toString()

        MyOkHttpUtils.getReturnWithParamsGeneric(Urls.HOME_URL, map){ res, ioe ->
            res?.let {
                if(res.isSuccessful){
                    val temp = res.body()?.string()
                    val result = Gson().fromJson<HomeBean>(temp, HomeBean::class.java)
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