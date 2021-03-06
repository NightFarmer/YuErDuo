package com.jqyd.yuerduo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.jqyd.yuerduo.R;
import com.jqyd.yuerduo.activity.main.MainActivity;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.et_username)
    EditText et_username;

    @Bind(R.id.et_password)
    EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.bt_login)
    public void onSubmit() {
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "登陆账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Logger.i("login:" + username + ",password:" + password);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @OnClick(R.id.bt_forgetPWD)
    public void onForgetPassword() {
        startActivity(new Intent(this, ResetPasswordActivity1.class));
    }
}
