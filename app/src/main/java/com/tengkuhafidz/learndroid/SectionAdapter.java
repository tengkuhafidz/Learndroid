package com.tengkuhafidz.learndroid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by tengkuhafidz on 11/11/17.
 */

public class SectionAdapter extends FragmentPagerAdapter {

    private ArrayList<Section> mSections;

    public SectionAdapter(ArrayList<Section> sections, FragmentManager fm) {
        super(fm);
        mSections = sections;
    }

    @Override
    public Fragment getItem(int position) {
        Section section = mSections.get(position);
        int pageNum = position + 1;
        int totalPages = mSections.size();
        return SectionFragment.newInstance(section, pageNum, totalPages);
    }

    @Override
    public int getCount() {
        return mSections.size();
    }
}
