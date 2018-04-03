package bwie.com.jingdong.View.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMShareAPI;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import bwie.com.jingdong.Model.bean.AddCartBean;
import bwie.com.jingdong.Model.bean.DeatilBean;
import bwie.com.jingdong.Presenter.AddCartPresenter;
import bwie.com.jingdong.Presenter.DetailPresenter;
import bwie.com.jingdong.Presenter.i_presenter.DetailActivityInter;
import bwie.com.jingdong.R;
import bwie.com.jingdong.View.Iview.ActivityAddCartInter;
import bwie.com.jingdong.View.Iview.GlideImageLoader;
import bwie.com.jingdong.util.ApiUtil;
import bwie.com.jingdong.util.CommonUtils;
import bwie.com.jingdong.util.ShareUtil;

public class DetailActivity extends AppCompatActivity  implements DetailActivityInter, View.OnClickListener ,ActivityAddCartInter {

    private Banner banner;
    private ImageView image_fan;
    private CheckBox guanzhu;

    private Button btn_fan3;
    private TextView detail_title;
    private TextView detail_bargin_price;
    private TextView detail_yuan_price;
    private DeatilBean deatilBean;
    private ImageView share;
    private DetailPresenter detailPresenter;
    private AddCartPresenter addCartPresenter;
    private int pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        banner = (Banner) findViewById(R.id.image_view);
        image_fan = (ImageView) findViewById(R.id.imageView2);
        guanzhu = (CheckBox) findViewById(R.id.guanzhi);
        share = (ImageView) findViewById(R.id.share);
        btn_fan3 = (Button) findViewById(R.id.btn_fan3);
        detail_title = (TextView) findViewById(R.id.detail_title);
        detail_bargin_price = (TextView) findViewById(R.id.detail_bargin_price);
        detail_yuan_price = (TextView) findViewById(R.id.detail_yuan_price);

        //创建presenter
        detailPresenter = new DetailPresenter(this);
        addCartPresenter = new AddCartPresenter(this);

        pid = getIntent().getIntExtra("pid", -1);
        if(pid !=-1){
            //拿着传递的pid请求商品详情的接口,然后展示数据...MVP
            detailPresenter.getDetailData(ApiUtil.DETAIL_URL, pid);

        }

        //初始化banner
        initBanner();
        //设置点击事件
        image_fan.setOnClickListener(this);
        btn_fan3.setOnClickListener(this);
        guanzhu.setOnClickListener(this);
        share.setOnClickListener(this);
    }

    private void initBanner() {
        //设置banner样式...CIRCLE_INDICATOR_TITLE包含标题
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_fan3://添加购物车...uid...pid

                addCartPresenter.addToCart(ApiUtil.ADD_CART_URL, CommonUtils.getString("uid"),pid);

                break;
            case R.id.imageView2://返回
                //finish() startActivity() setResult()...context.startActiivty()

                //DetailActivity.this.finish();
                finish();
                break;
        /*    case R.id.watch_cart://查看购物车....跳转的是购物车的activity

                Intent intent = new Intent(DetailActivity.this,CartActivity.class);

                startActivity(intent);
                break;*/
            case R.id.guanzhi:
                if (guanzhu.isChecked()) {
                    Toast.makeText(DetailActivity.this, "已关注", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailActivity.this, "未关注", Toast.LENGTH_SHORT).show();
                }
                break;

            //分享
            case R.id.share:

                if (deatilBean != null) {
                    //final Activity activity 分享的activity的上下文,
                    // String WebUrl 分享的商品的链接,
                    // String title 分享的商品的标题,
                    // String description 商品的描述,
                    // String imageUrl 商品的图片...如果没有图片传"",
                    // int imageID 本地商品的图片
                     DeatilBean.DataBean data = deatilBean.getData();
                   ShareUtil.shareWeb(DetailActivity.this,data.getDetailUrl(),data.getTitle(),"我在京东发现一个好的商品,赶紧来看看吧!",data.getImages().split("\\|")[0],R.mipmap.ic_launcher);

                }

                /*new ShareAction(DetailActivity.this)gg
                        .withText("hello")
                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                        .setCallback(shareListener)
                        .open();*/


                break;
        }
    }
    @Override
    public void onSuccess(final DeatilBean deatilBean) {
        this.deatilBean = deatilBean;
        runOnUiThread(new Runnable() {


            @Override
            public void run() {

                //添加删除线
                detail_yuan_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

                //设置数据显示
                detail_title.setText(deatilBean.getData().getTitle());
                detail_bargin_price.setText("优惠价:"+deatilBean.getData().getBargainPrice());
                detail_yuan_price.setText("原价:"+deatilBean.getData().getPrice());

                String[] strings = deatilBean.getData().getImages().split("\\|");

                final ArrayList<String> imageUrls = new ArrayList<>();
                for (int i = 0;i<strings.length;i++){
                    imageUrls.add(strings[i]);
                }
                banner.setImages(imageUrls);

                //bannner点击事件进行跳转
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                       Intent intent = new Intent(DetailActivity.this,ImageScaleActivity.class);
                        //传递的数据...整个轮播图数据的集合需要传递,,,当前展示的图片的位置需要传递postion
                        //intent传递可以传的数据...基本数据类型...引用数据类型(必须序列化,所有的类,包括内部类实现serilizable接口)...bundle
                        intent.putStringArrayListExtra("imageUrls",imageUrls);
                        intent.putExtra("position",position);

                        startActivity(intent);
                    }
                });

                banner.start();

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCartAddSuccess(final AddCartBean addCartBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if("加购成功".equals(addCartBean.getMsg())){

                    Toast.makeText(DetailActivity.this,addCartBean.getMsg(),Toast.LENGTH_SHORT).show();
                }else{

                    Intent intent = new Intent(DetailActivity.this, LoginActivity.class);
                    startActivity(intent);
                }




            }
        });
    }
}
