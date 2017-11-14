package com.tengkuhafidz.learndroid;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by tengkuhafidz on 11/11/17.
 */

public class SectionAdapter extends FragmentPagerAdapter {
    MyDB db;
    private ArrayList<Section> mSections;
    private String mTopicTitle;
    private Context mContext;

    public SectionAdapter(Context context, ArrayList<Section> sections, String topicTitle, FragmentManager fm) {
        super(fm);
        mSections = sections;
        mContext = context;
        mTopicTitle = topicTitle;
    }

    @Override
    public Fragment getItem(int position) {

        db = new MyDB(mContext);
        db.open();
        Section section = mSections.get(position);
        int pageNum = position + 1;
        int totalPages = mSections.size();

        if (pageNum == totalPages) {
            db.setTopicProgress(mTopicTitle);
        }

        db.close();
        return SectionFragment.newInstance(section, pageNum, totalPages);
    }

    @Override
    public int getCount() {
        return mSections.size();
    }
}
