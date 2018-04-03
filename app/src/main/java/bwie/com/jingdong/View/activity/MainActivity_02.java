package bwie.com.jingdong.View.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import bwie.com.jingdong.Model.bean.ProductListBean;
import bwie.com.jingdong.Model.bean.Pruducts;
import bwie.com.jingdong.Presenter.MainPresenter_SouSuo;
import bwie.com.jingdong.Presenter.ProductListPresenter;
import bwie.com.jingdong.R;
import bwie.com.jingdong.View.Adapter.ProDuctGridAdapter;
import bwie.com.jingdong.View.Adapter.ProDuctListAdapter;
import bwie.com.jingdong.View.Iview.IMain_Activity_02;
import bwie.com.jingdong.View.Iview.OnItemListner;
import bwie.com.jingdong.View.Iview.ProductListActivityInter;
import bwie.com.jingdong.util.ApiUtil;

import static bwie.com.jingdong.R.id.product_list_recycler;

/**
 * Created by LENOVO on 2018/3/29.
 */

public class MainActivity_02 extends AppCompatActivity implements View.OnClickListener,ProductListActivityInter {
    private MainPresenter_SouSuo mainPresenter_02;
    private TextView btn_fan2;
    private RecyclerView recycler01;
    private RecyclerView recycler02;
    private TextView tv_sousuo;
    private CheckBox huan;
    private String name;
    private int page = 1;

    private MainPresenter_SouSuo presenterSouSuo;
    private ProDuctListAdapter proDuctListAdapter;
    private ProDuctGridAdapter proDuctGridAdapter;
    private RefreshLayout refreshLayout;
    private boolean isList = true;//是否是列表展示
    private List<ProductListBean.DataBean> listAll  = new ArrayList<>();//装当前页面所有的数据
    private ProductListPresenter productListPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main02);
        btn_fan2 = (TextView) findViewById(R.id.btn_fan2);
        recycler01 = (RecyclerView) findViewById(R.id.recycler01);
        recycler02 = (RecyclerView) findViewById(R.id.recycler02);
        tv_sousuo = (TextView) findViewById(R.id.tv_sousuo);
        huan = (CheckBox) findViewById(R.id.iv_xxs);
        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);


        huan.setOnClickListener(this);
        tv_sousuo.setOnClickListener(this);
        btn_fan2.setOnClickListener(this);

        productListPresenter = new ProductListPresenter(this);

        //传值
        Intent intent = getIntent();
         name = intent.getStringExtra("name");
        if (name != null) {
            //根据关键词和page去请求列表数据

            productListPresenter.getProductData(ApiUtil.SEARTCH_URL,name,page);


        }
        //设置列表布局
        recycler01.setLayoutManager(new LinearLayoutManager(MainActivity_02.this));
        recycler02.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));

        //下拉刷新的监听
        RefreshLayout refreshLayout = this.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                //集合清空
                listAll.clear();
                //重新获取数据
                productListPresenter.getProductData(ApiUtil.SEARTCH_URL, name, page);

            }
        });
        //上拉加载的监听
        this.refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page ++;
                //重新获取数据
                productListPresenter.getProductData(ApiUtil.SEARTCH_URL,name,page);

            }
        });



    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_sousuo:
                Intent intent=new Intent(MainActivity_02.this,SouSuoActivity.class);
                overridePendingTransition(R.anim.rightin, R.anim.leftout);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_fan2:

                overridePendingTransition(R.anim.rightin, R.anim.leftout);

                finish();
                break;
            case R.id.iv_xxs:

                if (isList) {//表示当前展示的是列表..图标变成列表样式...表格进行显示,列表隐藏...isList---false


                    recycler02.setVisibility(View.VISIBLE);
                    recycler01.setVisibility(View.GONE);

                    isList = false;
                }else {


                    recycler01.setVisibility(View.VISIBLE);
                    recycler02.setVisibility(View.GONE);

                    isList = true;
                }

                break;


        }
    }
    private void setAdapter() {

        //设置列表设备器
        if (proDuctListAdapter == null) {
            proDuctListAdapter = new ProDuctListAdapter(MainActivity_02.this, listAll);
            recycler01.setAdapter(proDuctListAdapter);
        }else {
            proDuctListAdapter.notifyDataSetChanged();
        }

        //设置表格适配器
        if (proDuctGridAdapter == null) {
            proDuctGridAdapter = new ProDuctGridAdapter(MainActivity_02.this, listAll);
            recycler02.setAdapter(proDuctGridAdapter);
        }else {
            proDuctGridAdapter.notifyDataSetChanged();
        }

        //停止刷新和加载更多
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();


    }

    @Override
    public void getProductDataSuccess(final ProductListBean productListBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //先把数据添加到大集合
                listAll.addAll(productListBean.getData());

                //设置适配器就可以了

                setAdapter();


                //条目的点击事件 调到详情页面
                proDuctListAdapter.setOnItemListner(new OnItemListner() {
                    @Override
                    public void onItemClick(int position) {

                        //跳转详情
                        Intent intent = new Intent(MainActivity_02.this,DetailActivity.class);
                        intent.putExtra("pid",listAll.get(position).getPid());
                        startActivity(intent);

                    }

                    @Override
                    public void onItemLongClick(int position) {

                    }
                });
                proDuctGridAdapter.setOnItemListner(new OnItemListner() {
                    @Override
                    public void onItemClick(int position) {

                        //跳转详情
                        Intent intent = new Intent(MainActivity_02.this,DetailActivity.class);
                        intent.putExtra("pid",listAll.get(position).getPid());
                        startActivity(intent);

                    }

                    @Override
                    public void onItemLongClick(int position) {

                    }
                });
            }
        });
    }
}
