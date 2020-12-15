package com.pactera.download;

import android.app.Service;
import android.content.Context;
import android.os.Binder;
import android.os.Environment;
import android.widget.Toast;

import com.pactera.asynctask.DownloadTask;

import java.io.File;

public class DownloadBinder extends Binder {

    DownloadTask downloadTask;

    String downloadUrl;

    DownloadListener listener;

    Service service;

    public DownloadBinder(DownloadTask downloadTask, String downloadUrl, DownloadListener listener, Service context) {
        this.downloadTask = downloadTask;
        this.downloadUrl = downloadUrl;
        this.listener = listener;
        this.service = context;
    }

    public void startDownload(String url) {
        if (downloadTask == null) {
            downloadUrl = url;
            downloadTask = new DownloadTask(listener);
            downloadTask.execute(downloadUrl);
//            service.startForeground(1, service.getNotification("下载...", 0));
            Toast.makeText(service, "Downloading...", Toast.LENGTH_SHORT).show();
        }
    }

    public void pausedDownload() {
        if (downloadTask != null) {
            downloadTask.pauseDownload();
        }
    }

    public void cancelDownload() {
        if (downloadTask != null) {
            downloadTask.cancelDownlag();
        } else {
            if (downloadUrl != null) {
                //取消下载时 需要将文件删除，并将通知关闭
                String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                //下载路径
                String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                File file = new File(directory + fileName);
                //如果文件存在 记录文件的长度
                if (file.exists()) {
                    file.delete();
                }
//                getNotificationManager().cancel(1);
                service.stopForeground(true);
                Toast.makeText(service, "Cancel", Toast.LENGTH_SHORT).show();

            }
        }
    }

}
