package com.pactera.bean.base

import java.io.Serializable

/**
 *  基础类
 */
open class BaseBean: Serializable{
    open var code: String = "1"
    open var msg: String =""
}
