package com.pactera.bean

import java.io.Serializable




/**
 *  用于正确的返回信息
 */
class Success: Serializable{

    var msg = ""
    var message = ""
    var count = ""

    override fun toString(): String {
        return "Success(msg='$msg', message='$message', count='$count')"
    }

}