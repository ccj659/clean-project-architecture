package com.ccj.login.debug;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.Constants;
import com.ccj.base.utils.eventbus.EventUtils;
import com.ccj.login.R;
import com.ccj.login.R2;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = "/login/MainActivity")
public class MainActivity extends AppCompatActivity {

    @BindView(R2.id.tv_login_main)
    TextView tvLoginMain;
    @BindView(R2.id.btn_event)
    Button btnEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        String str = getIntent().getStringExtra(Constants.START_LOGIN_WITH_PARAMS);
        tvLoginMain.setText(str);

    }




    @OnClick(R2.id.btn_event)
    public void onViewClicked() {
        EventBus.getDefault().post(new EventUtils.StringEvent("hello, I am  from com.ccj.login.debug.MainActivity.btn_event EventBus"));
    }
}
