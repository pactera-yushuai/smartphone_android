package com.pactera.vo

import java.io.Serializable


/**
 *  从其他页面跳转到图片详情页面时传递的数据（图片名称和地址）
 */
class ImageVo : Serializable {

    var position = 0
    var title = ""
    var image = ""

    constructor()

    constructor(position: Int, title: String, image: String){
        this.position = position
        this.title = title
        this.image = image
    }

    override fun toString(): String {
        return "ImageVo(position=$position, title='$title', image='$image')"
    }


}
