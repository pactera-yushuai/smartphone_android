package com.pactera.common

import com.pactera.utils.Urls

object Convert {

    fun convertImageUrlToImage(imageUrl: String):String{
        return Urls.IMAGE_URL + imageUrl
    }

    fun convertEnglishStringToChineseString(english: String, sexMap: HashMap<String,String>) : String {

        var result = ""

        for ((k,v) in sexMap){
            if(v == english){
                result = k
            }
        }

        return result

    }


    fun convertChineseStringToEnglishString(chinese: String, hashMap: HashMap<String,String>) : String {

        var result = ""

        for ((k,v) in hashMap){
            if(k == chinese){
                result = v
            }
        }

        return result

    }

}