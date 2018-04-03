package bwie.com.jingdong.View.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import bwie.com.jingdong.R;

/**
 * Created by LENOVO on 2018/3/13.
 */

public class TuiJianHolder extends RecyclerView.ViewHolder {
    public TextView tui_jian_item_title;
    public TextView tui_jian_item_price;
    public ImageView simpleDraweeView;

    public TuiJianHolder(View itemView) {
        super(itemView);
        tui_jian_item_title = itemView.findViewById(R.id.tui_jian_item_title);
        tui_jian_item_price = itemView.findViewById(R.id.tui_jian_item_price);
        simpleDraweeView=itemView.findViewById(R.id.tui_jian_item_image);
    }
}
