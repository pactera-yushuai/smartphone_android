package com.pactera.bean

import java.io.Serializable

/**
 *
 */
class HomeBean: Serializable {

    var array1 = ArrayList<Banner>()

    var array2 = ArrayList<Goods>()

    override fun toString(): String {
        return "HomeBean(array1=$array1, array2=$array2)"
    }


}