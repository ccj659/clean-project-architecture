package com.efly.flyhelper.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.efly.flyhelper.ui.main.MainActivity;
import com.efly.flyhelper.R;
import com.efly.flyhelper.base.BaseActivity;
import com.efly.flyhelper.base.BaseApplication;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/7.
 */
public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View {

    @Bind(R.id.iv_cancel)
    ImageView ivCancel;
    @Bind(R.id.tv_phone)
    EditText tvPhone;
    @Bind(R.id.tv_password)
    EditText tvPassword;
    @Bind(R.id.cb_remember_pass)
    CheckBox cbRememberPass;
    @Bind(R.id.tv_forget_pass)
    TextView tvForgetPass;
    @Bind(R.id.linearLayout)
    LinearLayout linearLayout;
    @Bind(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.btn_register)
    Button btnRegister;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter=new LoginPresenter(this);
        mPresenter.start();
    }

    public void setPresenter(LoginPresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void navigateToMain() {
        Intent intent =new Intent(getBaseContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void navigateToRegister() {
        Intent intent =new Intent(getBaseContext(),MainActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.iv_cancel, R.id.btn_login, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_cancel:
                finish();
                break;
            case R.id.btn_login:
                mPresenter.login(tvPhone.getText().toString(),tvPassword.getText().toString());

                break;
            case R.id.btn_register:
                navigateToRegister();

                break;
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

}
