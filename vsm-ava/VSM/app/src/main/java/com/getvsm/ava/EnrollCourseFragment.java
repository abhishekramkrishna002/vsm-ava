package com.getvsm.ava;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by brabh on 5/13/2015.
 */
public class EnrollCourseFragment extends Fragment implements View.OnClickListener {

    Activity activity;
    Button allCourses, enrolledCourses, completedCourses;

    public EnrollCourseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.course_deatil_view, container, false);
        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Toast.makeText(MainActivity.acivity.getApplicationContext(),"hey",Toast.LENGTH_LONG).show();
        LinearLayout mainLayout=(LinearLayout)activity.findViewById(R.id.main_layout);
        //mainLayout.startAnimation(AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_in_botton));


        Toolbar toolbar
                = (Toolbar) activity.findViewById(R.id.app_bar);

        ((ActionBarActivity) activity).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        toolbar.setTitleTextColor(Color.GRAY);
        // toolbar.setBackgroundResource(R.drawable.transparent_gray_border);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MainActivity.mDrawerLayout.isDrawerOpen(Gravity.START)) {
                    MainActivity.mDrawerLayout.openDrawer(Gravity.START);
                } else {
                    MainActivity.mDrawerLayout.closeDrawer(Gravity.START);
                }
            }
        });


        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.sub_fragment_container, new CourseDescriptionFragment());
        ft.commit();

        Button enrollButton=(Button)activity.findViewById(R.id.enroll_button);
        enrollButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.enroll_button:
                FragmentTransaction ft=MainActivity.fm.beginTransaction();
                /*
                slide ottom to top
                 */
                ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_in_left);
                ft.replace(R.id.main_fragment_container,new EnrolledCourseFragment());
                ft.commit();
                break;
        }
    }
}


