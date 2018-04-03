package bwie.com.myapp2.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bwie.com.myapp2.R;


/**
 * Created by ASUS on 2018/3/23.
 */

public class Fragment_Read extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // View view=inflater.inflate(R.layout.fragment_read,container,false);
        TextView textView=new TextView(getActivity());
        textView.setText("这是一个阅读页面");

        return textView;

    }
}
