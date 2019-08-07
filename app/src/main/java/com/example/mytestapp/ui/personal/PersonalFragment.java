package com.example.mytestapp.ui.personal;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mytestapp.Base.fragment.BaseMvpFragment;
import com.example.mytestapp.R;
import com.example.mytestapp.manager.user.UserManager;
import com.example.mytestapp.upload.UploadHelper;
import com.example.mytestapp.utils.GLideUtil;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.api.widget.Widget;

import java.util.ArrayList;

public class PersonalFragment extends BaseMvpFragment implements View.OnClickListener {

    private ImageView miv;
    private TextView denglu;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initBar() {
        super.initBar();
        titleLayoutI.setTitle("个人");
    }

    @Override
    protected void initView() {

        miv = (ImageView) findViewById(R.id.iv);


        String urlImage = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561805219659&di=21aafa302d919a21be27a7acfde39a18&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F61%2F05%2F01300542392970153122058010203_s.jpg";

        //头像加载
        GLideUtil.loadImageViewRound(getContext(), urlImage, miv);

        miv.setOnClickListener(this);


        findViewById(R.id.geren_xiaoxi).setOnClickListener(this);
        findViewById(R.id.geren_shoucang).setOnClickListener(this);
        findViewById(R.id.geren_shezhi).setOnClickListener(this);
        denglu = (TextView) findViewById(R.id.mTv_denglu);

        if (UserManager.getInstance().isLogin()) {
            denglu.setText("已登陆：" + UserManager.getInstance().getPhoneNumber());
        }

    }


    private void openAlbum(){
        Album.image(getContext())
                .singleChoice()
                .camera(true)
                .columnCount(2)
                .widget(Widget.newDarkBuilder(getContext())
                        .title("图片选择")
                        .build())
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        if (isValidData(result)) {
                            UploadHelper uploadHelper = new UploadHelper();


                            String url= uploadHelper.uploadImage(result.get(0).getPath());
                            GLideUtil.loadImageViewRound(getContext(), url, miv);
                        }
                    }
                }).start();
    }




    @Override
    protected void loadData(int page) {

    }


    private boolean isValidData(ArrayList<AlbumFile> result) {
        if (null == result || result.isEmpty()) return false;
        AlbumFile albumFile = result.get(0);
        if (TextUtils.isEmpty(albumFile.getPath())) return false;
        return true;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_personal_layout;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.iv){
            openAlbum();
        }

        v = (View) v.getParent().getParent();
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
