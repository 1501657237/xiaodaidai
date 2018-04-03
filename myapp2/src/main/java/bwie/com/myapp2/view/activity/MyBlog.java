package bwie.com.myapp2.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import bwie.com.myapp2.R;
import bwie.com.myapp2.util.StringUtils;


public class MyBlog extends AppCompatActivity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_blog);
        web = (WebView) findViewById(R.id.web);
        String url = getIntent().getStringExtra("url");
        if (StringUtils.isEmpty(url)) {
            Toast.makeText(MyBlog.this, "加载失败，重新加载", Toast.LENGTH_SHORT).show();
            return;
        }
        web.loadUrl(url);
    }
}
