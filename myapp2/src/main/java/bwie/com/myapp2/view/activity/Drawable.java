package bwie.com.myapp2.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bwie.com.myapp2.R;
import bwie.com.myapp2.presenter.DrawPresenter;
import bwie.com.myapp2.util.JieKou;
import bwie.com.myapp2.view.IView.AndroidMain;
import bwie.com.myapp2.view.IView.ClickListener;
import bwie.com.myapp2.view.adapter.DrawAdapter;
import bwie.com.myapp2.view.bean.DrawBean;
import okhttp3.ResponseBody;

public class Drawable extends AppCompatActivity implements AndroidMain {

    private RecyclerView qianduan_rev;
    private DrawPresenter drawPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qian_duan);
        qianduan_rev = (RecyclerView) findViewById(R.id.qianduan_rev);
        qianduan_rev.setLayoutManager(new LinearLayoutManager(Drawable.this));
        String categroy = getIntent().getStringExtra("categroy");
        if (categroy!=null){
            drawPresenter = new DrawPresenter(this);
            Map<String, String> map=new HashMap<>();
            map.put("categroy",categroy);
            drawPresenter.getDate(JieKou.DRAW_URL+categroy+ JieKou.DRAW_URL2,map);
        }


    }

    @Override
    public void AndroidMainSuccess(ResponseBody responseBody) {
        try {
            String json = responseBody.string();
            DrawBean drawBean = new Gson().fromJson(json, DrawBean.class);
            final List<DrawBean.ResultsBean> results = drawBean.getResults();
            DrawAdapter drawAdapter = new DrawAdapter(results, Drawable.this);
            qianduan_rev.setAdapter(drawAdapter);
            drawAdapter.OnItemClick(new ClickListener() {
                @Override
                public void OnitemClicklistener(int position) {
                    String url = results.get(position).getUrl();
                    Intent intent = new Intent(Drawable.this, MyBlog.class);
                    intent.putExtra("url",url);
                    startActivity(intent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Error(Throwable throwable) {

    }

}
