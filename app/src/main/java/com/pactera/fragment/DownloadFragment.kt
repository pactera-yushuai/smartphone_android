package com.pactera.fragment

import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.pactera.R
import com.pactera.annotation.VMScope
import com.pactera.fragment.base.BaseFragment
import com.pactera.viewmodel.fragment.DownloadFragmentViewModel
import kotlinx.android.synthetic.main.fragment_download.*
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest
import java.io.File
import kotlin.properties.Delegates



/**
 * 文件下载
 */
class DownloadFragment : BaseFragment() , EasyPermissions.PermissionCallbacks {

    private val bundle: Bundle? = null

    private val map = HashMap<String, String>()

    private val map3 = HashMap<String, String>()

    private lateinit var downloadFragmentViewModel: DownloadFragmentViewModel


    var downloadId by Delegates.notNull<Long>()

    @VMScope("download")
    lateinit var vm:DownloadFragmentViewModel


    init {

    }


    override fun initView(): View {
//        injectViewModel()
        downloadFragmentViewModel = ViewModelProvider(requireActivity()).get(DownloadFragmentViewModel::class.java)

        return View.inflate(mActivity, R.layout.fragment_download, null)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        fragment_download_button.setOnClickListener {
            if(fragment_download_progressBar.progress==100){
//                println("dakaileaaa")
//                val file = meFragmentViewModel.file
//                watch(file)
            }
            if(EasyPermissions.hasPermissions(mActivity, "android.permission.WRITE_EXTERNAL_STORAGE")){
                downloadFragmentViewModel.downLoad(mActivity)
                fragment_download_button.isClickable = false
                fragment_download_button.text = "ダウンロード中"
            }else{
                Toast.makeText(mActivity, "まず、[設定] に移動してアクセス許可を開きます", Toast.LENGTH_SHORT).show()
                EasyPermissions.requestPermissions(
                        PermissionRequest.Builder(this, 10086, "android.permission.WRITE_EXTERNAL_STORAGE")
                                .build()
//                        AppSettingsDialog.Builder(this, "android.permission.WRITE_EXTERNAL_STORAGE", perms)
//                                .setRationale(R.string.camera_and_location_rationale)
//                                .setPositiveButtonText(R.string.rationale_ask_ok)
//                                .setNegativeButtonText(R.string.rationale_ask_cancel)
//                                .setTheme(R.style.my_fancy_style)
//                                .build()
                )
            }
        }

        downloadFragmentViewModel.progressState.observe(viewLifecycleOwner){
            fragment_download_progressBar.progress = it
            if(it==100){
                Toast.makeText(mActivity, "ダウンロードが完了しました", Toast.LENGTH_SHORT).show()

                val file = downloadFragmentViewModel.file
                fragment_download_button.text = "ダウンロードが完了しました"
//                watch(file)

            }
            fragment_download_textview_2.text = " ダウンロードの進行状況：$it%"
        }

        downloadFragmentViewModel.totalLength.observe(viewLifecycleOwner){
            fragment_download_textview_1.text = "合計サイズ："+it.toString()+"M "
        }

        downloadFragmentViewModel.currentLength.observe(viewLifecycleOwner){

        }

        // uri 是你的下载地址，可以使用Uri.parse("http://")包装成Uri对象
        val req = DownloadManager.Request(Uri.parse("https://gmxjjzapi.dkvet.com/pactera.mp4"))


    }


    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        println("success：" + requestCode)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        println("failure：" + requestCode)
    }

    private fun watch(file: File){

        val builder = VmPolicy.Builder()

        StrictMode.setVmPolicy(builder.build())

        val extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString())
        val mimetype = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    builder.detectFileUriExposure()

            val photoURI = FileProvider.getUriForFile(mActivity, mActivity.applicationContext.packageName.toString() + ".provider", file)

            val intent = Intent()

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) //设置标记

            intent.action = Intent.ACTION_VIEW //动作，查看

            intent.setDataAndType(photoURI, mimetype) //设置类型
            //                intent.setDataAndType(Uri.fromFile(file), getMIMEType(file)) //设置类型

            mActivity.startActivity(intent)

        }else{

            val intent = Intent()
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) //设置标记

            intent.action = Intent.ACTION_VIEW //动作，查看

            intent.setDataAndType(Uri.fromFile(file), mimetype) //设置类型
            //                intent.setDataAndType(Uri.fromFile(file), getMIMEType(file)) //设置类型

            mActivity.startActivity(intent)

        }

    }

}
