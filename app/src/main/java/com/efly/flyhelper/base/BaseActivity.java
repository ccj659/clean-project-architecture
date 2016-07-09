package com.efly.flyhelper.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.efly.flyhelper.AppManager;
import com.efly.flyhelper.R;

/**
 * base 来进行 toolbar dialog 初始化,activity栈的添加,删除等
 * Created by ccj on 2016/7/5.
 */
public class BaseActivity extends AppCompatActivity implements BaseView {

    protected ProgressDialog progressDialog;
    protected  Toolbar toolbar;
    protected BasePresenter mPresenter;
    protected Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        mContext=this;
        initDialog();
    }

    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.tool_bar_white));
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    private void initDialog() {
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.show_loading_msg));
    }

    public void showLoading() {
        progressDialog.show();
    }
    public void dismissLoading() {
        progressDialog.dismiss();
    }
}
