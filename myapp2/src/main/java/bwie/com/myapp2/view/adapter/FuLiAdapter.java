package bwie.com.myapp2.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwie.com.myapp2.R;
import bwie.com.myapp2.view.bean.FuLiBean;

/**
 * Created by ASUS on 2018/3/25.
 */

public class FuLiAdapter extends RecyclerView.Adapter<FuLiAdapter.ViewHolder> {
    private List<FuLiBean.ResultsBean> results;
    private Context context;

    public FuLiAdapter(List<FuLiBean.ResultsBean> results, Context context) {
        Log.e("loh", results.get(0).getCreatedAt());
        this.results = results;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.fuli, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String url = results.get(position).getUrl();
        Glide.with(context).load(url).into(holder.fuli_img);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView fuli_img;

        public ViewHolder(View itemView) {
            super(itemView);
            fuli_img = itemView.findViewById(R.id.fuli_img);
        }


    }
}
