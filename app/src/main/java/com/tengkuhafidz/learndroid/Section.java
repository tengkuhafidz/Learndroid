package com.tengkuhafidz.learndroid;

/**
 * Created by tengkuhafidz on 11/11/17.
 */

public class Section {
    private String mTitle;
    private String mVideoId;
    private String mLessonText;

    Section(String title, String videoId, String lessonText) {
        mTitle = title;
        mVideoId = videoId;
        mLessonText = lessonText;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getVideoId() {
        return mVideoId;
    }

    public String getLessonText() { return mLessonText; }
}
