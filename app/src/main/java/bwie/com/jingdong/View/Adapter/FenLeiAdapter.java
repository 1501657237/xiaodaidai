package bwie.com.jingdong.View.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import bwie.com.jingdong.Model.bean.FenLeiBean;
import bwie.com.jingdong.R;

/**
 * Created by LENOVO on 2018/3/14.
 */

public class FenLeiAdapter extends BaseAdapter {
    private FenLeiBean fenLeiBean;
    private Context context;
    private int i;

    public FenLeiAdapter(Context  context, FenLeiBean fenLeiBean) {
        this.context = context;
        this.fenLeiBean = fenLeiBean;
    }

    @Override
    public int getCount() {
        return fenLeiBean.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return fenLeiBean.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view == null){
            view = View.inflate(context, R.layout.fen_lei_item_layout,null);
            holder = new ViewHolder();

            holder.textView = view.findViewById(R.id.fen_lei_item_text);

            view.setTag(holder);
       }else {
            holder = (ViewHolder) view.getTag();
        }

        FenLeiBean.DataBean dataBean = fenLeiBean.getData().get(position);
        holder.textView.setText(dataBean.getName());
        //判断
        if (position == i) {
            //设置灰色的背景 和红色文字
            view.setBackgroundColor(Color.TRANSPARENT);
            holder.textView.setTextColor(Color.RED);
        }else {
            //白色的背景和黑色的文字
            view.setBackgroundColor(Color.WHITE);
            holder.textView.setTextColor(Color.BLACK);
        }

        return view;
    }
    private class ViewHolder{
        TextView textView;
    }
    /**
     * 设置的是点击了条目的位置
     * @param i
     */
    public void setCurPositon(int i) {
        this.i = i;
    }

}
