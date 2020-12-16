package com.pactera.utils

import android.os.Environment
import android.os.FileUtils
import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import java.io.*
import java.net.URLEncoder
import java.util.*
import java.util.concurrent.TimeUnit


object MyOkHttpUtils {


    private val MEDIA_TYPE_JPG = MediaType.parse("image/jpg")

    private var okHttpClient: OkHttpClient? = null

    private val myOkHttpUtils: OkHttpClient
        get() {
            if (okHttpClient == null) {
                okHttpClient = OkHttpClient()
                        .newBuilder()
                        .connectTimeout(60, TimeUnit.SECONDS)//设置连接超时时间
                        .readTimeout(60, TimeUnit.SECONDS)//设置读取超时时间
                        .build()
            }
            return okHttpClient!!
        }

    /**
     *  不传参数，只传请求头的get
     */
    fun getReturnWithGeneric(url: String, myHttpCallBackWithGeneric: (res: Response?, ioe: IOException?) -> Unit) {

        val request = Request.Builder().url(url).method("GET", null)
                .header("Content-Type", "application/json")
                .addHeader("Authorization", "bearer " + SharedPreferencesUtils.getstring("token", "")).build()

        val mcall = myOkHttpUtils.newCall(request)

        mcall.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                myHttpCallBackWithGeneric.invoke(null, e)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                myHttpCallBackWithGeneric.invoke(response, null)
            }
        })

    }


    /**
     *  传递参数的get
     */
    fun getReturnWithParamsGeneric(url: String, params: HashMap<String, String>, myHttpCallBackWithGeneric: (res: Response?, ioe: IOException?) -> Unit) {
        Log.d("request url:", url)
        val keys = params.keys.iterator()
        val values = params.values.iterator()
        val stringBuilder = StringBuilder()
        stringBuilder.append("?")

        for (i in 0 until params.size) {
            var value: String? = null
            try {
                value = URLEncoder.encode(values.next(), "utf-8")
            } catch (e: Exception) {
                e.printStackTrace()
            }

            stringBuilder.append(keys.next() + "=" + value)
            if (i != params.size - 1) {
                stringBuilder.append("&")
            }

        }

        val request1 = myOkHttpUtils.newBuilder().connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).build()

        val request = Request.Builder()


                .url(url + stringBuilder.toString()).method("GET", null)
                .header("Content-Type", "application/json")
                .addHeader("Authorization", "bearer " + SharedPreferencesUtils.getstring("token", ""))

                .build()


        val mcall = request1.newCall(request)

        mcall.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                myHttpCallBackWithGeneric.invoke(null, e)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                myHttpCallBackWithGeneric.invoke(response, null)
            }
        })

    }



    /**
     * Description: 以表单形式上传文本及多图
     * @param url 接口地址
     * @param params 文本参数
     * @param images 图片集合
     * @param okCallBackByActivity 回调接口
     * @throws FileNotFoundException FileNotFoundException
     */
    @Throws(FileNotFoundException::class)
    fun postImage(url: String, params: Map<String, String>?, images: HashMap<String, String>?, myHttpCallBackWithGeneric: (res: Response?, ioe: IOException?) -> Unit) {

        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)

        //组装文本
        params?.let {
            for ((k, v) in params) {
                Log.d("", "post text: $k=====$v")
                if(k.isNotBlank() && v.isNotBlank()){
                    builder.addFormDataPart(k, v)
                }
            }
        }

        //组装图片
        images?.let {

            if(images.isNotEmpty()){

                for ((k, v) in images) {

                    val s = images[k]

                    if(s != ""){

                        try {
                            val file = File(s)
                            if (!file.exists()) {
                                throw FileNotFoundException("上传文件不存在")
                            }else{

                                val name = file.name
                                Log.d("", "post image: $name")

                                builder.addFormDataPart(k, Date().time.toString() + ".jpg", RequestBody.create(MEDIA_TYPE_JPG, file))
                            }

                        } catch (e: FileNotFoundException) {
                            e.printStackTrace()
                        }

                    }

                }

            }

        }

        val build = builder.build()
        val request = Request.Builder().url(url).addHeader("Authorization", "bearer " + SharedPreferencesUtils.getstring("token", "")).post(build).build()
        myOkHttpUtils.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                myHttpCallBackWithGeneric.invoke(null, e)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                myHttpCallBackWithGeneric.invoke(response, null)
            }
        })
    }



    /**
     * Description: 以表单形式上传文本及多图
     * @param url 接口地址
     * @param params 文本参数
     * @param images 图片集合
     * @param okCallBackByActivity 回调接口
     * @throws FileNotFoundException FileNotFoundException
     */
    @Throws(FileNotFoundException::class)
    fun putImage(url: String, params: Map<String, String>?, images: HashMap<String, String>?, myHttpCallBackWithGeneric: (res: Response?, ioe: IOException?) -> Unit) {

        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)

        //组装文本
        params?.let {
            for ((k, v) in params) {
                Log.d("", "post text: $k=====$v")
                if(k.isNotBlank() && v.isNotBlank()){
                    builder.addFormDataPart(k, v)
                }
            }
        }

        //组装图片
        images?.let {

            if(images.isNotEmpty()){

                for ((k, v) in images) {

                    val s = images[k]

                    if(s != ""){

                        try {
                            val file = File(s)
                            if (!file.exists()) {
                                throw FileNotFoundException("上传文件不存在")
                            }else{

                                val name = file.name
                                Log.d("", "post image: $name")

                                builder.addFormDataPart(k, Date().time.toString() + ".jpg", RequestBody.create(MEDIA_TYPE_JPG, file))
                            }

                        } catch (e: FileNotFoundException) {
                            e.printStackTrace()
                        }

                    }

                }

            }

        }

        val build = builder.build()
        val request = Request.Builder().url(url).addHeader("Authorization", "bearer " + SharedPreferencesUtils.getstring("token", "")).put(build).build()
        myOkHttpUtils.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                myHttpCallBackWithGeneric.invoke(null, e)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                myHttpCallBackWithGeneric.invoke(response, null)
            }
        })
    }


    /**
     * Description: 以表单形式上传文本
     * @param url 接口地址
     * @param params 文本参数
     * @param images 图片集合
     * @param okCallBackByActivity 回调接口
     */
    fun putReturnWithParams(url: String, jsonString: String, myHttpCallBackWithGeneric: (res: Response?, ioe: IOException?) -> Unit) {

        val requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString)

        val request = Request.Builder().url(url).addHeader("Authorization", "bearer " + SharedPreferencesUtils.getstring("token", "")).put(requestBody).build()

        myOkHttpUtils.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                myHttpCallBackWithGeneric.invoke(null, e)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                myHttpCallBackWithGeneric.invoke(response, null)
            }
        })

    }



    /***
     * 表单提交(泛型解析)
     */
    fun postFormBodyWithGerinic(url: String, kv: Map<String, String>, myHttpCallBackWithGeneric: (res: Response?, ioe: IOException?) -> Unit) {

        val builder = FormBody.Builder()

        for ((k, v) in kv) {

            if(k.isNotBlank() && v.isNotBlank()){
                builder.add(k, v)
            }

        }

        val build = builder.build()
        val request = Request.Builder().url(url).post(build)
                .header("Content-Type", "application/json")
                .addHeader("Authorization", "bearer " + SharedPreferencesUtils.getstring("token", "")).build()

        myOkHttpUtils.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                myHttpCallBackWithGeneric.invoke(null, e)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                myHttpCallBackWithGeneric.invoke(response, null)
            }
        })

    }


    /***
     * 表单提交(泛型解析)
     */
    fun postJson(url: String, kv: Map<String, String>, myHttpCallBackWithGeneric: (res: Response?, ioe: IOException?) -> Unit) {
        val gsonData = FormBody.create(MediaType.parse("application/json; charset=utf-8"), Gson().toJson(kv))
        val request = Request.Builder().url(url).post(gsonData).addHeader("Authorization", "bearer " + SharedPreferencesUtils.getstring("token", ""))
                .build()
        myOkHttpUtils.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                myHttpCallBackWithGeneric.invoke(null, e)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                myHttpCallBackWithGeneric.invoke(response, null)
            }
        })

    }


    /***
     *
     */
    fun postJsonString(url: String, jsonString: String, myHttpCallBackWithGeneric: (res: Response?, ioe: IOException?) -> Unit) {

        val requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString)

        val request = Request.Builder().url(url).post(requestBody).addHeader("Authorization", "bearer " + SharedPreferencesUtils.getstring("token", ""))
                .build()

        myOkHttpUtils.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                myHttpCallBackWithGeneric.invoke(null, e)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                myHttpCallBackWithGeneric.invoke(response, null)
            }
        })

    }


    /**
     *  删除
     */
    fun deleteWithUrl(url: String, myHttpCallBackWithGeneric: (res: Response?, ioe: IOException?) -> Unit) {

        val request = Request.Builder().url(url).delete().addHeader("Authorization", "bearer " + SharedPreferencesUtils.getstring("token", ""))
                .build()

        myOkHttpUtils.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                myHttpCallBackWithGeneric.invoke(null, e)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                myHttpCallBackWithGeneric.invoke(response, null)
            }
        })

    }

    fun downLoadFile(url: String, myHttpCallBackWithGeneric: (res: Response?, ioe: IOException?) -> Unit){

        val request: Request = Request.Builder()
                .url(url)
//                .addHeader("Connection", "close")
                .build()

        myOkHttpUtils.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException) {
                e.printStackTrace()
                Log.e("一开始就失败","download failed")
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call?, response: Response) {
                myHttpCallBackWithGeneric.invoke(response, null)

            }

        })

    }


}
