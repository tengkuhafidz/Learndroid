package com.tengkuhafidz.learndroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.StringDef;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by justi on 12/11/2017.
 */

public class MyDB {

    MyDBHelper DBHelper;
    SQLiteDatabase db;
    final Context context;

    public MyDB(Context ctx) {
        this.context = ctx;
        DBHelper = new MyDBHelper(this.context);
    }

    public MyDB open() {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void populateDatabase() {
        //Chapter 1
        insertDataIntoProgressTable(context.getString(R.string.chapter1_title), context.getString(R.string.chapter1_topic1_title), "0");
        insertDataIntoProgressTable(context.getString(R.string.chapter1_title), context.getString(R.string.chapter1_topic2_title), "0");
        insertDataIntoProgressTable(context.getString(R.string.chapter1_title), context.getString(R.string.chapter1_topic3_title), "0");
        insertDataIntoProgressTable(context.getString(R.string.chapter1_title), context.getString(R.string.chapter1_topic4_title), "0");
        insertDataIntoProgressTable(context.getString(R.string.chapter1_title), context.getString(R.string.chapter1_topic5_title), "0");
        //Chapter 2
        insertDataIntoProgressTable(context.getString(R.string.chapter2_title), context.getString(R.string.chapter2_topic1_title), "0");
        insertDataIntoProgressTable(context.getString(R.string.chapter2_title), context.getString(R.string.chapter2_topic2_title), "0");
        insertDataIntoProgressTable(context.getString(R.string.chapter2_title), context.getString(R.string.chapter2_topic3_title), "0");
        //Chapter 3
        insertDataIntoProgressTable(context.getString(R.string.chapter3_title), context.getString(R.string.chapter3_topic1_title), "0");
        insertDataIntoProgressTable(context.getString(R.string.chapter3_title), context.getString(R.string.chapter3_topic2_title), "0");
        insertDataIntoProgressTable(context.getString(R.string.chapter3_title), context.getString(R.string.chapter3_topic3_title), "0");

        //Inserting highscore of 0
        insertDataIntoHighscoreTable("0");
    }

    public void close() {
        DBHelper.close();
    }

    public void insertDataIntoProgressTable(String chapterName, String topicName, String isCompleted) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(MyDBHelper.progress_chapterName, chapterName);
        initialValues.put(MyDBHelper.progress_topicName, topicName);
        initialValues.put(MyDBHelper.progress_isCompleted, isCompleted);
        db.insert(MyDBHelper.progressTable, null, initialValues);
    }

    public void insertDataIntoHighscoreTable(String score) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(MyDBHelper.highscore_score, score);
        db.insert(MyDBHelper.highscoreTable, null, initialValues);
    }

    public void deleteAllRecords() {
        db.delete(MyDBHelper.progressTable, null, null);
        db.delete(MyDBHelper.highscoreTable, null, null);
    }

    public int getHighscore() {

        String highScore = "0";

        Cursor scoreCursor = db.query(MyDBHelper.highscoreTable,
                new String[]{
                        MyDBHelper.highscore_id,
                        MyDBHelper.highscore_score},
                null, null, null, null, null);

        if (scoreCursor != null) {
            scoreCursor.moveToFirst();
        }

        if (scoreCursor.moveToFirst()) {
            highScore = scoreCursor.getString(1);
        }

        return Integer.parseInt(highScore);
    }

    public void setHighscore(int score) {

        String highscore = Integer.toString(score);
        ContentValues initialValues = new ContentValues();
        initialValues.put(MyDBHelper.highscore_score, highscore);
        db.update(MyDBHelper.highscoreTable, initialValues,
                MyDBHelper.highscore_id+"="+1, null);
    }

    public int getChapterProgress(String chapterName) {

        float chapterProgress = 0;
        float numOfCompletedTopics = 0;
        ArrayList<String[]> topicProgress = new ArrayList<String[]>();

        Cursor progressCursor = db.query(MyDBHelper.progressTable,
                new String[]{
                        MyDBHelper.progress_id,
                        MyDBHelper.progress_chapterName,
                        MyDBHelper.progress_topicName,
                        MyDBHelper.progress_isCompleted},
                MyDBHelper.progress_chapterName + "='" + chapterName + "'",
                null, null, null, null, null);

        if (progressCursor != null) {
            progressCursor.moveToFirst();
        }

        if (progressCursor.moveToFirst()) {
            do {
                String[] record = {progressCursor.getString(0), progressCursor.getString(1),
                        progressCursor.getString(2), progressCursor.getString(3)};
                topicProgress.add(record);
            } while (progressCursor.moveToNext());
        }

        for (String progress[] : topicProgress) {
            if (progress[3].equals("1")) {
                numOfCompletedTopics++;
            }
        }

        chapterProgress = numOfCompletedTopics/topicProgress.size();

        return Math.round(chapterProgress*100);
    }

    public boolean getTopicProgress(String topicName) {
        boolean topicProgress = false;

        Cursor progressCursor = db.query(MyDBHelper.progressTable,
                new String[]{
                        MyDBHelper.progress_id,
                        MyDBHelper.progress_chapterName,
                        MyDBHelper.progress_topicName,
                        MyDBHelper.progress_isCompleted},
                MyDBHelper.progress_topicName + "='" + topicName + "'",
                null, null, null, null, null);

        if (progressCursor != null) {
            progressCursor.moveToFirst();
        }

        if (progressCursor.moveToFirst()) {
            if (progressCursor.getString(3).equals("1")) {
                topicProgress = true;
            }
        }

        return topicProgress;
    }

    public void setTopicProgress(String topicName) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(MyDBHelper.progress_isCompleted, "1");
        db.update(MyDBHelper.progressTable, initialValues,
                MyDBHelper.progress_topicName+"='"+topicName+"'", null);

    }

    public ArrayList<String[]> getAllRecords() {
        Cursor c =  db.query(
                MyDBHelper.progressTable,
                new String[]{
                        MyDBHelper.progress_chapterName,
                        MyDBHelper.progress_topicName,
                        MyDBHelper.progress_isCompleted},
                null, null, null, null, null);

        ArrayList<String[]> records = new ArrayList<String[]>();

        if (c.moveToFirst()) {
            do {
                String[] record = {c.getString(0), c.getString(1), c.getString(2)};
                records.add(record);
            } while (c.moveToNext());
        }

        return records;

    }
}
