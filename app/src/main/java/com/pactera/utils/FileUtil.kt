package com.pactera.utils

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.WindowManager
import java.io.*


/**
 *
 */
object FileUtil {

    /**
     * AndroidQ以上保存图片到公共目录
     *
     * @param imageName 图片名称
     * @param imageType 图片类型
     * @param relativePath 缓存路径
     */
    fun insertFileIntoMediaStore(fileName: String, fileType: String,
                                              relativePath: String, contentResolver: ContentResolver): Uri? {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            return null
        }
        if (TextUtils.isEmpty(relativePath)) {
            return null
        }
        var insertUri: Uri? = null
        val resolver: ContentResolver = contentResolver
        val values = ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
        values.put(MediaStore.Images.Media.DESCRIPTION, fileName)
        //设置文件类型为image/*
        values.put(MediaStore.Images.Media.MIME_TYPE, fileType)
        //注意：MediaStore.Images.Media.RELATIVE_PATH需要targetSdkVersion=29,
        //故该方法只可在Android10的手机上执行
        values.put(MediaStore.Images.Media.RELATIVE_PATH, relativePath)
        val external: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        insertUri = resolver.insert(external, values)
        return insertUri
    }

    fun getFileName(url: String) = url.substring(Urls.DOWNLOAD_URL.lastIndexOf("/") + 1)

}