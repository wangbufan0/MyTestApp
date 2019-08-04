package com.example.mytestapp.ui.personal;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.mytestapp.Base.fragment.BaseMvpFragment;
import com.example.mytestapp.R;

public class PersonalFragment extends BaseMvpFragment implements View.OnClickListener{

    private ImageView miv;
    private TextView denglu;
    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

        miv = (ImageView) findViewById(R.id.iv);
        //头像加载
        Glide.with(getContext()).load(R.drawable.girl)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(miv);
        findViewById(R.id.geren_xiaoxi).setOnClickListener(this);
        findViewById(R.id.geren_shoucang).setOnClickListener(this);
        findViewById(R.id.geren_shezhi).setOnClickListener(this);

    }

    @Override
    protected void loadData(int page) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_personal_layout;
    }

    @Override
    public void onClick(View v) {
        v=(View) v.getParent().getParent();
        switch (v.getId()) {
            case R.id.geren_xiaoxi:
                //intent = new Intent(context, Activity_zixun.class);
                showToast("消息");
                break;
            case R.id.geren_shoucang:
                showToast("收藏");
                break;
            case R.id.geren_shezhi:
                showToast("设置");
                break;
        }
    }
}
