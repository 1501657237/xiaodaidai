package bwie.com.myapp2.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;


import bwie.com.myapp2.R;
import skin.support.app.SkinCompatActivity;

public abstract class BaseActivity extends SkinCompatActivity {

    private LinearLayout mRootLayout;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Intent intent = getIntent();
        initToolbar();
        mRootLayout = (LinearLayout) findViewById(R.id.root_layout);
        mRootLayout.addView(addContentView());
        initOperation(intent);
    }

    protected abstract void initOperation(Intent intent);

    protected abstract View addContentView();

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(setToolbarTitle());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_base_toolbar, menu);
        updateOptionsMenu(menu);
        return true;
    }

    protected void updateOptionsMenu(Menu menu) {
    }

    protected String setToolbarTitle() {
        return "xiangmu2";
    }
}
