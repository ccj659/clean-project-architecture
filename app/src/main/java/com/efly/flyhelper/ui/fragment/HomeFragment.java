package com.efly.flyhelper.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ccj.base.Constants;
import com.ccj.base.base.BaseFragment;
import com.ccj.base.utils.router.RouterConstants;
import com.efly.flyhelper.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/7.
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.tv_show)
    TextView tvShow;
    private View view;
    private static final String TAG = HomeFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_message, null);
        }
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    @OnClick({R.id.button2, R.id.button3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                navigateToLogin();
                break;
            case R.id.button3:
                navigateTakePhoto();
                break;
        }
    }


    private void navigateToLogin() {
        ARouter.getInstance().build(RouterConstants.LOGIN_MOUDLE_ACTIVITY).
                withString(Constants.START_LOGIN_WITH_PARAMS, "I am params from MainActivity").
                navigation(getActivity(), Constants.REQUEST_START_LOGIN);
    }

    private void navigateTakePhoto() {
        ARouter.getInstance().
                build(RouterConstants.VIDEO_MUDULE_ACTIVITY).
                withString(Constants.START_LOGIN_WITH_PARAMS, "I am params from MainActivity").
                navigation();

    }


    @Override
    public void initView() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_START_LOGIN) {
            if (data == null) {
                return;
            }
            String str = data.getStringExtra(Constants.PARAMS_RESULT_FROM_LOGIN);
            if (str == null) {
                return;
            }

            tvShow.setText(str);
        }
    }

}
