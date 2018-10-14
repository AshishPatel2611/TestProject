package com.example.ashishpatel.myapplication.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by codexalters on 31/1/18.
 */

public class ImageDownloader extends AsyncTask<String, Integer, String> {

    private static final String TAG = "ImageDownloader";
    ProgressDialog pDialog;
    Activity activity;
    File file;
    Boolean isForSharing = false; // to determine where to store file (cache memory or external folder)
    Handler handler;

    public ImageDownloader(@Nullable FragmentActivity activity, @NotNull Handler mImageDownloadHandler, boolean isForSharing) {
        this.activity = activity;
        this.isForSharing = isForSharing;
        this.handler = mImageDownloadHandler;
    }

    @Override
    protected String doInBackground(String... strings) {

        Log.i(TAG, "doInBackground: " + strings[0]);
        if (isForSharing) {
            File root = activity.getCacheDir();
            File directory = new File(root.getAbsolutePath() + "/FreeWishImages");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            file = new File(directory, "freewish_" + System.currentTimeMillis() + ".jpeg");

        } else {
            File root = android.os.Environment.getExternalStorageDirectory();
            File directory = new File(root.getAbsolutePath() + "/Pictures/FreeWish");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            file = new File(directory, "freewish_" + System.currentTimeMillis() + ".jpeg");
        }
        int count;
        try {
            URL url = new URL(strings[0]);
            URLConnection conection = url.openConnection();
            conection.connect();
            // getting file length
            int lengthOfFile = conection.getContentLength();

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            // Output stream to write file
            OutputStream output = new FileOutputStream(file);

            byte data[] = new byte[1024];

            long total = 0;

            count = input.read(data);
            while (count != -1) {
                total += count;
                // publishing the progress....
                // After this onProgressUpdate will be called
                publishProgress((int) ((total * 100) / lengthOfFile));

                // writing data to file
                output.write(data, 0, count);
                count = input.read(data);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();
        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());

            Message msg = new Message();
            msg.what = 1; // fail
            handler.sendMessage(msg);

        }

        return null;

    }

    @Override
    protected void onPreExecute() {
        Log.i(TAG, "onPreExecute: ");
        super.onPreExecute();
        if (!isForSharing) {
            pDialog = new ProgressDialog(activity);
            pDialog.setMessage("Downloading file. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setMax(100);
            pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pDialog.setCancelable(false);
            pDialog.show();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        Log.i(TAG, "onPostExecute: ");
        super.onPostExecute(s);
        if (!isForSharing) {
            pDialog.dismiss();
        }

        Message msg = new Message();
        msg.what = 0; // success
        if (isForSharing) {
            msg.arg1 = 0; // for share intent
        } else {
            msg.arg1 = 1; // only for download
        }
        msg.obj = file;
        handler.sendMessage(msg);

        if (!isForSharing)
            Toast.makeText(activity, "Image downloaded at\n" + file.getPath(), Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        if (!isForSharing) {
            super.onProgressUpdate(values);
            Log.i(TAG, "onProgressUpdate: " + values[0]);
            pDialog.setProgress(values[0]);
        }
    }


    /*
    * without progress
    *
    *   /*   URL url = null;
        try {
            url = new URL(strings[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection ucon = null;

        try {
            ucon = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // getting file length
        int lengthOfFile = ucon.getContentLength();


        InputStream is = null;
        try {
            is = ucon.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayBuffer baf = new ByteArrayBuffer(50);
        int current = 0;
        long total = 0;
        try {
            while ((current = bis.read()) != -1) {
                total += current;
                baf.append((byte) current);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream output = null;
        try {
            output = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            output.write(baf.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
//        return null;*/
}
