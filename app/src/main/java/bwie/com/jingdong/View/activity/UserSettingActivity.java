package bwie.com.jingdong.View.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import bwie.com.jingdong.R;
import bwie.com.jingdong.util.CommonUtils;

/**
 * Created by LENOVO on 2018/3/23.
 */

public class
UserSettingActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView detail_image_back;
    private ImageView setting_icon;
    private TextView setting_name;
    private TextView text_exit;
    private RelativeLayout relative_user;
    private TextView text_manage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        detail_image_back = (ImageView) findViewById(R.id.detail_image_back);
        setting_icon = (ImageView) findViewById(R.id.setting_icon);
        setting_name = (TextView) findViewById(R.id.setting_name);
        text_exit = (TextView) findViewById(R.id.text_exit);
        relative_user = (RelativeLayout) findViewById(R.id.relative_user);
        text_manage = (TextView) findViewById(R.id.text_manage);

        detail_image_back.setOnClickListener(this);
        text_exit.setOnClickListener(this);
        relative_user.setOnClickListener(this);
        text_manage.setOnClickListener(this);

    }
    @Override
    protected void onResume() {
        super.onResume();

        //判断一下是否登录..,..当登录成功之后,先存一下boolean值,,,在这里取出来判断
        boolean isLogin = CommonUtils.getBoolean("isLogin");

        if (isLogin) {
            if ( "".equals(CommonUtils.getString("iconUrl"))  || "null".equals(CommonUtils.getString("iconUrl"))){
                //显示默认头像
                setting_icon.setImageResource(R.drawable.user);
            }else {

                //1.加载一下头像显示...判断一下是否有头像的路径,,,没有则显示默认的头像
                Glide.with(this).load(CommonUtils.getString("iconUrl")).into(setting_icon);

                //加载圆形
               // GlideImgManager.glideLoader(UserSettingActivity.this, CommonUtils.getString("iconUrl"), R.drawable.user, R.drawable.user, setting_icon, 0);
                Glide.with(UserSettingActivity.this).load(CommonUtils.getString("iconUrl")).asBitmap().centerCrop().into(new BitmapImageViewTarget(setting_icon) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(UserSettingActivity.this.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        setting_icon.setImageDrawable(circularBitmapDrawable);
                    }
                });
            }
            //2.先试一下用户名
            setting_name.setText(CommonUtils.getString("name"));

        }else {
            //显示默认头像
            setting_icon.setImageResource(R.drawable.user);
            //用户名显示 登录/注册 >
            setting_name.setText("登录/注册 >");

        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detail_image_back:

                finish();

                break;
            case R.id.text_exit://退出登录...实际上是请求退出的接口..在这把登录成功保存的信息清空..结束当前页面的显示

                CommonUtils.clearSp("isLogin");
                CommonUtils.clearSp("uid");
                CommonUtils.clearSp("name");
                CommonUtils.clearSp("iconUrl");

                finish();
                break;
            case R.id.relative_user://进入个人中心

                Intent intent = new Intent(UserSettingActivity.this,UserInfoActivity.class);

                startActivity(intent);
                break;
            case R.id.text_manage://跳转地址管理
                Intent intent1 = new Intent(UserSettingActivity.this,ManageAddrActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
