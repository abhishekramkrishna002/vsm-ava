package com.getvsm.ava;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by fasal on 19-06-2015.
 */
public class AllCoursesAdapter extends ArrayAdapter<JSONObject> {
    Activity activity;
    int resource;
    List<JSONObject> courses;
    public AllCoursesAdapter(Activity activity, int resource, List<JSONObject> courses) {
        super(activity, resource, courses);
        this.activity=activity;
        this.resource=resource;
        this.courses=courses;
    }

    @Override
    public int getCount() {
        return courses.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View view=inflater.inflate(resource, null, true);
        JSONObject course=courses.get(position);
        try {
            ((TextView) view.findViewById(R.id.course_title)).setText(course.getJSONObject("details").getString("title"));
            ((TextView) view.findViewById(R.id.course_description)).setText(course.getJSONObject("details").getString("description"));
            //new ImageFetcherAsyncTask((ImageView) view.findViewById(R.id.course_image)).execute(course.getJSONObject("details").getString("image_small"));
            //cover_pic
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return view;
    }
}
