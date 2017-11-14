package com.tengkuhafidz.learndroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by justi on 12/11/2017.
 */

public class MyDBHelper extends SQLiteOpenHelper {

    public static final int databaseVersion = 1;
    public static final String databaseName = "learndroidDB";

    public static final String progressTable = "progress";
    public static final String progress_id = "_id";
    public static final String progress_chapterName = "chapterName";
    public static final String progress_topicName = "topicName";
    public static final String progress_isCompleted = "isCompleted";

    public static final String highscoreTable = "highscore";
    public static final String highscore_id = "_id";
    public static final String highscore_score = "score";

    private static final String SQLite_CREATE_HIGHSCORE =
            "CREATE TABLE "+highscoreTable+"("+highscore_id+
                    " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    highscore_score + " TEXT NOT NULL);"
            ;

    private static final String SQLite_CREATE_PROGRESS =
            "CREATE TABLE "+progressTable+"("+progress_id+
                    " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    progress_chapterName + " TEXT NOT NULL," +
                    progress_topicName + " TEXT NOT NULL," +
                    progress_isCompleted + " TEXT NOT NULL);"
            ;

    private static final String SQLite_DELETE_PROGRESS =
            "DROP TABLE IF EXISTS "+progressTable;

    private static final String SQLite_DELETE_HIGHSCORE =
            "DROP TABLE IF EXISTS "+highscoreTable;


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQLite_CREATE_PROGRESS);
        db.execSQL(SQLite_CREATE_HIGHSCORE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQLite_DELETE_PROGRESS);
        db.execSQL(SQLite_DELETE_HIGHSCORE);
        onCreate(db);
    }

    public MyDBHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
    }
}
