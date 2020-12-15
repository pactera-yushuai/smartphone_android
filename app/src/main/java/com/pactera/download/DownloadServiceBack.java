package com.pactera.download;

import java.io.File;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.autonavi.amap.mapcore.FileUtil;
import com.pactera.enums.DownloadJ;


public class DownloadServiceBack extends Service {
    public static final int Flag_Init = 0; // 初始状态
    public static final int Flag_Down = 1; // 下载状态
    public static final int Flag_Pause = 2; // 暂停状态
    public static final int Flag_Done = 3; // 完成状态
    String url;//下载地址
    String filetype;//文件类型
    String fileName;//文件名
    private int progress = 0;// 下载进度
    public int getProgress() {
        return progress;
    }
    private int flag;// 下载状态标志
    public int getFlag() {
        return flag;
    }
    DownThread mThread;
    Downloader downloader;
    private static DownloadServiceBack instance;
    public static DownloadServiceBack getInstance() {
//        if(instance == null){
//            instance = new DownloadService();
//        }
        return instance;
    }

//    public DownloadService() {
//        instance = new DownloadService();
//    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i("pactera", "service.........onCreate");
        instance = this;
        flag = Flag_Init;
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.i("pactera", "service.........onStart");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String msg = intent.getExtras().getString("flag");//通过标识判断下载状态
        url = intent.getExtras().getString("url");
        fileName= FileUtil.getName(url);
        if (mThread == null){
            mThread = new DownThread();
        }
        if(downloader == null)
        {
            downloader = new Downloader(this, url, fileName);
        }
        downloader.setDownloadListenerBack(downListener);

        if (msg.equals("start"))
        {
            startDownload();
        }
        else if (msg.equals("pause"))
        {
            downloader.pause();
        }
        else if (msg.equals("resume"))
        {
            downloader.resume();
        }
        else if (msg.equals("stop"))
        {
            downloader.cancel();
            stopSelf();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void startDownload() {
        if (flag == Flag_Init || flag == Flag_Pause) {
            if (mThread != null && !mThread.isAlive()) {
                mThread = new DownThread();
            }
            mThread.start();
        }
    }

    @Override
    public void onDestroy() {
        Log.e("xuxu", "service...........onDestroy");
        try {
            flag = 0;
            mThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mThread = null;
        super.onDestroy();
    }

    class DownThread extends Thread {

        @Override
        public void run() {

            if (flag == Flag_Init || flag == Flag_Done) {
                flag = Flag_Down;
            }

            downloader.start();
        }
    }

    /**
     * 下载监听
     */
    private DownloadListenerBack downListener = new DownloadListenerBack() {

        int fileSize;
        Intent intent = new Intent();

        @Override
        public void onSuccess(File file) {
            intent.setAction(DownloadJ.ACTION_DOWNLOAD_SUCCESS.toString());
            intent.putExtra("progress", 100);
            intent.putExtra("file", file);
            sendBroadcast(intent);
        }

        @Override
        public void onStart(int fileByteSize) {
            fileSize = fileByteSize;
            flag = Flag_Down;
        }

        @Override
        public void onResume() {
            flag = Flag_Down;
        }

        @Override
        public void onProgress(int receivedBytes) {
            if(flag == Flag_Down)
            {
                progress = (int)((receivedBytes / (float)fileSize) * 100);
                intent.setAction(DownloadJ.ACTION_DOWNLOAD_PROGRESS.toString());
                intent.putExtra("progress", progress);
                sendBroadcast(intent);
                Log.d("download progress:", String.valueOf(progress));
                if (progress == 100) {
                    flag = Flag_Done;
                }
            }
        }

        @Override
        public void onPause() {
            flag = Flag_Pause;
        }

        @Override
        public void onFail() {
            intent.setAction(DownloadJ.ACTION_DOWNLOAD_FAIL.toString());
            sendBroadcast(intent);
            flag = Flag_Init;
        }

        @Override
        public void onCancel(){
            progress = 0;
            flag = Flag_Init;
        }
    };

}
