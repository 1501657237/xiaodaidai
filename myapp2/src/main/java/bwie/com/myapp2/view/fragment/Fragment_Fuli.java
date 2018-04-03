package bwie.com.myapp2.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
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
import bwie.com.myapp2.presenter.FuliPresenter;
import bwie.com.myapp2.util.JieKou;
import bwie.com.myapp2.view.IView.FuLiMain;
import bwie.com.myapp2.view.adapter.FuLiAdapter;
import bwie.com.myapp2.view.bean.FuLiBean;
import okhttp3.ResponseBody;

/**
 * Created by ASUS on 2018/3/23.
 */

public class Fragment_Fuli extends Fragment implements FuLiMain, View.OnClickListener {

    private RecyclerView fuli_rev;
    private FuliPresenter fuliPresenter;
    private FloatingActionButton flo_list;
    private FloatingActionButton flo_grid;
    private FloatingActionButton flo_sta;
    private FloatingActionsMenu actionmenu;
    private FuLiAdapter fuLiAdapter;
    private SmartRefreshLayout fuli_smart;
    private int page=1;
    private List<FuLiBean.ResultsBean> results;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fuli,container,false);
        fuli_rev = (RecyclerView) view.findViewById(R.id.fuli_rev);
        flo_list = (FloatingActionButton) view.findViewById(R.id.flo_list);
        flo_grid = (FloatingActionButton) view.findViewById(R.id.flo_grid);
        flo_sta = (FloatingActionButton) view.findViewById(R.id.flo_sta);
        actionmenu = (FloatingActionsMenu) view.findViewById(R.id.actionmenu);
        fuli_smart = (SmartRefreshLayout) view.findViewById(R.id.fuli_smart);
        fuliPresenter = new FuliPresenter(this);
        fuli_rev.setLayoutManager(new LinearLayoutManager(getActivity()));
        fuli_smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                results.clear();
                Map<String,String> map=new HashMap<>();
                fuliPresenter.getDate(JieKou.FULI_URL+page,map);
                fuli_smart.finishRefresh(2000);
            }
        });
        fuli_smart.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                Map<String,String> map=new HashMap<>();
                fuliPresenter.getDate(JieKou.FULI_URL+page,map);
                fuli_smart.finishLoadmore(2000);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Map<String, String> map=new HashMap<>();
        fuliPresenter.getDate(JieKou.FULI_URL+page,map);
        actionmenu.setVisibility(View.VISIBLE);
        flo_list.setOnClickListener(this);
        flo_grid.setOnClickListener(this);
        flo_sta.setOnClickListener(this);
    }
    @Override
    public void FuLiMainSuccess(ResponseBody responseBody) {
        try {
            String json = responseBody.string();
            FuLiBean fuLiBean = new Gson().fromJson(json, FuLiBean.class);
            results = fuLiBean.getResults();
             fuLiAdapter = new FuLiAdapter(results, getActivity());
            fuli_rev.setAdapter(fuLiAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Error(Throwable throwable) {

    }


    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.flo_list:
                    fuli_rev.setLayoutManager(new LinearLayoutManager(getActivity()));
                    fuli_rev.setAdapter(fuLiAdapter);
                    fuli_smart.setOnRefreshListener(new OnRefreshListener() {
                        @Override
                        public void onRefresh(RefreshLayout refreshlayout) {
                            page=1;
                            results.clear();
                            Map<String,String> map=new HashMap<>();
                            fuliPresenter.getDate(JieKou.FULI_URL+page,map);
                            fuli_smart.finishRefresh(2000);
                        }
                    });
                    fuli_smart.setOnLoadmoreListener(new OnLoadmoreListener() {
                        @Override
                        public void onLoadmore(RefreshLayout refreshlayout) {
                            page++;
                            Map<String,String> map=new HashMap<>();
                            fuliPresenter.getDate(JieKou.FULI_URL+page,map);
                            fuli_smart.finishLoadmore(2000);
                        }
                    });
                    break;
                case R.id.flo_grid:
                    fuli_rev.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false));
                    fuli_rev.setAdapter(fuLiAdapter);
                    fuli_smart.setOnRefreshListener(new OnRefreshListener() {
                        @Override
                        public void onRefresh(RefreshLayout refreshlayout) {
                            page=1;
                            results.clear();
                            Map<String,String> map=new HashMap<>();
                            fuliPresenter.getDate(JieKou.FULI_URL+page,map);
                            fuli_rev.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false));
                            fuli_rev.setAdapter(fuLiAdapter);
                            fuli_smart.finishRefresh(2000);
                        }
                    });
                    fuli_smart.setOnLoadmoreListener(new OnLoadmoreListener() {
                        @Override
                        public void onLoadmore(RefreshLayout refreshlayout) {
                            page++;
                            Map<String,String> map=new HashMap<>();
                            fuliPresenter.getDate(JieKou.FULI_URL+page,map);
                            fuli_rev.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false));
                            fuli_rev.setAdapter(fuLiAdapter);
                            fuli_smart.finishLoadmore(2000);
                        }
                    });
                    break;
                case R.id.flo_sta:
                    fuli_rev.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    fuli_rev.setAdapter(fuLiAdapter);
                    fuli_smart.setOnRefreshListener(new OnRefreshListener() {
                        @Override
                        public void onRefresh(RefreshLayout refreshlayout) {
                            page=1;
                            results.clear();
                            Map<String,String> map=new HashMap<>();
                            fuliPresenter.getDate(JieKou.FULI_URL+page,map);
                            fuli_rev.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                            fuli_rev.setAdapter(fuLiAdapter);
                            fuli_smart.finishRefresh(2000);
                        }
                    });
                    fuli_smart.setOnLoadmoreListener(new OnLoadmoreListener() {
                        @Override
                        public void onLoadmore(RefreshLayout refreshlayout) {
                            page++;
                            Map<String,String> map=new HashMap<>();
                            fuliPresenter.getDate(JieKou.FULI_URL+page,map);
                            fuli_rev.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                            fuli_rev.setAdapter(fuLiAdapter);
                            fuli_smart.finishLoadmore(2000);
                        }
                    });
                    break;
            }
    }


}
