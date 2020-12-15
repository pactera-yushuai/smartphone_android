package com.pactera.download;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.pactera.R;
import com.pactera.activity.MainActivity;
import com.pactera.asynctask.DownloadTask;

import java.io.File;

public class DownloadService extends Service {

    private DownloadTask downloadTask;

    private String downloadUrl;

    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("Downloading...", progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            //下载成功通知前台关闭通知，并创建下一个通知
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Success", -1));
            Toast.makeText(DownloadService.this, "Download Success", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onFailed() {
            downloadTask = null;
            //下载失败时将前台服务关闭，并创建下一个失败通知
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Failed", -1));
            Toast.makeText(DownloadService.this, "Download Failed", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onPaused() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, "Download Paused", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this, "Download Canceled", Toast.LENGTH_SHORT).show();

        }
    };

    public DownloadService() {
    }


    private DownloadBinder mBinder = new DownloadBinder(downloadTask, downloadUrl, listener, this);

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

//    public class DownloadBinder extends Binder {
//        public void startDownload(String url) {
//            if (downloadTask == null) {
//                downloadUrl = url;
//                downloadTask = new DownloadTask(listener);
//                downloadTask.execute(downloadUrl);
//                startForeground(1, getNotification("下载...", 0));
//                Toast.makeText(DownloadService.this, "Downloading...", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        public void pausedDownload() {
//            if (downloadTask != null) {
//                downloadTask.pauseDownload();
//            }
//        }
//
//        public void cancelDownload() {
//            if (downloadTask != null) {
//                downloadTask.cancelDownlag();
//            } else {
//                if (downloadUrl != null) {
//                    //取消下载时 需要将文件删除，并将通知关闭
//                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
//                    //下载路径
//                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
//                    File file = new File(directory + fileName);
//                    //如果文件存在 记录文件的长度
//                    if (file.exists()) {
//                        file.delete();
//                    }
//                    getNotificationManager().cancel(1);
//                    stopForeground(true);
//                    Toast.makeText(DownloadService.this, "Cancel", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        }
//
//    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title, int progress) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if (progress > 0) {
            //当progress >0 或 =0 的时候才需要显示现在进度
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);

        }
        return builder.build();
    }

}
