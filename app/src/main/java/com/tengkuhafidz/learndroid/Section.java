package com.tengkuhafidz.learndroid;

/**
 * Created by tengkuhafidz on 11/11/17.
 */

public class Section {
    private String mTitle;
    private String mVideoUrl;
    private String mLessonText;

    Section(String title, String videoUrl, String lessonText) {
        mTitle = title;
        mVideoUrl = videoUrl;
        mLessonText = lessonText;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getVideoUrl() {
        return mVideoUrl;
    }

    public String getLessonText() { return mLessonText; }
}
