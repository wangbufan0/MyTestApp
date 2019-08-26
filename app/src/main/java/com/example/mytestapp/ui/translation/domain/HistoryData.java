package com.example.mytestapp.ui.translation.domain;

import java.util.Objects;

/**
 * @Name: HistoryData
 * @Author: wangbufan
 * @Date: 2019/8/26 16:42
 * @Description:
 */
public class HistoryData {
    private int _id;
    private String from;
    private String to;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryData data = (HistoryData) o;
        return Objects.equals(from, data.from) &&
                Objects.equals(to, data.to);
    }

    public HistoryData(){

    }
    public HistoryData(String from,String to){
        this.from=from;
        this.to=to;
    }
    public HistoryData(int id,String from,String to){
        _id=id;
        this.from=from;
        this.to=to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "HistoryData{" +
                "_id=" + _id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
