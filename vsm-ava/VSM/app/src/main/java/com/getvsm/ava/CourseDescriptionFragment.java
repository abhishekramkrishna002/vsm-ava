package com.getvsm.ava;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by brabh on 5/13/2015.
 */
public class CourseDescriptionFragment extends Fragment {
    private Activity activity = null;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public CourseDescriptionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.description_layout, container, false);
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
        prepareListData();

        listAdapter = new ExpandableListAdapter(super.getActivity(), listDataHeader, listDataChild);
        expListView = (ExpandableListView) activity.findViewById(R.id.course_chapter_list);
        expListView.setAdapter(listAdapter);
    }
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Chapter 1");
        listDataHeader.add("Chapter 2");
        listDataHeader.add("Chapter 3");

        // Adding child data
        List<String> chapterOne = new ArrayList<String>();
        chapterOne.add("Topic 1");
        chapterOne.add("Topic 2");
        chapterOne.add("Topic 3");
        chapterOne.add("Topic 4");
        chapterOne.add("Topic 5");
        chapterOne.add("Topic 6");
        chapterOne.add("Topic 7");

        List<String> chapterTwo = new ArrayList<String>();
        chapterTwo.add("Topic 1");
        chapterTwo.add("Topic 2");
        chapterTwo.add("Topic 3");
        chapterTwo.add("Topic 4");
        chapterTwo.add("Topic 5");
        chapterTwo.add("Topic 6");
        chapterTwo.add("Topic 7");

        List<String> chapterThree = new ArrayList<String>();
        chapterThree.add("Topic 1");
        chapterThree.add("Topic 2");
        chapterThree.add("Topic 3");
        chapterThree.add("Topic 4");
        chapterThree.add("Topic 5");
        chapterThree.add("Topic 6");
        chapterThree.add("Topic 7");

        listDataChild.put(listDataHeader.get(0), chapterOne); // Header, Child data
        listDataChild.put(listDataHeader.get(1), chapterTwo);
        listDataChild.put(listDataHeader.get(2), chapterThree);
    }


}
