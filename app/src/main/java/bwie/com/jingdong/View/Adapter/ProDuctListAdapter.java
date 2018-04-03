package bwie.com.jingdong.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import bwie.com.jingdong.Model.bean.ProductListBean;
import bwie.com.jingdong.R;
import bwie.com.jingdong.View.Iview.OnItemListner;
import bwie.com.jingdong.View.holder.ProductListHolder;

/**
 * Created by LENOVO on 2018/3/30.
 */

public class ProDuctListAdapter extends RecyclerView.Adapter<ProductListHolder> {
    private  List<ProductListBean.DataBean> listAll;
    private Context context;
    private OnItemListner onItemListner;

    public ProDuctListAdapter(Context context, List<ProductListBean.DataBean> listAll) {
        this.context = context;
        this.listAll = listAll;
    }

    @Override
    public ProductListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.product_list_item_layout,null);

        ProductListHolder productListHolder = new ProductListHolder(view);

        return productListHolder;
    }

    @Override
    public void onBindViewHolder(ProductListHolder holder, final int position) {

        ProductListBean.DataBean dataBean = listAll.get(position);

        //赋值
        holder.product_list_title.setText(dataBean.getTitle());
        holder.product_list_price.setText("¥"+dataBean.getBargainPrice());

        Glide.with(context).load(dataBean.getImages().split("\\|")[0]).into(holder.product_list_image);

        //点击
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemListner.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listAll.size();
    }

    public void setOnItemListner(OnItemListner onItemListner) {
        this.onItemListner = onItemListner;
    }
}
