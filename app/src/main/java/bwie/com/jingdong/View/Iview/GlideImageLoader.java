package bwie.com.jingdong.View.Iview;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

/**
 * Created by LENOVO on 2018/3/12.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        //Glide 加载图片简单用法
        Glide.with(context).load(path).into(imageView);
    }
}