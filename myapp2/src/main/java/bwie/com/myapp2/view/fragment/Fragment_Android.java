package bwie.com.myapp2.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bwie.com.myapp2.R;
import bwie.com.myapp2.presenter.HomePresenter;
import bwie.com.myapp2.util.JieKou;
import bwie.com.myapp2.view.IView.AndroidMain;
import bwie.com.myapp2.view.adapter.AndroidAdapter;
import bwie.com.myapp2.view.bean.HomeBean;
import okhttp3.ResponseBody;

/**
 * Created by ASUS on 2018/3/23.
 */

public class Fragment_Android extends Fragment implements AndroidMain {

    private RecyclerView android_rev;
    private HomePresenter homePresenter;
    private SmartRefreshLayout smart;
    private  int page=1;
    private List<HomeBean.ResultsBean> results;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_andtoid,container,false);
        android_rev = (RecyclerView) view.findViewById(R.id.android_rev);
        smart = (SmartRefreshLayout) view.findViewById(R.id.smart);
        homePresenter = new HomePresenter(this);
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                results.clear();
                Map<String,String> map=new HashMap<>();
                homePresenter.getDate(JieKou.ANDROID_URL+page,map);
                smart.finishRefresh(2000);
            }
        });
        smart.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                Map<String,String> map=new HashMap<>();
                homePresenter.getDate(JieKou.ANDROID_URL+page,map);
                smart.finishLoadmore(2000);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Map<String,String> map=new HashMap<>();
        homePresenter.getDate(JieKou.ANDROID_URL+page,map);
    }


    @Override
    public void AndroidMainSuccess(ResponseBody responseBody) {
        try {
            String json = responseBody.string();
            Gson gson = new Gson();
            HomeBean homeBean = gson.fromJson(json, HomeBean.class);
            results = homeBean.getResults();
            android_rev.setLayoutManager(new LinearLayoutManager(getActivity()));
            AndroidAdapter androidAdapter = new AndroidAdapter(results, getActivity());
            android_rev.setAdapter(androidAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void Error(Throwable throwable) {

    }
}
