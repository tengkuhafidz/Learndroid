package com.tengkuhafidz.learndroid;

/**
 * Created by tengkuhafidz on 11/11/17.
 */

public class Topic {

    private String mTitle;
    private int mPercentageCompleted;

    Topic(String title, int percentageCompleted) {
        mTitle = title;
        mPercentageCompleted = percentageCompleted;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getPercentageCompleted() {
        return mPercentageCompleted;
    }
}
