package bwie.com.myapp2.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwie.com.myapp2.R;
import bwie.com.myapp2.view.bean.HomeBean;

/**
 * Created by ASUS on 2018/3/25.
 */

public class AndroidAdapter extends RecyclerView.Adapter<AndroidAdapter.ViewHolder> {
    private List<HomeBean.ResultsBean> results;
    private Context context;
    private String img;

    public AndroidAdapter(List<HomeBean.ResultsBean> results, Context context) {
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.android_name.setText(results.get(position).getWho());
        holder.android_time.setText(results.get(position).getCreatedAt());
        holder.android_title.setText(results.get(position).getDesc());
        List<String> images = results.get(position).getImages();
        if (images!=null){
            img = images.get(0);
        }else {
            img="error";
        }
        Uri parse = Uri.parse(img);
        holder.android_img.setImageURI(parse);
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(parse)
                .setTapToRetryEnabled(true).setOldController(holder.android_img.getController())
                .build();
        holder.android_img.setController(controller);

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private  TextView android_title;
        private  TextView android_time;
        private  TextView android_name;
        private  SimpleDraweeView android_img;

        public ViewHolder(View itemView) {
            super(itemView);
            android_img = itemView.findViewById(R.id.android_img);
            android_name = itemView.findViewById(R.id.android_name);
            android_time = itemView.findViewById(R.id.android_time);
            android_title = itemView.findViewById(R.id.android_title);
        }
    }
}
