package com.example.mytestapp.load.image;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.blankj.utilcode.util.ToastUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author : wangbufan
 * @Time : 14:15 2019-08-14
 * @description :
 * --------------------------------------
 */
public class LoadImage {


    private static ExecutorService singleExecutor = null;


    public static void DownloadImage(final Context context, String url) {

        GetImagePathService getImagePathService = new GetImagePathService(context, url,
                new GetImagePathCallBack() {
                    @Override
                    public void Successed(File file) {
//                        String newPath= Environment.getExternalStorageDirectory().getAbsolutePath()+'/'+ HashUtil.getMD5String(file)+ ".jpg";
//                        copyFile(file,newPath);
//                        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(newPath))));
//                        ToastUtils.showShort("图片下载到："+newPath);
                    }

                    @Override
                    public void BitMapSuccessed(Bitmap bitmap) {
                        String storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "mytestapp";
                        File appDir = new File(storePath);
                        if (!appDir.exists()) {
                            appDir.mkdirs();
                        }
                        String fileName = System.currentTimeMillis() + ".jpg";
                        File file = new File(appDir, fileName);
                        try {
                            FileOutputStream fos = new FileOutputStream(file);
                            //通过io流的方式来压缩保存图片
                            boolean isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fos);
                            fos.flush();
                            fos.close();

                            //把文件插入到系统图库
                            //MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);

                            //保存图片后发送广播通知更新数据库
                            Uri uri = Uri.fromFile(file);
                            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
                            if (isSuccess) {
                                ToastUtils.showShort("图片下载到："+storePath+fileName);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void Failed() {

                    }
                });

        if (singleExecutor == null) {
            singleExecutor = Executors.newSingleThreadExecutor();
        }
        singleExecutor.submit(getImagePathService);


    }


    public static void copyFile(File oldfile, String newPath) {
        try {
            int bytesum = 0;
            int byteread ;
            String oldPath=oldfile.getPath();
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }


    }
}
