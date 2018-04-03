package bwie.com.jingdong.View.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import bwie.com.jingdong.R;

/**
 * Created by LENOVO on 2018/3/14.
 */

public class FenLeiRecyclerOutHolder extends RecyclerView.ViewHolder {
    public TextView recycler_out_text;
    public RecyclerView recycler_innner;

    public FenLeiRecyclerOutHolder(View itemView) {
        super(itemView);

        recycler_out_text = itemView.findViewById(R.id.recycler_out_text);
        recycler_innner = itemView.findViewById(R.id.recycler_innner);

    }
}
