package com.tengkuhafidz.learndroid;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by tengkuhafidz on 8/11/17.
 */

public class ScreenAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public ScreenAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ChaptersFragment();
        } else {
            return new QuizFragment();
        }
    }

    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.screen_lessons);
        } else {
            return mContext.getString(R.string.screen_quizes);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
