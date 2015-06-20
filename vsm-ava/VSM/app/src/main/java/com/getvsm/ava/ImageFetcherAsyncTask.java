package com.getvsm.ava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageFetcherAsyncTask extends AsyncTask<String, Integer, Bitmap> {

    ImageView view;
//	ProgressDialog progress = null;

    public ImageFetcherAsyncTask(ImageView view) {
        // TODO Auto-generated constructor stub
        this.view = view;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        // TODO Auto-generated method stub

        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory
                    .decodeStream((InputStream) new URL("http://"+params[0].split("//")[1]).getContent());
            publishProgress(0);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            return bitmap;
        }

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        // TODO Auto-generated method stub
        super.onProgressUpdate(values);
//		progress = new ProgressDialog(MainActivity.applicationContext);
//		progress.setMessage("Fetching images.. ");
//		progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//		progress.setIndeterminate(true);
//		progress.setCancelable(false);
//		progress.show();
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        this.view.setImageBitmap(result);
//
//		progress.hide();

    }

}
