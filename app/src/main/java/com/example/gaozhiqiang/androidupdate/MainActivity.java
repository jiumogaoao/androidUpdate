package com.example.gaozhiqiang.androidupdate;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.Menu;

import java.io.File;

public class MainActivity extends Activity {

    private final String TAG="MainActivity";
    private String pathName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pathName=this.getFilesDir().getParent();
        Log.d(TAG, "Environment.getExternalStorageDirectory()="+Environment.getExternalStorageDirectory());
        Log.d(TAG, "getCacheDir().getAbsolutePath()="+getCacheDir().getAbsolutePath());
        deleteFolder(new File(pathName + "/app_download"));
        deleteFolder(new File(pathName + "/app_h5"));
        //showDownLoadDialog();
        //doZipExtractorWork();
        doDownLoadWork();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void showDownLoadDialog(){
        new AlertDialog.Builder(this).setTitle("确认")
                .setMessage("是否下载？")
                .setPositiveButton("是", new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Log.d(TAG, "onClick 1 = "+which);
                        doDownLoadWork();
                    }
                })
                .setNegativeButton("否", new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Log.d(TAG, "onClick 2 = "+which);
                    }
                })
                .show();
    }
    public  void deleteFolder(File file) {
        if (!file.exists())
            return;

        if (file.isDirectory()) {
            File files[] = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFolder(files[i]);
            }
        }
        file.delete();
    }
    public void doZipExtractorWork(){
        //ZipExtractorTask task = new ZipExtractorTask("/storage/usb3/system.zip", "/storage/emulated/legacy/", this, true);
        File file=this.getDir("h5", Context.MODE_PRIVATE);
        String zipPath=file.getAbsolutePath();
        ZipExtractorTask task = new ZipExtractorTask(pathName+"/app_download/5.zip",zipPath, this, true);
        task.execute();
    }

    private void doDownLoadWork(){
        File file=this.getDir("download", Context.MODE_PRIVATE);
        String filePath=file.getAbsolutePath();
        DownLoaderTask task = new DownLoaderTask("http://192.168.1.102/down/5.zip", filePath, this);
        //DownLoaderTask task = new DownLoaderTask("http://192.168.9.155/johnny/test.h264", getCacheDir().getAbsolutePath()+"/", this);
        task.execute();
    }

}