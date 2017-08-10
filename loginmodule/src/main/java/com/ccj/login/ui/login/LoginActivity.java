package com.ccj.login.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.base.BaseApplication;
import com.ccj.base.utils.router.RouterConstants;
import com.ccj.login.R;
import com.ccj.login.R2;
import com.ccj.login.debug.MainActivity;
import com.ccj.login.ui.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/7.
 */

@Route(path = RouterConstants.LOGIN_MOUDLE_ACTIVITY)
public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View {

    @BindView(R2.id.iv_cancel)
    ImageView ivCancel;
    @BindView(R2.id.tv_phone)
    EditText tvPhone;
    @BindView(R2.id.tv_password)
    EditText tvPassword;
    @BindView(R2.id.cb_remember_pass)
    CheckBox cbRememberPass;
    @BindView(R2.id.tv_forget_pass)
    TextView tvForgetPass;
    @BindView(R2.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R2.id.linearLayout2)
    LinearLayout linearLayout2;
    @BindView(R2.id.btn_login)
    Button btnLogin;
    @BindView(R2.id.btn_register)
    Button btnRegister;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter=new LoginPresenter(this);
        mPresenter.start();
    }


    @Override
    public void navigateToMain() {
        Intent intent =new Intent(getBaseContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void navigateToRegister() {
        Intent intent =new Intent(getBaseContext(),RegisterActivity.class);
        startActivity(intent);
    }

    @OnClick({R2.id.iv_cancel, R2.id.btn_login, R2.id.btn_register})
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.iv_cancel) {
            finish();
        } else if (i == R.id.btn_login) {
            //mPresenter.login(tvPhone.getText().toString(), tvPassword.getText().toString());
            Toast.makeText(this,"登录测试",Toast.LENGTH_SHORT).show();
        } else if (i == R.id.btn_register) {
            navigateToRegister();
        }
    }



    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(String error) {
        BaseApplication.showShortToast(error);

    }

    @Override
    public void initView() {


    }
}
