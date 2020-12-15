package com.pactera.bean


import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 *  Banner
 */
class Banner : Serializable {

    @SerializedName("id")
    var id = ""
    @SerializedName("url_small")
    var urlSmall = ""
    @SerializedName("url_big")
    var urlBig = ""

    override fun toString(): String {
        return "Banner(id='$id', urlSmall='$urlSmall', urlBig=$urlBig)"
    }

}

