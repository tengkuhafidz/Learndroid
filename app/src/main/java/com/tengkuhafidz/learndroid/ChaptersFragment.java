package com.tengkuhafidz.learndroid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChaptersFragment extends Fragment {

    private RecyclerView mRecyclerView;
    ChapterAdapter mChapterAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static ChaptersFragment newInstance() {
        ChaptersFragment fragment = new ChaptersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recyclerview, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // set chapters

        final ArrayList<Chapter> chapters = new ArrayList<Chapter>();
        chapters.add(new Chapter("User Interface", 100, R.drawable.user_interface));
        chapters.add(new Chapter("User Input", 44, R.drawable.user_input));
        chapters.add(new Chapter("Multi-screen Apps", 5, R.drawable.multi_screen));
        chapters.add(new Chapter("Networking", 0, R.drawable.networking));
        chapters.add(new Chapter("Data Storage", 1, R.drawable.data));



        // specify an adapter
        mChapterAdapter = new ChapterAdapter(chapters);
        mRecyclerView.setAdapter(mChapterAdapter);

        return rootView;
    }

}
