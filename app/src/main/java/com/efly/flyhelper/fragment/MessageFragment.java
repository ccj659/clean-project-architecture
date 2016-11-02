package com.efly.flyhelper.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.efly.flyhelper.R;
import com.efly.flyhelper.base.BaseFragment;
import com.efly.flyhelper.ui.hotfix.HelloHack;
import com.efly.flyhelper.ui.login.LoginActivity;
import com.efly.flyhelper.ui.takephoto.TakePhotoActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/7.
 */
public class MessageFragment extends BaseFragment {

    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.button3)
    Button button3;
    @Bind(R.id.textview)
    TextView textview;
    @Bind(R.id.button5)
    Button button5;
    private View view;
    private static final String TAG = MessageFragment.class.getSimpleName();

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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.button2, R.id.button3,R.id.button5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                navigateToLogin();
                break;
            case R.id.button3:
                navigateTakePhoto();
                break;
            case R.id.button5:
                hotFix();
                break;
        }
    }

    private void hotFix() {
        HelloHack hack = new HelloHack();
        Toast.makeText(getActivity(), hack.showHello(), Toast.LENGTH_SHORT).show();

    }

    private void navigateToLogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    private void navigateTakePhoto() {
        Intent intent = new Intent(getActivity(), TakePhotoActivity.class);
        startActivity(intent);
    }


    @Override
    public void initView() {

    }
}
