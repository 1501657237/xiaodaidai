package bwie.com.jingdong.View.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import bwie.com.jingdong.Model.bean.RegistBean;
import bwie.com.jingdong.Presenter.RegistPresenter;
import bwie.com.jingdong.R;
import bwie.com.jingdong.View.Iview.RegistActivityInter;
import bwie.com.jingdong.util.ApiUtil;

/**
 * Created by LENOVO on 2018/3/21.
 */

public class RegistActivity extends AppCompatActivity implements RegistActivityInter, View.OnClickListener  {
    private EditText edit_phone;
    private EditText edit_pwd;
    private RegistPresenter registPresenter;
    private ImageView cha_iamge;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        edit_phone =(EditText) findViewById(R.id.edit_phone);
        edit_pwd = (EditText) findViewById(R.id.edit_pwd);
        cha_iamge = (ImageView) findViewById(R.id.cha_iamge);
        //中间者
        registPresenter = new RegistPresenter(this);

        cha_iamge.setOnClickListener(this);


    }
    /**
     * 注册的点击事件
     * @param view
     */
    public void regist(View view) {

        String name = edit_phone.getText().toString();
        String pwd = edit_pwd.getText().toString();

        registPresenter.registUser(ApiUtil.REGIST_URL,name,pwd);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cha_iamge://叉

                finish();
                break;
        }
    }

    @Override
    public void onRegistSuccess(final RegistBean registBean) {
      runOnUiThread(new Runnable() {
          @Override
          public void run() {
              String code = registBean.getCode();
              if ("1".equals(code)) {//注册失败
                  Toast.makeText(RegistActivity.this,registBean.getMsg(),Toast.LENGTH_SHORT).show();
              }else {
                  Toast.makeText(RegistActivity.this,registBean.getMsg(),Toast.LENGTH_SHORT).show();
                  //并且结束显示
                  finish();
              }
          }
      });
    }
}
