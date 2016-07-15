package com.efly.flyhelper.ui.takephoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.efly.flyhelper.R;
import com.efly.flyhelper.base.BaseActivity;
import com.efly.flyhelper.base.Constants;
import com.efly.flyhelper.utils.TLog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/8.
 */
public class TakePhotoActivity extends BaseActivity<TakePhotoContract.Presenter> implements TakePhotoContract.View {
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.button)
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

    @OnClick(R.id.button)
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
