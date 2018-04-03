package bwie.com.myapp2.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.List;

import bwie.com.myapp2.R;
import bwie.com.myapp2.view.bean.GoodsBean;

/**
 * Created by ASUS on 2018/3/25.
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {
    private List<GoodsBean.ResultsBean> results;
    private Context context;

    public GoodsAdapter(List<GoodsBean.ResultsBean> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_homefragment, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(results.get(position).getDesc());
        holder.tvAuthor.setText(results.get(position).getWho());
        holder.tvTime.setText(results.get(position).getPublishedAt());
        String url = results.get(position).getUrl();

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvAuthor;
        private  TextView tvTime;
        private  TextView tvTitle;
        private ImageView good_img;

        public ViewHolder(View itemView) {
            super(itemView);
            good_img = itemView.findViewById(R.id.good_img);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
        }


    }
}
