package bwie.com.jingdong.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bwie.com.jingdong.Model.bean.FenLeiBean;
import bwie.com.jingdong.R;
import bwie.com.jingdong.View.activity.SouSuoActivity;
import bwie.com.jingdong.View.holder.MyListHolder04;

/**
 * Created by LENOVO on 2018/3/22.
 */

public class SouSuo_fenlei_adapter extends RecyclerView.Adapter<MyListHolder04>  {
    private Context context;
    private List<FenLeiBean.DataBean> list;

    public SouSuo_fenlei_adapter(Context context, List<FenLeiBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public MyListHolder04 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item04,null);
        //根据展示的条目的视图  创建viewHolder
        MyListHolder04 mylistHolder = new MyListHolder04(view);
        return mylistHolder;
    }

    @Override
    public void onBindViewHolder(MyListHolder04 holder, int position) {
        //设置文本
        holder.textView.setText(list.get(position).getName().toString());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
