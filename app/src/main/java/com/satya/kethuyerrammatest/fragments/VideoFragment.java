package com.satya.kethuyerrammatest.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.satya.kethuyerrammatest.R;
import com.satya.kethuyerrammatest.adapters.MyRecylerViewAdapter;
import com.satya.kethuyerrammatest.models.Video;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by satya on 11-Jun-17.
 */

public class VideoFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MyRecylerViewAdapter mMyVideoAdapter;
    private List<Video> mVideoArrayList = new ArrayList<>();

    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyler_view);

        mMyVideoAdapter = new MyRecylerViewAdapter(mVideoArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mMyVideoAdapter);
        addingVideoDataToArrLst();
        return view;
    }

    private void addingVideoDataToArrLst() {
        Random ra = new Random();
       // mVideoArrayList.clear();
        int[] images = {R.drawable.raviteja, R.drawable.raviteja, R.drawable.raviteja, R.drawable.raviteja, R.drawable.raviteja, R.drawable.raviteja, R.drawable.raviteja, R.drawable.raviteja, R.drawable.raviteja, R.drawable.raviteja};

        for (int i = 0; i < 10; i++) {
            Video video = new Video();
            video.setTitle("Kick " + (i + 1));
            video.setTime((ra.nextInt(24)) + " HOURS AGO");
            // video.setResouce(R.drawable.raviteja);
            video.setResouce(images[i]);
            video.setDesc("Kick is a 2014 Indian action film produced and directed by Sajid Nadiadwala under his Nadiadwala Grandson Entertainment banner." + (i + 1));
            mVideoArrayList.add(video);

        }

        mMyVideoAdapter.notifyDataSetChanged();
    }
}
