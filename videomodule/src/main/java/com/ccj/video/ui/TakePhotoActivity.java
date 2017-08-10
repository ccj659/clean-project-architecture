package com.ccj.video.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.base.Constants;
import com.ccj.base.utils.TLog;
import com.ccj.base.utils.router.RouterConstants;
import com.ccj.video.R;
import com.ccj.video.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/8.
 */
@Route(path= RouterConstants.VIDEO_MUDULE_ACTIVITY)
public class TakePhotoActivity extends BaseActivity<TakePhotoContract.Presenter> implements TakePhotoContract.View {
    @BindView(R2.id.imageView)
    ImageView imageView;
    @BindView(R2.id.button)
    Button button;
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int MEDIA_TYPE_IMAGE = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        ButterKnife.bind(this);
        mPresenter = new TakePhotoPresenter(this);
        mPresenter.start();
    }

    @Override
    public void initView() {

    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }
//WindowLeaked
    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showBitmap(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @OnClick(R2.id.button)
    public void onClick() {
        startTakePhoto();


    }

    private void startTakePhoto() {
        Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(picture, Constants.IMAGES_ACTIVITY_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.IMAGES_ACTIVITY_REQUEST_CODE == requestCode) {
            if (resultCode == RESULT_OK) {
                TLog.log(data.getData().toString());
                Log.e("Tlog","data-->"+data.getData().toString());
                mPresenter.savePhoto(data);
            }

        }



    }
}
