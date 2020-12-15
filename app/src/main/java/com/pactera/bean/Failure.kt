package com.pactera.bean

import java.io.Serializable


class Failure : Serializable {

    var code = ""
    var message = ""

    override fun toString(): String {
        return "Failure(code='$code', message='$message')"
    }


}