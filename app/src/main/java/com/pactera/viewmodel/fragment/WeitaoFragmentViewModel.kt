package com.pactera.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import com.pactera.viewmodel.base.BaseViewModel
import com.google.gson.Gson
import com.pactera.bean.WeiTao1
import com.pactera.bean.WeiTao2
import com.pactera.bean.WeiTaoBean
import com.pactera.utils.MyOkHttpUtils
import com.pactera.utils.Urls
import java.util.HashMap


/**
 *
 */
class WeitaoFragmentViewModel: BaseViewModel(){

    val list1 = MutableLiveData<ArrayList<WeiTao1>>()
    val list2 = MutableLiveData<ArrayList<WeiTao2>>()

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

        MyOkHttpUtils.getReturnWithParamsGeneric(Urls.SECOND_URL, map){ res, ioe ->
            res?.let {
                if(res.isSuccessful){
                    val temp = res.body()?.string()
                    val result = Gson().fromJson<WeiTaoBean>(temp, WeiTaoBean::class.java)
                    println(result)
                    list1.postValue(result.array1)
                    list2.postValue(result.array2)
                }else{
                    println(res.body()?.string())
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

