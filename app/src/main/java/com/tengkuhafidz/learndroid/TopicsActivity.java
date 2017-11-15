package com.tengkuhafidz.learndroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class TopicsActivity extends AppCompatActivity {
    MyDB db;
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
        db = new MyDB(this);
        // get chapter title
        Intent intent = getIntent();
        mChapterTitle = intent.getStringExtra("chapterTitle");

        // set action bar title
        getSupportActionBar().setTitle(mChapterTitle);

        final ArrayList<Topic> topics = getAssociatedTopics(mChapterTitle);

        // specify an adapter
        mTopicAdapter = new TopicAdapter(topics, this);
        mRecyclerView.setAdapter(mTopicAdapter);
    }

    private ArrayList<Topic> getAssociatedTopics(String chapterTitle) {

        db.open();
        ArrayList<Topic> topics = new ArrayList<Topic>();

        if (chapterTitle.equals(getString(R.string.chapter1_title))) {
            topics.add(new Topic(getString(R.string.chapter1_topic1_title),
                    db.getTopicProgress(getString(R.string.chapter1_topic1_title))));
            topics.add(new Topic(getString(R.string.chapter1_topic2_title),
                    db.getTopicProgress(getString(R.string.chapter1_topic2_title))));
            topics.add(new Topic(getString(R.string.chapter1_topic3_title),
                    db.getTopicProgress(getString(R.string.chapter1_topic3_title))));
            topics.add(new Topic(getString(R.string.chapter1_topic4_title),
                    db.getTopicProgress(getString(R.string.chapter1_topic4_title))));
            topics.add(new Topic(getString(R.string.chapter1_topic5_title),
                    db.getTopicProgress(getString(R.string.chapter1_topic5_title))));
        }
        else if (chapterTitle.equals(getString(R.string.chapter2_title))) {
            topics.add(new Topic(getString(R.string.chapter2_topic1_title),
                    db.getTopicProgress(getString(R.string.chapter2_topic1_title))));
            topics.add(new Topic(getString(R.string.chapter2_topic2_title),
                    db.getTopicProgress(getString(R.string.chapter2_topic2_title))));
            topics.add(new Topic(getString(R.string.chapter2_topic3_title),
                    db.getTopicProgress(getString(R.string.chapter2_topic3_title))));
        }
        else if (chapterTitle.equals(getString(R.string.chapter3_title))) {
            topics.add(new Topic(getString(R.string.chapter3_topic1_title),
                    db.getTopicProgress(getString(R.string.chapter3_topic1_title))));
            topics.add(new Topic(getString(R.string.chapter3_topic2_title),
                    db.getTopicProgress(getString(R.string.chapter3_topic2_title))));
            topics.add(new Topic(getString(R.string.chapter3_topic3_title),
                    db.getTopicProgress(getString(R.string.chapter3_topic3_title))));
        }

        db.close();
        return topics;
    }


}
