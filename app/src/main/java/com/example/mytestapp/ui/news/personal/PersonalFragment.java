package com.example.mytestapp.ui.news.personal;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mytestapp.Base.fragment.BaseMvpFragment;
import com.example.mytestapp.Base.widget.MyBottomSheetDialog;
import com.example.mytestapp.R;
import com.example.mytestapp.manager.user.UserManager;
import com.example.mytestapp.ui.news.community.BigImage.BigImageActivity;
import com.example.mytestapp.upload.UploadHelper;
import com.example.mytestapp.utils.GLideUtil;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumLoader;
import com.yanzhenjie.album.api.widget.Widget;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PersonalFragment extends BaseMvpFragment implements View.OnClickListener {

    private ImageView miv;
    private TextView denglu;
    private MyBottomSheetDialog bottomSheetDialog;
    String[] s={"查看大图","更换图片"};
    String urlImage;

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


       urlImage = "https://wangbufan0.oss-cn-beijing.aliyuncs.com/image/201908/ca102b133d72bb43f7e90875ff187fb2.jpg";

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
        initAlbum();

    }

    private void initAlbum() {
        Album.initialize(AlbumConfig.newBuilder(getContext())
                .setAlbumLoader(new AlbumLoader() {
                    @Override
                    public void load(ImageView imageView, AlbumFile albumFile) {
                        load(imageView, albumFile.getPath());
                    }

                    @Override
                    public void load(ImageView imageView, String url) {

                        GLideUtil.loadImageViewLoding(imageView.getContext(),url,imageView);
                    }
                })
                .setLocale(Locale.getDefault())
                .build()
        );
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
            if(bottomSheetDialog==null)
                bottomSheetDialog=new MyBottomSheetDialog(getContext(),s);
            List<View> views=bottomSheetDialog.getViews();
            views.get(0).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BigImageActivity.launch(getContext(),urlImage);
                    bottomSheetDialog.dismiss();
                }
            });
            views.get(1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openAlbum();
                    bottomSheetDialog.dismiss();
                }
            });
            bottomSheetDialog.show();
            //openAlbum();
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
