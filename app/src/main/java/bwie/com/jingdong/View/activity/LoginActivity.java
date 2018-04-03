package bwie.com.jingdong.View.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import bwie.com.jingdong.Model.bean.LoginBean;
import bwie.com.jingdong.Presenter.LoginPresenter;
import bwie.com.jingdong.R;
import bwie.com.jingdong.View.Iview.LoginActivityInter;
import bwie.com.jingdong.util.ApiUtil;
import bwie.com.jingdong.util.CommonUtils;

/**
 * Created by LENOVO on 2018/3/21.
 */

public class LoginActivity extends AppCompatActivity implements LoginActivityInter, View.OnClickListener{

    private EditText edit_pwd;
    private EditText edit_phone;
    private TextView text_regist;
    private ImageView login_by_qq;
    private ImageView login_by_wechat;
    private LoginPresenter loginPresenter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        edit_pwd = (EditText)findViewById(R.id.edit_pwd);
        text_regist = (TextView) findViewById(R.id.text_regist);
        login_by_wechat = (ImageView) findViewById(R.id.login_by_wechat);
        login_by_qq = (ImageView)findViewById(R.id.login_by_qq);

        //登录的presenter
        loginPresenter = new LoginPresenter(this);
        //点击事件
        text_regist.setOnClickListener(this);
        login_by_wechat.setOnClickListener(this);
        login_by_qq.setOnClickListener(this);

    }
    /**
     * 登录的点击事件
     * @param view
     */
    public void login(View view) {
        String phone = edit_phone.getText().toString();
        String pwd = edit_pwd.getText().toString();

        loginPresenter.getLogin(ApiUtil.LOGIN_URL,phone,pwd);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_regist:
                //跳转注册页面
                Intent intent = new Intent(LoginActivity.this,RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.login_by_wechat://微信登录

            //    UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN, authListener);

                break;
            case R.id.login_by_qq://qq登录

                UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);

                break;
        }
    }


    @Override
    public void getLoginSuccess(final LoginBean loginBean) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this,loginBean.getMsg(), Toast.LENGTH_SHORT).show();

                if ("0".equals(loginBean.getCode())) {//登录成功
                    //登录成功之后需要做:.....保存状态true...
                    CommonUtils.putBoolean("isLogin",true);
                    CommonUtils.saveString("uid", String.valueOf(loginBean.getData().getUid()));
                    CommonUtils.saveString("name",loginBean.getData().getUsername());
                    CommonUtils.saveString("iconUrl",loginBean.getData().getIcon());

                    //结束当前界面
                    finish();
                }
            }
        });

    }

    /**
     * 授权的监听事件
     */
    private UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {


            String qq_uid = data.get("uid");
            String ni_cheng = data.get("name");
            String iconurl = data.get("iconurl");

            //实际上是根据这些qq提供的信息,去服务器拿到分配的临时账号,登录进京东的服务器
            //授权成功拿到信息模拟登录
            Log.i("----",ni_cheng+"--"+iconurl);

            loginPresenter.getLoginByQQ("1501657237","123456",ni_cheng,iconurl);


        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(),                                  Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    /**
     * 用qq登录成功的回调
     * @param loginBean
     * @param ni_cheng
     * @param iconurl
     */
    @Override
    public void getLoginSuccessByQQ(final LoginBean loginBean, final String ni_cheng, final String iconurl) {
       runOnUiThread(new Runnable() {
           @Override
           public void run() {
               if ("0".equals(loginBean.getCode())) {//登录成功
                   //登录成功之后需要做:.....保存状态true...
                   CommonUtils.putBoolean("isLogin",true);
                   CommonUtils.saveString("uid", String.valueOf(loginBean.getData().getUid()));
                   CommonUtils.saveString("name",ni_cheng);
                   CommonUtils.saveString("iconUrl",iconurl);

                   //结束当前界面
                   finish();
               }
           }
       });
    }
}
