package com.fivelove;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String urlImage = intent.getStringExtra("name");
            startDownloadImage(urlImage);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void startDownloadImage(String urlImage) {
        MyDownloadAsyncTask myDownloadAsyncTask = new MyDownloadAsyncTask(() -> {
            Toast.makeText(getApplicationContext(),"download success",Toast.LENGTH_SHORT).show();
        });
        myDownloadAsyncTask.execute(urlImage);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class MyDownloadAsyncTask extends AsyncTask<String, Void, Bitmap> {
        Callback callback;
        Context context;

        public MyDownloadAsyncTask(Callback callback) {
            this.callback = callback;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = Utils.downloadImage(strings[0]);
            Utils.saveBitmapToFile(getBaseContext(), bitmap);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            callback.onDownloadSuccess();
        }
    }


    public interface Callback {
        void onDownloadSuccess();
    }
}
