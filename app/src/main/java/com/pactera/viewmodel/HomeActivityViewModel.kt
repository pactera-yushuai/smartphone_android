package com.pactera.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *  Activity与Fragment共享数据
 */
class HomeActivityViewModel : ViewModel() {

    var temp = MutableLiveData<String>()

    fun setData(temp1 : String){
        temp.value = temp1
    }

    fun getData(): String{
        return temp.value ?: "temp"
    }

}