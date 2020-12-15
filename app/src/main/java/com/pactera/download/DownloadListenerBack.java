package com.pactera.download;

import java.io.File;

public interface DownloadListenerBack {

    void onStart(int fileByteSize);

    void onPause();

    void onResume();

    void onProgress(int receivedBytes);

    void onFail();

    void onSuccess(File file);

    void onCancel();
}
