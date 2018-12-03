package com.example.zk01.SQ;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQHelper extends SQLiteOpenHelper {
    public SQHelper(Context context) {
        super(context,"sjk",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table if not exists user(id integer primary key autoincrement,context text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
