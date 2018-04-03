package bwie.com.jingdong.View.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import bwie.com.jingdong.R;
import bwie.com.jingdong.View.activity.SouSuoActivity;

/**
 * Created by LENOVO on 2018/3/22.
 */

public class listAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public listAdapter(List<String> list, Context context) {
        this.list = list;
        //Log.e("TAG",list.size()+"====");
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView = View.inflate(context, R.layout.item5, null);
            viewHolder.tv=convertView.findViewById(R.id.text4);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }

        viewHolder.tv.setText(list.get(position));



        return convertView;
    }

    class ViewHolder{
        TextView tv;
    }
}
