package com.pactera.download

import android.content.Context
import android.os.Build
import android.util.Log
import com.pactera.utils.FileUtil
import com.pactera.utils.MediaFileUtil
import com.pactera.utils.MyOkHttpUtils
import com.pactera.utils.Urls
import okio.Buffer
import okio.buffer
import okio.sink
import java.io.*

/**
 *
 * @param context
 * @param url
 * @param filePath
 * @param fileName
 */
class Downloader(var context: Context, // 下载链接
    var urlStr: String?, // 下载路径
    var filePath: String, // 下载文件名
    var fileName: String?) {
    var downloadListenerBack: DownloadListenerBack? = null

    @JvmName("setDownloadListener1")
    fun setDownloadListener(listenerBack: DownloadListenerBack?) {
        downloadListenerBack = listenerBack
    }

    /**
     *
     * @param context
     * @param url
     * @param fileName
     */
    constructor(context: Context, url: String?, fileName: String?) : this(context, url, "/download/", fileName) {}

    /**
     * 开始下载
     */
    fun start() {

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

//                    totalLength.postValue((contentLength/1024/1024).toFloat())

                    println("Build.VERSION.SDK_INT " + Build.VERSION.SDK_INT)

                    val fileName = FileUtil.getFileName(Urls.DOWNLOAD_URL)
                    val fileType = MediaFileUtil.getFileType(fileName)?.mimeType


                    if(Build.VERSION.SDK_INT>=29){
//                        var file = File(MediaStore.Downloads.EXTERNAL_CONTENT_URI.toString(), Urls.DOWNLOAD_URL.substring(Urls.DOWNLOAD_URL.lastIndexOf("/") + 1))
                        var file = File(context.filesDir, fileName)
                        Log.d("download file location:", file.absolutePath)
//                        FileUtil.insertFileIntoMediaStore(fileName, fileType!!, MediaStore.Downloads.EXTERNAL_CONTENT_URI.toString(), context.contentResolver)

                        fos = FileOutputStream(file)
                        var sum: Int = 0
                        while (inputStream.read(buf).also { len = it } != -1) {
                            fos.write(buf, 0, len)
                            sum += len
                            Log.d("sum:", sum.toString())
                            downloadListenerBack!!.onProgress(sum)
//                            if (sum == len) {
//
//                                break
//                            }
                        }
                        downloadListenerBack!!.onSuccess(file)
                        fos.flush()
                        Log.i("download success", "download success")
//                        Log.i("download success", "totalTime=" + (System.currentTimeMillis() - startTime)+"ms")

                    }else{
//                        file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName)
                        var file = File(context.getExternalFilesDir(""), fileName)
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
//                            progressState.postValue(progress)
//                            currentLength.postValue((totalBytesRead/1024/1024).toFloat())
                        }
                        sink.flush()

                    }

                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
//                    Log.e(TAG, "download failed : " + e.message)
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

//        var url: URL? = null
//        try {
//            url = URL(urlStr)
//            val urlCon = url.openConnection() as HttpURLConnection
//            urlCon.doInput = true
//            urlCon.requestMethod = "GET"
//            urlCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
//
//            // 建立连接
//            urlCon.connect()
//            val length = urlCon.contentLength
//            downloadListener!!.onStart(length)
//            if (urlCon.responseCode == 200) {
//
////                File path = Environment.getExternalStoragePublicDirectory(filePath);
////                File path = context.getExternalFilesDir("");
//                val path = context.filesDir
//                val file = File(path, fileName)
//                Log.d("download file location:", file.absolutePath)
//                val inputStream = BufferedInputStream(urlCon.inputStream)
//                val out = BufferedOutputStream(FileOutputStream(file))
//                val buffer = ByteArray(10240)
//                var len = 0
//                var receivedBytes = 0
//                label@ while (true) {
//                    // 这里如果暂停下载，并没有真正的销毁线程，而是处于等待状态
//                    // 但如果这时候用户退出了，要做处理，比如取消任务；或做其他处理
////                    if (isPause) downloadListener!!.onPause()
////                    if (isCancel) {
////                        downloadListener!!.onCancel()
////                        break@label
////                    }
////                    try {
////                        Thread.sleep(50)
////                    } catch (e: InterruptedException) {
////                        e.printStackTrace()
////                    }
////                    while (!isPause && inputStream.read(buffer).also { len = it } > 0) {
////                        out.write(buffer, 0, len)
////                        receivedBytes += len
////                        downloadListener!!.onProgress(receivedBytes)
////                        if (receivedBytes == length) {
////                            downloadListener!!.onSuccess(file)
////                            break@label
////                        }
////                        if (isCancel) {
////                            downloadListener!!.onCancel()
////                            file.delete()
////                            break@label
////                        }
////                    }
////                    out.flush()
//                    while (inputStream.read(buffer).also { len = it } != -1) {
//                        out.write(buffer, 0, len)
//                        receivedBytes += len
//                        downloadListener!!.onProgress(receivedBytes)
//                        if (receivedBytes == length) {
//                            downloadListener!!.onSuccess(file)
//                            break@label
//                        }
////                        sum += len.toLong()
////                        val progress = (sum * 1.0f / contentLength * 100).toInt()
////                        Log.i(TAG, "download progress : $progress")
////                        progressState.postValue(progress)
//                    }
//                    out.flush()
//                }
//                inputStream.close()
//                out.close()
//            } else {
//                Log.e("jlf", "ResponseCode:" + urlCon.responseCode + ", msg:" + urlCon.responseMessage)
//            }
//        } catch (e: MalformedURLException) {
//            e.printStackTrace()
//            downloadListener!!.onFail()
//        } catch (e: IOException) {
//            e.printStackTrace()
//            downloadListener!!.onFail()
//        }
    }

    private var isPause = false
    fun pause() {
        isPause = true
    }

    fun resume() {
        isPause = false
        isCancel = false
        downloadListenerBack!!.onResume()
    }

    private var isCancel = false
    fun cancel() {
        isCancel = true
    }
}