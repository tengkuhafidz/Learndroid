package com.tengkuhafidz.learndroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class TopicsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mTopicAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    String mChapterTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // get chapter title
        Intent intent = getIntent();
        mChapterTitle = intent.getStringExtra("chapterTitle");

        // set action bar title
        getSupportActionBar().setTitle(mChapterTitle);

        final ArrayList<Topic> topics = getAssociatedTopics(mChapterTitle);

        // specify an adapter
        mTopicAdapter = new TopicAdapter(topics);
        mRecyclerView.setAdapter(mTopicAdapter);
    }

    private ArrayList<Topic> getAssociatedTopics(String chapterTitle) {

        ArrayList<Topic> topics = new ArrayList<Topic>();

        switch(chapterTitle) {
            case "User Interface":
                topics.add(new Topic("User Interface", 100));
                topics.add(new Topic("User Input", 44));
                topics.add(new Topic("Multi-screen Apps", 2));
                topics.add(new Topic("Networking", 5));
                topics.add(new Topic("Data Storage", 1));
                break;
            default:
                topics.add(new Topic("erje5tjet", 60));
                topics.add(new Topic("jrjrtte", 44));
                topics.add(new Topic("jeqtte", 2));
                topics.add(new Topic("geqhhqhq", 5));
                topics.add(new Topic("Daeqheta eqhqeh", 1));
        }

        return topics;
    }
}
