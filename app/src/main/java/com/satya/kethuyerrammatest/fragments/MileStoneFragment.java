package com.satya.kethuyerrammatest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.satya.kethuyerrammatest.R;

/**
 * Created by satya on 11-Jun-17.
 */

public class MileStoneFragment extends Fragment {
    public MileStoneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.milestone_fragment, container, false);
    }
}
