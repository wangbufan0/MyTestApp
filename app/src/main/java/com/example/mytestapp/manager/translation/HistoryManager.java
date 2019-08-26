package com.example.mytestapp.manager.translation;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mytestapp.manager.translation.sqliteHelper.TranslationDataHelper;
import com.example.mytestapp.ui.translation.domain.HistoryData;

import java.util.ArrayList;
import java.util.List;

/**
 * @Name: HistoryManager
 * @Author: wangbufan
 * @Date: 2019/8/26 16:41
 * @Description: 翻译历史保存
 */
public class HistoryManager {

    private TranslationDataHelper helper;
    private SQLiteDatabase db;

    public HistoryManager(Context context){
        helper=new TranslationDataHelper(context);
        db=helper.getWritableDatabase();
    }

    /**
      *
      * @Name:           add
      * @Author:         wangbufan
      * @CreateDate:     2019/8/26 16:51
      * @Description:  添加数据
     */

    public void add(HistoryData data){
        delete(data);
        db.execSQL("INSERT INTO history VALUES(null,?,?)",new Object[]{data.getFrom(),data.getTo()});
    }

    public void addall(List<HistoryData> datas){
        db.beginTransaction();
        try{
            for(HistoryData data:datas){
                db.execSQL("INSERT INTO history VALUES(null,?,?)",new Object[]{data.getFrom(),data.getTo()});
            }
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    public List<HistoryData> query(){

        List<HistoryData> datas=new ArrayList<>();

        Cursor c=queryTheCursor();
        while (c.moveToNext()){
            HistoryData data= new HistoryData(c.getInt(c.getColumnIndex("_id"))
                                                ,c.getString(c.getColumnIndex("_from"))
                                                ,c.getString(c.getColumnIndex("_to")));
            datas.add(0,data);
        }
        return datas;
    }

    public void delete(HistoryData data){
        db.delete("history","_from = ? and _to = ?",new String[]{data.getFrom(),data.getTo()});
//        db.execSQL("DELETE FROM histroy WHERE _from = '"+data.getFrom()+"' AND" +
//                " _to = '"+data.getTo()+"';");
    }

    public void deleteAll(){
        db.execSQL("delete from history");
    }


    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM history", null);
        return c;
    }


    public void closeDB(){
        db.close();
    }


}
