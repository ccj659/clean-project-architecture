package com.ccj.login.debug;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.login.R;
import com.ccj.login.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = "/login/MainActivity")
public class MainActivity extends AppCompatActivity {

    @BindView(R2.id.tv_login_main)
    TextView tvLoginMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


}
