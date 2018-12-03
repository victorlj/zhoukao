package com.example.zk01.SQ;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class SQprovoider extends ContentProvider {

    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        SQHelper helper = new SQHelper(getContext());
        db = helper.getWritableDatabase();
        return false;
    }


    @Override
    public Cursor query( Uri uri,  String[] projection,  String selection,  String[] selectionArgs,  String sortOrder) {
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        return cursor;
    }

    @Override
    public String getType( Uri uri) {
        return null;
    }


    @Override
    public Uri insert( Uri uri,  ContentValues values) {
        db.insert("user",null,values);
        return null;
    }

    @Override
    public int delete( Uri uri,  String selection,  String[] selectionArgs) {
        db.delete("user",selection,selectionArgs);
        return 0;
    }

    @Override
    public int update( Uri uri,  ContentValues values,  String selection,  String[] selectionArgs) {
        return 0;
    }
}
