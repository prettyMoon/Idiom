package com.example.dell.idiom.android_db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dell.idiom.Idiom_SQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2015/5/13.
 */
public class Databse_Operate {

    private Idiom_SQLite sqlite;
    private List<String> list;
    private SQLiteDatabase sqldb;
    private static final String Name = "Idiom";
    private Context context;
    private int count = 0;
    String[] chengyu = {"魑魅魍魉", "柳陌花丛", "万家灯火",
            "搔首踟躇", "餐葩饮露", "蜂媒蝶使", "鳏寡孤独",
            "不落窠臼", "水木清华", "鹿裘未完", "濡沫涸辙",
            "随遇而安", "尸鸠之平", "浪蝶游蜂", "鹊返鸾回",
            "凤凰于飞", "寒蝉僵鸟", "窗明几净", "鹤发童颜",
            "百喙难辩", "暴雨梨花", "南柯一梦", "疾首蹙眉",
            "醍醐灌顶", "不求甚解", "欲壑难填", "喟然长叹",
            "干将莫邪", "江山如画", "空谷幽兰", "白云苍狗",
            "蚍蜉撼树", "傲雪欺霜", "桑榆末景", "炫昼缟夜",
            "沽名吊誉", "鸿断鱼沉", "竭泽焚薮", "豕虎传讹"};


    //构造方法
    public Databse_Operate(Context context) {
        this.context = context;
        sqlite = new Idiom_SQLite(context);
        sqldb = sqlite.getWritableDatabase();
//        for (int i = 166; i < 206; i++) {
//            Delete(i);
//        }
        for (int i = 0; i < chengyu.length; i++) {
            Insert(chengyu[i]);
        }

    }

    public void createDatabase() {
        sqlite.getWritableDatabase();
    }

    public void closeDatabase() {
        sqlite.close();
    }

    public List<String> slectData() {
        list = new ArrayList<String>();
        sqldb = sqlite.getReadableDatabase();
        String sql = "select * from chengyu_base";
        Cursor cursor = sqldb.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            count++;
            String data = cursor.getString(1);
            list.add(data);
        }
        return list;
    }

    public void Insert(String str) {

        String sql = "insert into chengyu_base (source) values (" + "'" + str + "'" + ")";
        sqldb.execSQL(sql);
    }

    public void Delete(int id) {
        String sql = "delete from chengyu_base where id = " + id;
        sqldb.execSQL(sql);
    }
}
