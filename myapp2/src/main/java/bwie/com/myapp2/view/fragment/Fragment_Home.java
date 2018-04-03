package bwie.com.myapp2.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

import bwie.com.myapp2.R;
import bwie.com.myapp2.view.adapter.TabAdapter;

/**
 * Created by ASUS on 2018/3/23.
 */

public class Fragment_Home extends Fragment {

    private TabLayout tab;
    private ViewPager home_vp;
    private List<Fragment> list;
    private List<String> title;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        tab = (TabLayout) view.findViewById(R.id.tab);
        home_vp = (ViewPager) view.findViewById(R.id.home_vp);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = new ArrayList<>();
        list.add(new Fragment_Android());
        list.add(new Fragment_Ios());
        list.add(new Fragment_Fuli());
        title = new ArrayList<>();
        title.add("ANDROID");
        title.add("IOS");
        title.add("福利");
        TabAdapter tabAdapter = new TabAdapter(getFragmentManager(), list,title);
        home_vp.setAdapter(tabAdapter);
        tab.setTabMode(TabLayout.MODE_FIXED);
        tab.setupWithViewPager(home_vp);

    }
}
