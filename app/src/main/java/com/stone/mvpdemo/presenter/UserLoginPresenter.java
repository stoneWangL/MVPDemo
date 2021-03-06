package com.stone.mvpdemo.presenter;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.stone.mvpdemo.IUserLoginView;
import com.stone.mvpdemo.MainActivity;
import com.stone.mvpdemo.bean.User;
import com.stone.mvpdemo.net.UserLoginNet;

/**
 * 业务相关
 */
public class UserLoginPresenter {

    //Activity 和 Fragment 通用性不好
    //提高通用性：放置参数为通用（抽象类或接口，实际开发中接口更通用）
    private IUserLoginView view;

    public UserLoginPresenter(IUserLoginView view){
        this.view = view;
    }

    //判断用户输入
    public boolean checkUserInfo(User user){
        if (TextUtils.isEmpty(user.username) || TextUtils.isEmpty(user.password)){
            return false;
        }
        return true;
    }

    //用户登陆
    public void login(final User user){
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserLoginNet net = new UserLoginNet();
                if (net.sendUserLoginInfo(user)){
                    view.success();
                }else{
                    view.failed();
                }
            }
        }).start();
    }

}
