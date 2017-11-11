package com.tengkuhafidz.learndroid;

/**
 * Created by tengkuhafidz on 9/11/17.
 */

public class Chapter {

    private String mTitle;
    private int mPercentageCompleted;
    private int mImageResourceId;

    Chapter(String title, int percentageCompleted, int imageResourceId) {
        mTitle = title;
        mPercentageCompleted = percentageCompleted;
        mImageResourceId = imageResourceId;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public int getPercentageCompleted() {
        return mPercentageCompleted;
    }
}
