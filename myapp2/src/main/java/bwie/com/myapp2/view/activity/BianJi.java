package bwie.com.myapp2.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import bwie.com.myapp2.R;
import bwie.com.myapp2.util.StringUtils;


public class BianJi extends BaseActivity {
    private View mView;
    private TextInputLayout mLayoutNickName;
    private TextInputLayout mLayoutBlog;
    private TextInputLayout mLayoutOther;
    private String mName;
    private String mBlog;
    private String mOther;
    private Button mBtnSave;

    @Override
    protected void initOperation(Intent intent) {

    }

    @Override
    protected View addContentView() {
        mView = View.inflate(this, R.layout.activity_bian_ji, null);
        mLayoutNickName = (TextInputLayout) mView.findViewById(R.id.layout_nickname);
        mLayoutBlog = (TextInputLayout) mView.findViewById(R.id.layout_blog);
        mLayoutOther = (TextInputLayout) mView.findViewById(R.id.layout_other);
        mBtnSave = (Button) mView.findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkInfor()){
                    return;
                }
                SharedPreferences preferences = getSharedPreferences("name", 0);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putString("mName",mName);
                edit.putString("mBlog",mBlog);
                edit.putString("mOther",mOther);
                edit.commit();
                finish();
            }
        });
        return mView;
    }

    private boolean checkInfor() {
        mName = mLayoutNickName.getEditText().getText().toString().trim();
        if (StringUtils.isEmpty(mName)) {
            mLayoutNickName.setError("请填写昵称");
            return false;
        }
        mBlog = mLayoutBlog.getEditText().getText().toString().trim();
        if (StringUtils.isEmpty(mBlog)) {
            mLayoutBlog.setError("请填写博客地址");
            return false;
        }
        mOther = mLayoutOther.getEditText().getText().toString().trim();
        if (StringUtils.isEmpty(mOther)) {
            mLayoutOther.setError("请填写其他地址");
            return false;
        }
        mLayoutNickName.setError("");
        mLayoutBlog.setError("");
        mLayoutOther.setError("");
        return true;
    }

    @Override
    protected void updateOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_save).setVisible(false);
        menu.findItem(R.id.action_share).setVisible(false);
        menu.findItem(R.id.action_download).setVisible(false);
    }

    @Override
    protected String setToolbarTitle() {
        return "编辑";
    }


}
