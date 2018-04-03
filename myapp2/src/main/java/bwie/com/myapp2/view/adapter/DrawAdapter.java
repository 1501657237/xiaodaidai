package bwie.com.myapp2.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwie.com.myapp2.R;
import bwie.com.myapp2.view.IView.ClickListener;
import bwie.com.myapp2.view.bean.DrawBean;

/**
 * Created by ASUS on 2018/4/1.
 */

public class DrawAdapter extends RecyclerView.Adapter<DrawAdapter.ViewHolder> {
    private List<DrawBean.ResultsBean> results;
    private Context context;
    private ClickListener clickListener;
    public DrawAdapter(List<DrawBean.ResultsBean> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.android,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String url = results.get(position).getUrl();
        Uri parse = Uri.parse(url);
        holder.android_img.setImageURI(parse);
        holder.android_title.setText(results.get(position).getDesc());
        holder.android_name.setText(results.get(position).getType());
        holder.android_time.setText(results.get(position).getPublishedAt());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.OnitemClicklistener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView android_title;
        private  TextView android_time;
        private  TextView android_name;
        private SimpleDraweeView android_img;
        public ViewHolder(View itemView) {
            super(itemView);
            android_img = itemView.findViewById(R.id.android_img);
            android_name = itemView.findViewById(R.id.android_name);
            android_time = itemView.findViewById(R.id.android_time);
            android_title = itemView.findViewById(R.id.android_title);
        }
    }
    public void OnItemClick(ClickListener clickListener){
        this.clickListener=clickListener;
    };
}
