package com.example.mytestapp.ui.translation.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.mytestapp.ui.translation.binder.holder.TranslationViewHolder;
import com.example.mytestapp.ui.translation.domain.HistoryData;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @Name: TranslationBinder
 * @Author: wangbufan
 * @Date: 2019/8/26 20:02
 * @Description:
 */
public class TranslationBinder extends ItemViewBinder<HistoryData, TranslationViewHolder> {

    @NonNull
    @Override
    protected TranslationViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return TranslationViewHolder.getInstance(parent);
    }

    @Override
    protected void onBindViewHolder(@NonNull TranslationViewHolder translationViewHolder, @NonNull HistoryData data) {
        translationViewHolder.postData2UI(data);
    }
}
