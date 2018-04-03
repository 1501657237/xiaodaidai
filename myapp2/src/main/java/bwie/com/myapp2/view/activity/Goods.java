package bwie.com.myapp2.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bwie.com.myapp2.R;
import bwie.com.myapp2.presenter.SelectPresenter;
import bwie.com.myapp2.util.JieKou;
import bwie.com.myapp2.view.IView.SelectMain;
import bwie.com.myapp2.view.adapter.GoodsAdapter;
import bwie.com.myapp2.view.bean.GoodsBean;

public class Goods extends AppCompatActivity implements SelectMain {

    private ImageView mIvBack;
    private EditText mEtSearch;
    private SelectPresenter selectPresenter;
    private RecyclerView good_rev;
    private SmartRefreshLayout good_smart;
    private int page=1;
    private List<GoodsBean.ResultsBean> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mEtSearch = (EditText) findViewById(R.id.et_search);
        good_rev = (RecyclerView) findViewById(R.id.good_rev);
        good_smart = (SmartRefreshLayout) findViewById(R.id.good_smart);
        String mKeywords = getIntent().getStringExtra("mKeywords");
        final String url= JieKou.SELECT_URL2+mKeywords+JieKou.SELECT_URL+page;
        selectPresenter = new SelectPresenter(this);
        selectPresenter.getDate(url);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        good_smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                results.clear();
                selectPresenter.getDate(url);
                good_smart.finishRefresh(2000);
            }
        });
        good_smart.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                selectPresenter.getDate(url);
                good_smart.finishLoadmore(2000);
            }
        });
    }

    @Override
    public void SelectSuccess(final GoodsBean goodsBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                results = goodsBean.getResults();
                good_rev.setLayoutManager(new LinearLayoutManager(Goods.this));
                GoodsAdapter goodsAdapter = new GoodsAdapter(results, Goods.this);
                good_rev.setAdapter(goodsAdapter);
            }
        });
    }
}
