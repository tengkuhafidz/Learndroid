package com.tengkuhafidz.learndroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class SectionsActivity extends AppCompatActivity {
    String mTopicTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // get chapter title
        Intent intent = getIntent();
        mTopicTitle = intent.getStringExtra("topicTitle");


        // set action bar title
        getSupportActionBar().setTitle(mTopicTitle);

        final ArrayList<Section> sections = getAssociatedSections(mTopicTitle);

        // Create an adapter that knows which fragment should be shown on each page
        SectionAdapter sectionAdapter = new SectionAdapter(sections, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(sectionAdapter);
    }


    private ArrayList<Section> getAssociatedSections(String topicTitle) {
        ArrayList<Section> sections = new ArrayList<Section>();
        sections.add(new Section("one", "wahid.com", "42yio24ht oi24htip24 j24 t" ));
        sections.add(new Section("two", "ithnani.com", " l;hiowrgohrw oghrw;g  hrhqh"));
        sections.add(new Section("three", "thalatha.com", " lweughw o;ihgw rgnrw"));
        sections.add(new Section("four", "arba'a.com", " weoughw orgh ;orwhjgjgrw h"));
        return sections;
    }
}
