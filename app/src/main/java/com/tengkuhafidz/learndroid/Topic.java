package com.tengkuhafidz.learndroid;

/**
 * Created by tengkuhafidz on 11/11/17.
 */

public class Topic {

    private String mTitle;
    private boolean mIsCompleted;

    Topic(String title, boolean isCompleted) {
        mTitle = title;
        mIsCompleted = isCompleted;
    }

    public String getTitle() {
        return mTitle;
    }

    public boolean getIsCompleted() {
        return mIsCompleted;
    }
}
