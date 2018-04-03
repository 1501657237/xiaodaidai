package bwie.com.jingdong.View.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import bwie.com.jingdong.R;

/**
 * Created by LENOVO on 2018/3/22.
 */

public class MyListHolder04 extends RecyclerView.ViewHolder{
    public TextView textView;


    public MyListHolder04(View itemView) {
        super(itemView);
        //holder里面是需要拿到视图中的控件

        textView= itemView.findViewById(R.id.item4);

    }
}
