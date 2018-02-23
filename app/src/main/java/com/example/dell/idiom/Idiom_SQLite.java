package com.example.dell.idiom;

import android.app.ActionBar;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by DELL on 2015/5/10.
 */
public class Idiom_SQLite extends SQLiteOpenHelper {
    private static String name = "Idiom";
    private static int version = 1;

    public Idiom_SQLite(Context context) {
        super(context, name, null, version);
    }

    /**
     * 当数据库被创建时，第一次被执行，完成对数据库表的创建
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table chengyu_base(id integer primary key autoincrement,source verchar(225))";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
