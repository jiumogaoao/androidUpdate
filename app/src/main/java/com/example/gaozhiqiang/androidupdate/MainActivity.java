package com.example.gaozhiqiang.androidupdate;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    private final String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Environment.getExternalStorageDirectory()="+Environment.getExternalStorageDirectory());
        Log.d(TAG, "getCacheDir().getAbsolutePath()="+getCacheDir().getAbsolutePath());

        showDownLoadDialog();
        //doZipExtractorWork();
        //doDownLoadWork();
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

    public void showUnzipDialog(){
        new AlertDialog.Builder(this).setTitle("确认")
                .setMessage("是否解压？")
                .setPositiveButton("是", new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Log.d(TAG, "onClick 1 = "+which);
                        doZipExtractorWork();
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

    public void doZipExtractorWork(){
        //ZipExtractorTask task = new ZipExtractorTask("/storage/usb3/system.zip", "/storage/emulated/legacy/", this, true);
        ZipExtractorTask task = new ZipExtractorTask("/storage/emulated/legacy/2.zip", "/storage/emulated/legacy/", this, true);
        task.execute();
    }

    private void doDownLoadWork(){
        DownLoaderTask task = new DownLoaderTask("http://192.168.1.139/down/2.zip", "/storage/emulated/legacy/", this);
        //DownLoaderTask task = new DownLoaderTask("http://192.168.9.155/johnny/test.h264", getCacheDir().getAbsolutePath()+"/", this);
        task.execute();
    }

}