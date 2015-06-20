package com.getvsm.ava;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by fasal on 19-06-2015.
 */
public class ListAsync extends AsyncTask<String ,Integer,String> {
    Activity activity;

    ProgressDialog progress = null;
    public ListAsync(Activity activity) {
        this.activity = activity;
    }
    @Override
    protected String doInBackground(String... params) {
        publishProgress(0);
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://ava.getvsm.com/api/v1/courses/all");
        httpGet.addHeader("Authorization","b9199b70f1df46610c550694bfa26d33");
        httpGet.addHeader("X-Console-Key","5690dddfa28ae085d23518a035707282");

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String result = EntityUtils.toString(httpEntity);
            Log.d("TAG",result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("TAG",s);
        try
        {
            JSONObject object=new JSONObject(s);
            JSONArray allCourses=object.getJSONArray("all_courses");
            ArrayList<JSONObject> courses=new ArrayList<>();
            for(int i=0;i<allCourses.length();i++)
            {
                JSONObject course=allCourses.getJSONObject(i);
                courses.add(course);

            }
            AllCoursesAdapter coursesAdapter=new AllCoursesAdapter(activity,R.layout.list_course_item_layout,courses);
            ListCourseFragment.coursesListView.setAdapter(coursesAdapter);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            progress.hide();
        }
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
}
