package bwie.com.myapp2.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;




import java.util.List;

import bwie.com.myapp2.R;
import bwie.com.myapp2.model.HostroyBean;

/**
 * 作者：Leon
 * 时间：2017/6/13
 * 描述:
 */
public class HistorySearchAdapter extends RecyclerView.Adapter<HistorySearchAdapter.ViewHolder> {

    private Context mContext;
    private List<HostroyBean> hostroyBeen;
    private OnClickListener mOnMyClickListener;
    private OnItemClickLsetener onItemClickLsetener;

    public interface OnClickListener {
        void onClick(int position);
    }

    public HistorySearchAdapter(Context mContext, List<HostroyBean> hostroyBeen) {
        this.mContext = mContext;
        this.hostroyBeen = hostroyBeen;
    }

    public void setmOnMyClickListener(OnClickListener mOnMyClickListener) {
        this.mOnMyClickListener = mOnMyClickListener;
    }

    public void setHostroyBeen(List<HostroyBean> hostroyBeen) {
        this.hostroyBeen = hostroyBeen;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_history_search, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(hostroyBeen.get(position).getName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(onItemClickLsetener!=null)
                   onItemClickLsetener.itemClick(position);
            }
        });
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnMyClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hostroyBeen.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_info);
            imageView = (ImageView) itemView.findViewById(R.id.iv_clear);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.layout_historysearch);
        }
    }

    public interface OnItemClickLsetener{
        void itemClick(int position);
    }

    public void setOnItemClickLsetener(OnItemClickLsetener onItemClickLsetener){
        this.onItemClickLsetener = onItemClickLsetener;
    }
}
