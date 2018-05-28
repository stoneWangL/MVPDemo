package com.stone.mvpdemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.stone.mvpdemo.bean.User;
import com.stone.mvpdemo.presenter.UserLoginPresenter;

public class MainActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private ProgressDialog dialog;

    private UserLoginPresenter presenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
    }

    //按钮点击
    public void Login(View View) {
        final String username = mUsername.getText().toString();
        final String password = mPassword.getText().toString();
        final User user = new User();
        user.username = username;
        user.password = password;

        boolean userInfo = presenter.checkUserInfo(user);

        if (userInfo){
            dialog = new ProgressDialog(this);
            dialog.show();
            presenter.login(user);
        }else{
            Toast.makeText(this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    //登陆成功
    public void success(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //登陆成功
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "欢迎回来"+mUsername.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //登陆失败
    public void failed(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //登陆失败
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
