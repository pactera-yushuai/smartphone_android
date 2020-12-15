package com.pactera.bean

import java.io.Serializable
import kotlin.collections.ArrayList

/**
 *
 */
class WeiTaoBean : Serializable {

    var array1 = ArrayList<WeiTao1>()
    var array2 = ArrayList<WeiTao2>()

    override fun toString(): String {
        return "WeiTaoBean(array1=$array1, array2=$array2)"
    }

}