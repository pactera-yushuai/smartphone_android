package com.pactera.viewmodel.fragment


import android.content.ContentResolver
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pactera.utils.FileUtil
import com.pactera.utils.MediaFileUtil
import com.pactera.utils.MyOkHttpUtils
import com.pactera.utils.Urls
import com.pactera.viewmodel.base.BaseViewModel
import okio.Buffer
import okio.Okio
import okio.buffer
import okio.sink
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream


class DownloadFragmentViewModel() : BaseViewModel(){


    var progressState = MutableLiveData<Int>()

    var totalLength = MutableLiveData<Float>()

    var currentLength = MutableLiveData<Float>()

    lateinit var file : File

//    var bipmap = MutableLiveData<Bitmap>()
//

    /**
     *
     */
    fun downLoad(context: Context) {

        val TAG = "DownLoadFile:"

        val startTime = System.currentTimeMillis()

        Log.i(TAG, "startTime=$startTime")

        MyOkHttpUtils.downLoadFile(Urls.DOWNLOAD_URL){ res, ioe ->
            if(ioe!=null){

            }else{
                var inputStream: InputStream? = null
                val buf = ByteArray(2048)
                var len = 0
                var fos: FileOutputStream? = null

                try {
                    inputStream = res!!.body()!!.byteStream()
                    val contentLength = res.body()!!.contentLength()

                    println("inputStream：$inputStream")
                    println("总大小：$contentLength")

                    totalLength.postValue((contentLength/1024/1024).toFloat())

                    println("Build.VERSION.SDK_INT " + Build.VERSION.SDK_INT)

                    val fileName = FileUtil.getFileName(Urls.DOWNLOAD_URL)
                    val fileType = MediaFileUtil.getFileType(fileName)?.mimeType


                    if(Build.VERSION.SDK_INT>=29){
//                        var file = File(MediaStore.Downloads.EXTERNAL_CONTENT_URI.toString(), Urls.DOWNLOAD_URL.substring(Urls.DOWNLOAD_URL.lastIndexOf("/") + 1))
                        file = File(context.filesDir, fileName)
                        Log.d("download file location:", file.absolutePath)
//                        FileUtil.insertFileIntoMediaStore(fileName, fileType!!, MediaStore.Downloads.EXTERNAL_CONTENT_URI.toString(), context.contentResolver)

                        fos = FileOutputStream(file)
                        var sum: Long = 0
                        while (inputStream.read(buf).also { len = it } != -1) {
                            fos.write(buf, 0, len)
                            sum += len.toLong()
                            val progress = (sum * 1.0f / contentLength * 100).toInt()
                            Log.i(TAG, "download progress : $progress")
                            progressState.postValue(progress)
                        }
                        fos.flush()
                        Log.i(TAG, "download success")
                        Log.i(TAG, "totalTime=" + (System.currentTimeMillis() - startTime)+"ms")

                    }else{
//                        file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName)
                        file = File(context.getExternalFilesDir(""), fileName)
                        Log.d("download file location:", file.absolutePath)
                        val source = res.body()!!.source()
                        val sink = file.sink().buffer()
                        val sinkBuffer: Buffer = sink.buffer()
                        var totalBytesRead: Long = 0
                        val bufferSize = 8 * 1024
                        var bytesRead: Long
                        while (source.read(sinkBuffer, bufferSize.toLong()).also { bytesRead = it } != -1L) {
                            sink.emit()
                            totalBytesRead += bytesRead
                            val progress = (totalBytesRead * 100 / contentLength).toInt()
                            progressState.postValue(progress)
                            currentLength.postValue((totalBytesRead/1024/1024).toFloat())
                        }
                        sink.flush()

                    }

                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                    Log.e(TAG, "download failed : " + e.message)
                } finally {
                    try {
                        inputStream?.close()
                    } catch (e: IOException) {
                    }
                    try {
                        fos?.close()
                    } catch (e: IOException) {
                    }
                }
            }

        }

    }

//
//    /**
//     * 图片上传
//     * @param compressPath
//     */
//    fun uploadImage(compressPath: String?) {
//
//        try {
//            // 用BitmapFactory.decodeByteArray()方法可以把相机传回的裸数据转换成Bitmap对象
//            // Bitmap mBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//            val bitMap = BitmapFactory.decodeFile(compressPath)
//
//            // 将bitMap转为二进制上传后台网络
//            // 设置上传文件url
//            val url = Urls.UPLOADIMAGE_URL
//
//            // createScaledBitmap 将图片压缩成用户所期望的长度和宽度
//            val bm = Bitmap.createScaledBitmap(bitMap, 150, 150, true)
//
//            Log.i("bank", "压缩后图片的大小" + bm.byteCount / 1024 + "KB 宽度为" + bm.width + "高度为" + bm.height)
//
//
//            val map = HashMap<String, String>()
//
//            map["uid"] = uid
//            map["filename"] = SimpleDateFormat("yyyyMMddHHmmss").format(Date()) + ".jpg"
//            map["base64"] = BitmapUtils.Bitmap2StrByBase64(bm, 100)
//
//            MyOkHttpUtils.postJson(url, map, object : MyHttpCallBackWithGeneric<User>() {
//
//                override fun parseJson(responseGson: String) {
//                    lateinit var user: User
//                    try {
//                        user = Gson().fromJson(responseGson)
//                        Log.d("正常数据：",user.toString())
//                        user1.postValue(user)
//                        bipmap.postValue(bitMap)
//                    }catch (e:Exception){
//                        println(getString("R.string.json_fail"))
//                    }
//
//                }
//
//            })
//
//        } catch (e: Exception) {
//
//            e.printStackTrace()
//
//        }
//
//    }

}
