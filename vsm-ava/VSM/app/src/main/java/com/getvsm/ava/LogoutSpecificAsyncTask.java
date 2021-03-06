package com.getvsm.ava;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * Created by brabh on 5/21/2015.
 */
public class LogoutSpecificAsyncTask extends AsyncTask<String, Integer, String> {
    JSONObject obj;
    Activity activity;
    public MyWebSocketClient mWebSocketClient;
    ProgressDialog progress = null;
    String fingerprint;
    public static String oauth;

    public LogoutSpecificAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        publishProgress(0);
        fingerprint=params[0];

        try {
            HttpClient client = new DefaultHttpClient();


            HttpPost post = new HttpPost("http://" + activity.getResources().getString(R.string.server_ip) + "/vsm/api/v1/logout/index.php");
            SharedPreferences cache = activity.getSharedPreferences("Cache", activity.MODE_PRIVATE);
            String oauth = cache.getString("oauth", "oauh not found");
            post.addHeader("Authorization", oauth);
            JSONObject parms = new JSONObject();
            parms.put("fingerprint", params[0]);
            post.setEntity(new StringEntity(parms.toString()));
            HttpResponse response = client.execute(post);
            HttpEntity httpEntity = response.getEntity();
            String result = EntityUtils.toString(httpEntity);
            Log.d("logout api::Result::", result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        // TODO Auto-generated method stub
        super.onProgressUpdate(values);
        progress = new ProgressDialog(activity);
        progress.setMessage("please wait.. ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.show();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //TODO read the response ,if sucesss logout ,else throw error dialog
        Intent myService = new Intent(activity.getBaseContext(), BindService.class);
        myService.putExtra("task", "logout_fingerprint");
        myService.putExtra("fingerprint",fingerprint);
        activity.startService(myService);

        progress.hide();



    }


}