package bwie.com.jingdong.View.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import bwie.com.jingdong.R;

/**
 * Created by LENOVO on 2018/3/12.
 */

public class MiaoShaHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;

    public MiaoShaHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.miao_sha_image);

    }
}
