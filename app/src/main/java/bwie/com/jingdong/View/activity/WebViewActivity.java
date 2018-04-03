package bwie.com.jingdong.View.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import bwie.com.jingdong.R;

/**
 * Created by LENOVO on 2018/3/13.
 */

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView) findViewById(R.id.web_view);

        String detailUrl = getIntent().getStringExtra("detailUrl");

        if (detailUrl != null) {
            webView.loadUrl(detailUrl);

            //webview一系列设置
            webView.setWebViewClient(new WebViewClient());//在当前应用打开,而不是去浏览器
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
        }
    }
}
