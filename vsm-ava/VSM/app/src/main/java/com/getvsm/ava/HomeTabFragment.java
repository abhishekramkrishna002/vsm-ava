package com.getvsm.ava;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.AbstractCollection;


public class HomeTabFragment extends Fragment implements View.OnClickListener {

    Activity activity;
    Button allCourses, enrolledCourses, completedCourses;

    public HomeTabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_tab_layout, container, false);
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

        Toolbar toolbar
                = (Toolbar) activity.findViewById(R.id.app_bar);

        ((ActionBarActivity) activity).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        toolbar.setTitleTextColor(Color.BLACK);
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
        ft.replace(R.id.sub_tab_fragent_container, new ListCourseFragment());
        ft.commit();

        allCourses = (Button) activity.findViewById(R.id.tab_1);
        enrolledCourses = (Button) activity.findViewById(R.id.tab_2);
        completedCourses = (Button) activity.findViewById(R.id.tab_3);
        allCourses.setOnClickListener(this);
        enrolledCourses.setOnClickListener(this);
        completedCourses.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();

        switch (v.getId()) {
            case R.id.tab_1:

                allCourses.setBackgroundResource(R.drawable.red_background);
                allCourses.setTextColor(Color.BLACK);
                enrolledCourses.setBackgroundResource(R.drawable.gray_border);
                enrolledCourses.setTextColor(Color.GRAY);
                completedCourses.setBackgroundResource(R.drawable.gray_border);
                completedCourses.setTextColor(Color.GRAY);
                ft.replace(R.id.sub_tab_fragent_container, new ListCourseFragment());

                break;
            case R.id.tab_2:

                allCourses.setBackgroundResource(R.drawable.gray_border);
                allCourses.setTextColor(Color.GRAY);
                enrolledCourses.setBackgroundResource(R.drawable.red_background);
                enrolledCourses.setTextColor(Color.BLACK);
                completedCourses.setBackgroundResource(R.drawable.gray_border);
                completedCourses.setTextColor(Color.GRAY);
                ft.replace(R.id.sub_tab_fragent_container, new EnrolledListCourseFragment());

                break;
            case R.id.tab_3:

                allCourses.setBackgroundResource(R.drawable.gray_border);
                allCourses.setTextColor(Color.GRAY);
                enrolledCourses.setBackgroundResource(R.drawable.gray_border);
                enrolledCourses.setTextColor(Color.GRAY);
                completedCourses.setBackgroundResource(R.drawable.red_background);
                completedCourses.setTextColor(Color.BLACK);
                ft.replace(R.id.sub_tab_fragent_container, new CompletedListCourseFragment());

                break;
        }
        ft.commit();
    }
}

