package com.example.mytestapp.manager.translation.sqliteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @Name: TranslationDataHelper
 * @Author: wangbufan
 * @Date: 2019/8/26 16:29
 * @Description:
 */
public class TranslationDataHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "translation.db"; //数据库名称
    private static final int version = 1; //数据库版本

    public TranslationDataHelper(@Nullable Context context) {
        super(context, DB_NAME, null, version);
    }

    /**
     * 在创建数据库时调用
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String tab_history="create table IF NOT EXISTS history(_id integer primary key autoincrement,_from text,_to text)";
        //执行SQL语句
        db.execSQL(tab_history);
    }
    /**
     * 在升级数据库时调用
     * @param db
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String upData="ALTER TABLE history ADD COLUMN other text";
        db.execSQL(upData);
    }
}
