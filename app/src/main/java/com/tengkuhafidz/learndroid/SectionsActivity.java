package com.tengkuhafidz.learndroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class SectionsActivity extends AppCompatActivity {
    MyDB db;
    String mTopicTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);
        db = new MyDB(this);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // get chapter title
        Intent intent = getIntent();
        mTopicTitle = intent.getStringExtra("topicTitle");


        // set action bar title
        getSupportActionBar().setTitle(mTopicTitle);

        final ArrayList<Section> sections = getAssociatedSections(mTopicTitle);

        // Create an adapter that knows which fragment should be shown on each page
        SectionAdapter sectionAdapter = new SectionAdapter(this, sections, mTopicTitle, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(sectionAdapter);
    }


    private ArrayList<Section> getAssociatedSections(String topicTitle) {

        db.open();
        ArrayList<Section> sections = new ArrayList<Section>();

        if (topicTitle.equals(getString(R.string.chapter1_topic1_title))) {
            sections.add(new Section("Views", "ctvsMCVlENI",
                    "Camel case is a convention that is not limited to programming." +
                            " If you've ever used FedEx, listened to an iPod," +
                            " created a PowerPoint, or eaten at McDonalds," +
                            " you've encountered camel case!\n\n" +
                            "We used a lot of new words in this video!\n" +
                            "Layout, User Interface, TextView, ImageView, Button, Camel Case" ));
            sections.add(new Section("Using a TextView", "qoyjqLcTbPM", ""));
            sections.add(new Section("XML Syntax", "s2VszKiPD0E",
                    "Here are the words we introduced in this video:\n\n" +
                            "XML element, Tag, Self-closing tags, Attributes, Syntax"));
            sections.add(new Section("Change the TextView", "uuB1HApT9nk",
                    "Words we used in this video:\n\n" +
                            "Android Studio, Device, Desity-Independent Pixels"));
        }
        else if (topicTitle.equals(getString(R.string.chapter1_topic2_title))) {
            sections.add(new Section("one", "63KYESWr4ts", "42yio24ht oi24htip24 j24 t" ));
            sections.add(new Section("two", "HJg7_dxkICQ", " l;hiowrgohrw oghrw;g  hrhqh"));
            sections.add(new Section("three", "lDXFmtb92i4", " lweughw o;ihgw rgnrw"));
            sections.add(new Section("four", "dNbHlbCX7tc", " weoughw orgh ;orwhjgjgrw h"));
        }
        else if (topicTitle.equals(getString(R.string.chapter1_topic3_title))) {
            sections.add(new Section("one", "63KYESWr4ts", "42yio24ht oi24htip24 j24 t" ));
            sections.add(new Section("two", "HJg7_dxkICQ", " l;hiowrgohrw oghrw;g  hrhqh"));
            sections.add(new Section("three", "lDXFmtb92i4", " lweughw o;ihgw rgnrw"));
            sections.add(new Section("four", "dNbHlbCX7tc", " weoughw orgh ;orwhjgjgrw h"));
        }
        else if (topicTitle.equals(getString(R.string.chapter1_topic4_title))) {
            sections.add(new Section("one", "63KYESWr4ts", "42yio24ht oi24htip24 j24 t" ));
            sections.add(new Section("two", "HJg7_dxkICQ", " l;hiowrgohrw oghrw;g  hrhqh"));
            sections.add(new Section("three", "lDXFmtb92i4", " lweughw o;ihgw rgnrw"));
            sections.add(new Section("four", "dNbHlbCX7tc", " weoughw orgh ;orwhjgjgrw h"));
        }
        else if (topicTitle.equals(getString(R.string.chapter1_topic5_title))) {
            sections.add(new Section("one", "63KYESWr4ts", "42yio24ht oi24htip24 j24 t" ));
            sections.add(new Section("two", "HJg7_dxkICQ", " l;hiowrgohrw oghrw;g  hrhqh"));
            sections.add(new Section("three", "lDXFmtb92i4", " lweughw o;ihgw rgnrw"));
            sections.add(new Section("four", "dNbHlbCX7tc", " weoughw orgh ;orwhjgjgrw h"));
        }
        else if (topicTitle.equals(getString(R.string.chapter2_topic1_title))) {
            sections.add(new Section("one", "63KYESWr4ts", "42yio24ht oi24htip24 j24 t" ));
            sections.add(new Section("two", "HJg7_dxkICQ", " l;hiowrgohrw oghrw;g  hrhqh"));
            sections.add(new Section("three", "lDXFmtb92i4", " lweughw o;ihgw rgnrw"));
            sections.add(new Section("four", "dNbHlbCX7tc", " weoughw orgh ;orwhjgjgrw h"));
        }
        else if (topicTitle.equals(getString(R.string.chapter2_topic2_title))) {
            sections.add(new Section("one", "63KYESWr4ts", "42yio24ht oi24htip24 j24 t" ));
            sections.add(new Section("two", "HJg7_dxkICQ", " l;hiowrgohrw oghrw;g  hrhqh"));
            sections.add(new Section("three", "lDXFmtb92i4", " lweughw o;ihgw rgnrw"));
            sections.add(new Section("four", "dNbHlbCX7tc", " weoughw orgh ;orwhjgjgrw h"));
        }
        else if (topicTitle.equals(getString(R.string.chapter2_topic3_title))) {
            sections.add(new Section("one", "63KYESWr4ts", "42yio24ht oi24htip24 j24 t" ));
            sections.add(new Section("two", "HJg7_dxkICQ", " l;hiowrgohrw oghrw;g  hrhqh"));
            sections.add(new Section("three", "lDXFmtb92i4", " lweughw o;ihgw rgnrw"));
            sections.add(new Section("four", "dNbHlbCX7tc", " weoughw orgh ;orwhjgjgrw h"));
        }
        else if (topicTitle.equals(getString(R.string.chapter3_topic1_title))) {
            sections.add(new Section("one", "63KYESWr4ts", "42yio24ht oi24htip24 j24 t" ));
            sections.add(new Section("two", "HJg7_dxkICQ", " l;hiowrgohrw oghrw;g  hrhqh"));
            sections.add(new Section("three", "lDXFmtb92i4", " lweughw o;ihgw rgnrw"));
            sections.add(new Section("four", "dNbHlbCX7tc", " weoughw orgh ;orwhjgjgrw h"));
        }
        else if (topicTitle.equals(getString(R.string.chapter3_topic2_title))) {
            sections.add(new Section("one", "63KYESWr4ts", "42yio24ht oi24htip24 j24 t" ));
            sections.add(new Section("two", "HJg7_dxkICQ", " l;hiowrgohrw oghrw;g  hrhqh"));
            sections.add(new Section("three", "lDXFmtb92i4", " lweughw o;ihgw rgnrw"));
            sections.add(new Section("four", "dNbHlbCX7tc", " weoughw orgh ;orwhjgjgrw h"));
        }
        else if (topicTitle.equals(getString(R.string.chapter3_topic3_title))) {
            sections.add(new Section("one", "63KYESWr4ts", "42yio24ht oi24htip24 j24 t" ));
            sections.add(new Section("two", "HJg7_dxkICQ", " l;hiowrgohrw oghrw;g  hrhqh"));
            sections.add(new Section("three", "lDXFmtb92i4", " lweughw o;ihgw rgnrw"));
            sections.add(new Section("four", "dNbHlbCX7tc", " weoughw orgh ;orwhjgjgrw h"));
        }

        db.close();
        return sections;
    }
}
