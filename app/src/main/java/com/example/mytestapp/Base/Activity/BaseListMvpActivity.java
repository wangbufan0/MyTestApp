package com.example.mytestapp.Base.Activity;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mytestapp.Base.decoration.BaseItemDecoration;
import com.example.mytestapp.R;

import me.drakeet.multitype.MultiTypeAdapter;


public abstract class BaseListMvpActivity extends BaseMvpActivity {
    protected RecyclerView mRecyclerView;
    protected MultiTypeAdapter mAdapter;

    @Override
    protected void initView() {
        this.mRecyclerView = findViewById(R.id.recycler_view);
        this.mRecyclerView.addItemDecoration(new BaseItemDecoration());
        this.mAdapter = new MultiTypeAdapter();
        registerMultiType();
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    protected abstract void registerMultiType();

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_base_list_layout;
    }
}
