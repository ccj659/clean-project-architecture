package com.ccj.base.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;


/**
 * Created by Administrator on 2016/7/5.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {
    private static final String TAG="BaseFragment";
    private LayoutInflater mInflater;
    private Dialog dialog;
    public T mPresenter;
    protected float mDensity;
    protected int mDensityDpi;
    protected int mWidth;
    protected int mAvatarSize;
    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getScreen();
    }


    public void showLoading() {
        if (dialog == null) {
            dialog = new ProgressDialog(getActivity());
        }
        dialog.setTitle("正在加载...");
        dialog.show();
    }

    public void dismissLoading() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    private void getScreen() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        mDensity = dm.density;
        mDensityDpi = dm.densityDpi;
        mWidth = dm.widthPixels;
        mAvatarSize = (int) (50 * mDensity);
    }

    /**
     * 解绑的时候 清除
     */
    @Override
    public void onDetach() {
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }

        super.onDetach();
    }
}
