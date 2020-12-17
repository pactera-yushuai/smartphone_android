package com.pactera.common

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.text.TextUtils
import android.view.Gravity
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import java.io.File

/**
 * 共有类
 */
object Common {


    fun initNoScrollRecyclerView(mRecyclerView: RecyclerView, context: Context) {

        val linearLayoutManager = object : LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        mRecyclerView.layoutManager = linearLayoutManager

    }

    fun initRecyclerView(mRecyclerView: RecyclerView, context: Context) {

        mRecyclerView.layoutManager = com.pactera.expand.RecyclerViewNoBugLinearLayoutManager(context)

        //设置item的动画，可以不设置
        mRecyclerView.itemAnimator = DefaultItemAnimator()

    }

    fun initHRecyclerView(mRecyclerView: RecyclerView, context: Context) {

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        mRecyclerView.layoutManager = linearLayoutManager

        //设置item的动画，可以不设置
        mRecyclerView.itemAnimator = DefaultItemAnimator()

    }



    /**
     *  监听CheckBox的变化
     */
    fun observeCheckBox(checkBox : Array<CheckBox?>){
        checkBox[0]!!.setOnClickListener {
            if (checkBox[0]!!.isChecked) {
                checkBox.filter { it != checkBox[0] }.forEach { it!!.isChecked = false }
            }
        }

        for (i in 1 until checkBox.size) {
            checkBox[i]!!.setOnClickListener {
                if (checkBox[i]!!.isChecked) {
                    checkBox[0]!!.isChecked = false
                }
                if (checkBox.filter { it != checkBox[0] }.all { it!!.isChecked }) {
                    checkBox[0]!!.isChecked = true
                    xuanzebuxian(checkBox)
                }
            }
        }
    }


    /**
     * 当所有的复选框都选中时，直接切换到不限
     *
     * @param checkBox
     */
    fun xuanzebuxian(checkBox: Array<CheckBox?>) {
        for (i in 1 until checkBox.size) {
            checkBox[i]!!.isChecked = false
        }
    }



    fun showToast(context: Context, string: String) {

        val toast = Toast.makeText(context, string, Toast.LENGTH_SHORT)

        toast.show()

    }


    /**
     * Try to return the absolute file path from the given Uri  兼容了file:///开头的 和 content://开头的情况
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    fun getRealFilePathFromUri(context: Context, uri: Uri?): String? {
        if (null == uri) return null
        val scheme = uri.scheme
        var data: String? = null
        if (scheme == null)
            data = uri.path
        else if (ContentResolver.SCHEME_FILE == scheme) {
            data = uri.path
        } else if (ContentResolver.SCHEME_CONTENT == scheme) {
            val cursor = context.contentResolver.query(uri, arrayOf(MediaStore.Images.ImageColumns.DATA), null, null, null)
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    val index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                    if (index > -1) {
                        data = cursor.getString(index)
                    }
                }
                cursor.close()
            }
        }
        return data
    }


    /**
     * 检查文件夹是否存在
     */
    fun checkDirPath(dirPath: String): String {
        if (TextUtils.isEmpty(dirPath)) {
            return ""
        }
        val dir = File(dirPath)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dirPath
    }


}
