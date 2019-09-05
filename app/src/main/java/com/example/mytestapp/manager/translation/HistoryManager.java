package com.example.mytestapp.manager.translation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mytestapp.manager.translation.sqliteHelper.TranslationDataHelper;
import com.example.mytestapp.ui.translation.domain.HistoryData;

import java.util.ArrayList;
import java.util.List;


/**
 * The type History manager.
 *
 * @Name: HistoryManager
 * @Author: wangbufan
 * @Date: 2019 /8/26 16:41
 * @Description: 翻译历史保存
 */
public class HistoryManager {

    private TranslationDataHelper helper;
    private SQLiteDatabase db;

    /**
     * Instantiates a new History manager.
     *
     * @param context the context
     */
    public HistoryManager(Context context) {
        helper = new TranslationDataHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
      *
      * @Author:         wangbufan
      * @CreateDate:     2019/9/4 17:05
      * @Description:    添加单个数据
     */
    public void add(HistoryData data) {
        delete(data);
        ContentValues contentValues = new ContentValues();
        contentValues.put("_from", data.getFrom());
        contentValues.put("_to", data.getTo());
        db.insert("history", null, contentValues);
        //db.execSQL("INSERT INTO history VALUES(null,?,?)",new Object[]{data.getFrom(),data.getTo()});
    }


    /**
      *
      * @Author:         wangbufan
      * @CreateDate:     2019/9/4 17:04
      * @Description:    添加一组数据
     */
    public void addall(List<HistoryData> datas) {
        db.beginTransaction();
        try {
            for (HistoryData data : datas) {
                db.execSQL("INSERT INTO history VALUES(null,?,?)", new Object[]{data.getFrom(), data.getTo()});
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    /**
      *
      * @Author:         wangbufan
      * @CreateDate:     2019/9/4 17:05
      * @Description:    获得所有数据
     */
    public List<HistoryData> query() {
        List<HistoryData> datas = new ArrayList<>();

        Cursor c =  db.rawQuery("SELECT * FROM history", null);
        while (c.moveToNext()) {
            HistoryData data = new HistoryData(c.getInt(c.getColumnIndex("_id"))
                    , c.getString(c.getColumnIndex("_from"))
                    , c.getString(c.getColumnIndex("_to")));
            datas.add(0, data);
        }
        c.close();
        return datas;
    }

    /**
      *
      * @Author:         wangbufan
      * @CreateDate:     2019/9/4 17:06
      * @Description:      删除一个数据
     */
    public void delete(HistoryData data) {
        db.delete("history", "_from = ? and _to = ?", new String[]{data.getFrom(), data.getTo()});
    }

    /**
      *
      * @Author:         wangbufan
      * @CreateDate:     2019/9/4 17:07
      * @Description:       清空数据库
     */
    public void deleteAll() {
        db.execSQL("delete from history");
    }

    /**
      *
      * @Author:         wangbufan
      * @CreateDate:     2019/9/4 17:08
      * @Description:  资源释放
     */
    public void closeDB() {
        db.close();
    }


}
