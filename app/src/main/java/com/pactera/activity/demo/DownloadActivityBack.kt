package com.pactera.activity.demo

import android.app.AlertDialog
import android.content.*
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.pactera.R
import com.pactera.activity.base.BaseActivity
import com.pactera.download.DownloadServiceBack
import com.pactera.enums.DownloadJ
import java.io.File


/**
 * 文件下载
 */
class DownloadActivityBack : BaseActivity() {

    var url = "https://gmxjjzapi.dkvet.com/download/pactera.mp4" // 下载链接

    // 下载进度条
    var progBar: ProgressBar? = null

    // 广播
    var receiver : MyReceiver? = null

    // 显示下载路径
    var textView: TextView? = null

    // 打开文件
    var btnOpen: Button? = null


    override fun initView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_download)
        progBar = findViewById<View>(R.id.progressBar1) as ProgressBar
        textView = findViewById<View>(R.id.text_desc) as TextView
        btnOpen = findViewById<View>(R.id.btn_open) as Button
        if (DownloadServiceBack.getInstance() != null) {
            progBar!!.progress = DownloadServiceBack.getInstance().getProgress() // 获取DownloadService下载进度
        }
        receiver = MyReceiver()
    }


    override fun onStart() {
        super.onStart()
        val filter = IntentFilter()
        filter.addAction(DownloadJ.ACTION_DOWNLOAD_PROGRESS.toString())
        filter.addAction(DownloadJ.ACTION_DOWNLOAD_SUCCESS.toString())
        filter.addAction(DownloadJ.ACTION_DOWNLOAD_FAIL.toString())
        registerReceiver(receiver, filter) // 注册广播
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver) // 注销广播
    }

    fun onClick(v: View) {
        when (v.getId()) {
            R.id.btn_down -> startDownloadService()
            R.id.button_pause -> pauseDownloadService()
            R.id.button_cancel -> stopDownloadService()
            else -> {
            }
        }
    }

    /**
     * 开始下载
     */
    fun startDownloadService() {
//        if (DownloadService.getInstance() != null
//                && DownloadService.getInstance().getFlag() !== DownloadService.Flag_Init) {
//            Toast.makeText(this, "已经在下载", Toast.LENGTH_SHORT).show()
//            return
//        }
        val it = Intent(this, DownloadServiceBack::class.java)
        it.putExtra("flag", "start")
        it.putExtra("url", url)
        it.putExtra("filetype", ".mp4") // 文件后缀名（注意要确认你下载的文件类型）
        startService(it)
        println("startDownloadService")
    }

    /**
     * 暂停下载
     */
    fun pauseDownloadService() {
        var flag: String? = null
        val f: Int = DownloadServiceBack.getInstance().getFlag()
        if (DownloadServiceBack.getInstance() != null) {
            // 如果当前已经暂停，则恢复
            flag = if (f == DownloadServiceBack.Flag_Pause) {
                "resume"
            } else if (f == DownloadServiceBack.Flag_Down) {
                "pause"
            } else {
                return
            }
        }
        val it = Intent(this, DownloadServiceBack::class.java)
        it.putExtra("flag", flag)
        startService(it)
        println("pauseDownloadService")
    }

    /**
     * 取消下载
     */
    fun stopDownloadService() {
        val it = Intent(this, DownloadServiceBack::class.java)
        it.putExtra("flag", "stop")
        startService(it)
        progBar?.progress = 0
        println("stopDownloadService")
    }

    class MyReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val action = intent.action
            var activity = context as DownloadActivityBack
            if (action == DownloadJ.ACTION_DOWNLOAD_PROGRESS.toString()) { // 下载中显示进度
                val pro = intent.extras!!.getInt("progress")
                activity.progBar?.setProgress(pro)
            } else if (action == DownloadJ.ACTION_DOWNLOAD_SUCCESS.toString()) { // 下载成功
                Toast.makeText(context, "下载成功", Toast.LENGTH_SHORT).show()
                val f: File = intent.extras!!.getSerializable("file") as File
                activity.btnOpen?.setVisibility(View.VISIBLE)
                activity.textView?.setText("文件已保存在：" + f.getAbsolutePath())
                activity.btnOpen?.setOnClickListener { activity.openFile(f) }
            } else if (action == DownloadJ.ACTION_DOWNLOAD_FAIL.toString()) { // 下载失败
                Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * 打开文件
     *
     * @param f
     */
    private fun openFile(f: File) {
        val intent = Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.action = Intent.ACTION_VIEW
        // String type = "audio";
        val type = "video/mp4" // 文件类型(word文档)
        intent.setDataAndType(Uri.fromFile(f), type)
        startActivity(intent)
    }

    /**
     * 监听返回操作
     */
    override fun onBackPressed() {
        if (DownloadServiceBack.getInstance() != null) {
            val f: Int = DownloadServiceBack.getInstance().getFlag()
            // XXX:暂停状态下退出？？？
            if (f == DownloadServiceBack.Flag_Down || f == DownloadServiceBack.Flag_Pause) {
                AlertDialog.Builder(this).setTitle("确定退出程序？").setMessage("你有未完成的下载任务")
                        .setNegativeButton("取消下载", DialogInterface.OnClickListener { dialog, which ->
                            stopDownloadService()
                            super.onBackPressed()
                        }).setPositiveButton("后台下载", DialogInterface.OnClickListener { dialog, which ->
                            if (f == DownloadServiceBack.Flag_Pause) {
                                val it = Intent(this, DownloadServiceBack::class.java)
                                it.putExtra("flag", "resume")
                                startService(it)
                            }
                            super.onBackPressed()
                        }).create().show()
                return
            }
            DownloadServiceBack.getInstance().stopSelf() //退出停止下载（也可不用则后台下载）
        }
        super.onBackPressed()
    }



}