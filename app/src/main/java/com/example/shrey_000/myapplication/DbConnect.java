package com.example.shrey_000.myapplication;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Path;

/**
 * Created by SHREY_000 on 3/12/2016.
 */
public class DbConnect extends SQLiteOpenHelper {

    SQLiteDatabase database;

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "DB.db";

    private static final String INT_TYPE = " INTEGER";

    private static final String SQL_CREATE_TASKINFO = "CREATE TABLE " + DbContract.TaskInfoEntry.TABLE_NAME + " (" +
            DbContract.TaskInfoEntry.COLUMN_NAME_TASK_ID + " INTEGER NOT NULL"  + "," +
            DbContract.TaskInfoEntry.COLUMN_NAME_TASK_NAME + " TEXT NOT NULL" + "," +
            DbContract.TaskInfoEntry.COLUMN_NAME_PROJECT_ID + " INTEGER NOT NULL"  + "," +
            DbContract.TaskInfoEntry.COLUMN_NAME_PROJECT_NAME + " TEXT NOT NULL" + "," +
            "PRIMARY KEY (" + DbContract.TaskInfoEntry.COLUMN_NAME_TASK_ID + "," + DbContract.TaskInfoEntry.COLUMN_NAME_PROJECT_ID + ")" + " );";

    private static final String SQL_CREATE_TASKDEPENDENCY = "CREATE TABLE " + DbContract.TaskDependencyEntry.TABLE_NAME + " (" +
            DbContract.TaskDependencyEntry.COLUMN_NAME_TASK_ID + " INTEGER NOT NULL"  + "," +
            DbContract.TaskDependencyEntry.COLUMN_NAME_ESTIMATED_DURATION + " INTEGER" + "," +
            DbContract.TaskDependencyEntry.COLUMN_NAME_DEPENDENT_TASK + " INTEGER"  + "," +
            DbContract.TaskDependencyEntry.COLUMN_NAME_PROJECT_ID + " INTEGER NOT NULL" +" );";

    private static final String SQL_CREATE_TASKHISTORY = "CREATE TABLE " + DbContract.TaskHistoryEntry.TABLE_NAME + " (" +
            DbContract.TaskHistoryEntry.COLUMN_NAME_TASK_ID + " INTEGER NOT NULL"  + "," +
            DbContract.TaskHistoryEntry.COLUMN_NAME_PAST_DURATION + " INTEGER NOT NULL" + "," +
            DbContract.TaskHistoryEntry.COLUMN_NAME_PROJECT_ID + " INTEGER NOT NULL" +" );";

    private static final String SQL_DELETE_TASKINFO =
            "DROP TABLE IF EXISTS " + DbContract.TaskInfoEntry.TABLE_NAME + ";";

    private static final String SQL_DELETE_TASKDEPENDENCY =
            "DROP TABLE IF EXISTS " + DbContract.TaskDependencyEntry.TABLE_NAME + ";";

    private static final String SQL_DELETE_TASKHISTORY =
            "DROP TABLE IF EXISTS " + DbContract.TaskHistoryEntry.TABLE_NAME + ";";

    public DbConnect(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = this.getWritableDatabase();
        onUpgrade(database,3,4);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TASKINFO);
        db.execSQL(SQL_CREATE_TASKDEPENDENCY);
        db.execSQL(SQL_CREATE_TASKHISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TASKINFO);
        db.execSQL(SQL_DELETE_TASKDEPENDENCY);
        db.execSQL(SQL_DELETE_TASKHISTORY);
        onCreate(db);
    }

    public Cursor getProjectTasks(){
        return null;
    }

}
