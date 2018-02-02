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
import com.ccj.base.Constants;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.base.BaseApplication;
import com.ccj.base.RouterConstants;
import com.ccj.login.R;
import com.ccj.login.R2;
import com.ccj.login.debug.MainActivity;
import com.ccj.login.ui.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ccj.base.Constants.PARAMS_RESULT_FROM_LOGIN;
import static com.ccj.base.Constants.RESULT_FROM_LOGIN;

/**
 * Created by Administrator on 2016/7/7.
 */

@Route(path = RouterConstants.USER_MOUDLE_ACTIVITY)
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
    private String str = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        str = getIntent().getStringExtra(Constants.START_LOGIN_WITH_PARAMS);
        ButterKnife.bind(this);
        mPresenter = new LoginPresenter(this);
        mPresenter.start();
    }


    @Override
    public void navigateToMain() {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void navigateToRegister() {
        Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
        startActivity(intent);
    }

    @OnClick({R2.id.iv_cancel, R2.id.btn_login, R2.id.btn_register})
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.iv_cancel) {
            Intent intent = new Intent();
            intent.putExtra(PARAMS_RESULT_FROM_LOGIN, "login sucess");
            setResult(RESULT_FROM_LOGIN, intent);
            finish();
        } else if (i == R.id.btn_login) {
            //mPresenter.login(tvPhone.getText().toString(), tvPassword.getText().toString());

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
        Toast.makeText(this, "登录测试,来自app/MainActivity的参数--" + str, Toast.LENGTH_SHORT).show();

    }
}
