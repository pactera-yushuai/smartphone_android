package com.pactera.bean.base

import java.io.Serializable
import java.util.ArrayList

/**
 *  带数组的基础类
 */
open class BaseArrayBean<T> {

    open var array: ArrayList<T> = ArrayList()

    open var totalElements = ""

    override fun toString(): String {
        return "BaseArrayBean(list=$array, totalElements='$totalElements')"
    }


}


open class BaseArrayBean2<T>: Serializable {

    var code = ""
    var message = ""

    var data = MyData<T>()

    class MyData<T>: Serializable {

        var list : ArrayList<T> = ArrayList()
        var totalElements = ""

        override fun toString(): String {
            return "MyData(list=$list, totalElements='$totalElements')"
        }

    }


    override fun toString(): String {
        return "BaseArrayBean2(code='$code', message='$message', data=$data)"
    }


}
