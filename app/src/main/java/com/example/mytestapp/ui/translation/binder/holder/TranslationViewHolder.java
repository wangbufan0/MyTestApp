package com.example.mytestapp.ui.translation.binder.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.mytestapp.R;
import com.example.mytestapp.ui.translation.TranslationActivity;
import com.example.mytestapp.ui.translation.domain.HistoryData;

/**
 * @Name: TranslationViewHolder
 * @Author: wangbufan
 * @Date: 2019/8/26 20:03
 * @Description:
 */
public class TranslationViewHolder extends RecyclerView.ViewHolder {

    public static TranslationViewHolder getInstance(ViewGroup parenter){
        View view= LayoutInflater.from(parenter.getContext()).inflate(R.layout.item_translation_layout,parenter,false);
        return new TranslationViewHolder(view);
    }

    private TextView TvFrom,TvTo;

    public TranslationViewHolder(@NonNull final View itemView) {
        super(itemView);
        TvFrom=itemView.findViewById(R.id.tv_from);
        TvTo=itemView.findViewById(R.id.tv_to);

    }

    public void postData2UI(final HistoryData data){
        TvFrom.setText(data.getFrom());
        TvTo.setText(data.getTo());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslationActivity activity= (TranslationActivity) ActivityUtils.getActivityByView(itemView);
                activity.change2NotNULLUI(data);
            }
        });
    }
}
