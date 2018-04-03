package bwie.com.jingdong.View.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gongwen.marqueen.SimpleMF;
import com.gongwen.marqueen.SimpleMarqueeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bwie.com.jingdong.Model.bean.FenLeiBean;
import bwie.com.jingdong.Model.bean.HomeBean;
import bwie.com.jingdong.Presenter.FragmentHomeP;
import bwie.com.jingdong.R;
import bwie.com.jingdong.View.Adapter.HengXiangAdapter;
import bwie.com.jingdong.View.Adapter.MiaoShaAdapter;
import bwie.com.jingdong.View.Adapter.TuiJianAdapter;
import bwie.com.jingdong.View.Iview.GlideImageLoader;
import bwie.com.jingdong.View.Iview.InterFragmentHome;
import bwie.com.jingdong.View.Iview.ObservableScrollView;
import bwie.com.jingdong.View.Iview.OnItemListner;
import bwie.com.jingdong.View.activity.DetailActivity;
import bwie.com.jingdong.View.activity.SouSuoActivity;
import bwie.com.jingdong.View.activity.WebViewActivity;
import bwie.com.jingdong.View.activity.erWerMaActivity;
import bwie.com.jingdong.util.ApiUtil;

/**
 * Created by LENOVO on 2018/3/12.
 */

public class FragmentHome extends Fragment implements InterFragmentHome {

    private  int REQUEST_CODE = 1001;
    //使用handler用于更新UI
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            countDown();
            sendEmptyMessageDelayed(0, 1000);
        }
    };
    private TextView tv_miaosha;
    private TextView mMiaoshaTimeTv;
    private View view;
    private TextView mMiaoshaMinterTv;
    private TextView mMiaoshaShiTv;
    private TextView mMiaoshaSecondTv;
    private FragmentHomeP fragmentHomeP;
    private int imageHeight=300; //设置渐变高度，一般为导航图片高度，自己控制
    private Banner banner;
    private SimpleMarqueeView simpleMarqueeView;
    private SmartRefreshLayout smart_refresh;
    private RecyclerView miao_sha_recycler;
    private RecyclerView heng_xiang;
    private RecyclerView tuijian_recycler;
    private LinearLayout line;
    private RelativeLayout edit_view;
    private ObservableScrollView scrollView;
    private final String TAG_MARGIN_ADDED = "marginAdded";

    private ImageView iv_xx;
    private TextView tv_erweima;
    private ImageView erweima;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //主要在oncreateView中做的操作是找到控件...
        View view = inflater.inflate(R.layout.fragment_home_layout,container,false);
        tv_miaosha = (TextView) view.findViewById(R.id.tv_miaosha);
        banner = (Banner) view.findViewById(R.id.banner);
        simpleMarqueeView = (SimpleMarqueeView) view.findViewById(R.id.simpleMarqueeView);
        mMiaoshaTimeTv = (TextView) view.findViewById(R.id.tv_miaosha_time);
        mMiaoshaMinterTv = (TextView) view.findViewById(R.id.tv_miaosha_minter);
        mMiaoshaShiTv = (TextView) view.findViewById(R.id.tv_miaosha_shi);
        mMiaoshaSecondTv = (TextView) view.findViewById(R.id.tv_miaosha_second);
        smart_refresh = (SmartRefreshLayout)view.findViewById(R.id.smart_refresh);
        miao_sha_recycler = (RecyclerView)view.findViewById(R.id.miao_sha_recycler);
        heng_xiang = (RecyclerView)view.findViewById(R.id.heng_xiang_recycler);
        tuijian_recycler = (RecyclerView) view.findViewById(R.id.tui_jian_recycler);
        line = (LinearLayout) view.findViewById(R.id.line);
        edit_view = (RelativeLayout) view.findViewById(R.id.edit_view);
        scrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);


        erweima = (ImageView) view.findViewById(R.id.erweima);
        tv_erweima = (TextView) view.findViewById(R.id.tv_erweima);
        iv_xx = (ImageView) view.findViewById(R.id.iv_xx);


        return view;
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       // ChenJinUtil.setStatusBarColor(getActivity(), Color.TRANSPARENT);


        //标题栏
        initTitleBar();


        //在这里可以做其他操作....
        fragmentHomeP = new FragmentHomeP(this);
        //调用p层里面的方法....https://www.zhaoapi.cn/ad/getAd
        fragmentHomeP.getNetData(ApiUtil.HOME_URL);



        //一个view一般是对应着一个presenter和一个model;;;;逻辑比较复杂的页面可能会对应多个presenter和多个model
        fragmentHomeP.getFenLeiData(ApiUtil.FEN_LEI_URL);




        startCountDown();

        //初始化banner
        initBanner();

        //初始化跑马灯
        initMarqueeView();

        smart_refresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                smart_refresh.finishLoadmore(2000);
            }
        });
        smart_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                smart_refresh.finishRefresh(2000);
            }
        });
        erweima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), erWerMaActivity.class);
                startActivityForResult(intent, 2);
            }
        });



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
/**
 * 处理二维码扫描结果
 */
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == 2) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);

                    Toast.makeText(getActivity(), "解析结果:" + result+"****", Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    private void initTitleBar() {
        //搜索框点击方式
        edit_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(getActivity(),SouSuoActivity.class));
                getActivity().overridePendingTransition(R.anim.rightin, R.anim.leftout);

            }
        });


        //搜索框在布局最上面
        line.bringToFront();
        scrollView.setScrollViewListener(new ObservableScrollView.IScrollViewListener() {
            @Override
            public void onScrollChanged(int x, int y, int oldl, int oldt) {
                if (y <= 0) {
                    erweima.setImageResource(R.drawable.a_s);
                    iv_xx.setImageResource(R.drawable.a9x);
                    line.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
                    tv_erweima.setTextColor(Color.argb((int) 250,250, 250, 250));
                } else if (y > 0 && y <= imageHeight) {
                    float scale = (float) y / imageHeight;
                    float alpha = (255 * scale);
                    // 只是layout背景透明
                    erweima.setImageResource(R.drawable.sao_hei);
                    iv_xx.setImageResource(R.drawable.my_msg_hei);
                    tv_erweima.setTextColor(Color.parseColor("#000000"));
                    line.setBackgroundColor(Color.argb((int) alpha, 250, 250, 250));
                } else {

                    line.setBackgroundColor(Color.argb((int) 250, 250, 250, 250));
                    //  erweima.setBackgroundResource(R.drawable.sao_kind);
                    //  erweima.setImageResource(R.drawable.sao_hei);

                    // tv_erweima.setTextColor(Color.argb((int) 1, 250, 0, 250));

                }


            }
        });





    }





    private void initMarqueeView() {
        List<String> datas = new ArrayList<>();
        datas.add("欢迎访问京东app");
        datas.add("天若有情天易老");
        datas.add("有情之人总难圆");
        datas.add("心事空流转思念渐渐倾斜");
        SimpleMF<String> marqueeFactory = new SimpleMF(getActivity());

        marqueeFactory.setData(datas);
        simpleMarqueeView.setMarqueeFactory(marqueeFactory);
        simpleMarqueeView.startFlipping();



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

    private void startCountDown() {
        handler.sendEmptyMessage(0);
    }

    /**
     * 秒杀
     */
    private void countDown() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String format = df.format(curDate);
        StringBuffer buffer = new StringBuffer();
        String substring = format.substring(0, 11);
        buffer.append(substring);
        Log.d("ccc", substring);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour % 2 == 0) {
            mMiaoshaTimeTv.setText(hour+ "点场");
            buffer.append((hour + 2));
            buffer.append(":00:00");
        } else {
            mMiaoshaTimeTv.setText((hour - 1) + "点场");
            buffer.append((hour + 1));
            buffer.append(":00:00");
        }
        String totime = buffer.toString();
        try {
            java.util.Date date = df.parse(totime);
            java.util.Date date1 = df.parse(format);
            long defferenttime = date.getTime() - date1.getTime();
            long days = defferenttime / (1000 * 60 * 60 * 24);
            long hours = (defferenttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minute = (defferenttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long seconds = defferenttime % 60000;
            long second = Math.round((float) seconds / 1000);
            mMiaoshaShiTv.setText("0" + hours + "");
            if (minute >= 10) {
                mMiaoshaMinterTv.setText(minute + "");
            } else {
                mMiaoshaMinterTv.setText("0" + minute + "");
            }
            if (second >= 10) {
                mMiaoshaSecondTv.setText(second + "");
            } else {
                mMiaoshaSecondTv.setText("0" + second + "");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onSuccess(final HomeBean homeBean) {

     getActivity().runOnUiThread(
             new Runnable() {
                 @Override
                 public void run() {
                     //设置显示bannner
                     List<HomeBean.DataBean> datas = homeBean.getData();

                     List<String> imageUrls = new ArrayList<>();
                     for (int i = 0;i<datas.size();i++){
                         imageUrls.add(datas.get(i).getIcon());
                     }

                     banner.setImages(imageUrls);

                     //banner的点击事件5
                     banner.setOnBannerListener(new OnBannerListener() {
                         @Override
                         public void OnBannerClick(int position) {

                             HomeBean.DataBean dataBean = homeBean.getData().get(position);
                             if (dataBean.getType() == 0) {
                                 //跳转webView
                                Intent intent = new Intent(getActivity(),WebViewActivity.class);

                                 intent.putExtra("detailUrl",dataBean.getUrl());
                                 startActivity(intent);

                             }else {
                                 Toast.makeText(getActivity(),"即将跳转商品详情",Toast.LENGTH_SHORT).show();
                             }
                         }
                     });

                     //放在最后进行start 否则点击事件会有问题
                     banner.start();

                     //秒杀的数据
                     miao_sha_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), OrientationHelper.HORIZONTAL,false));
                     MiaoShaAdapter miaoShaAdapter = new MiaoShaAdapter(getActivity(), homeBean.getMiaosha());
                     miao_sha_recycler.setAdapter(miaoShaAdapter);
                     //条目的点击事件
                     miaoShaAdapter.setOnItemListner(new OnItemListner() {
                         @Override
                         public void onItemClick(int position) {
                             //跳转到下一个页面....传值过去...webView页面
                            Intent intent = new Intent(getActivity(), WebViewActivity.class);

                             String detailUrl = homeBean.getMiaosha().getList().get(position).getDetailUrl();
                             intent.putExtra("detailUrl",detailUrl);
                             startActivity(intent);

                         }

                         @Override
                         public void onItemLongClick(int position) {

                         }
                     });

                     //瀑布流
                     tuijian_recycler.setLayoutManager(new StaggeredGridLayoutManager(2,OrientationHelper.VERTICAL));
                     //为你推荐设置适配器
                     TuiJianAdapter tuiJianAdapter = new TuiJianAdapter(getActivity(), homeBean.getTuijian());
                     tuijian_recycler.setAdapter(tuiJianAdapter);

                     //为你推荐的点击事件
                     tuiJianAdapter.setOnItemListner(new OnItemListner() {
                         @Override
                         public void onItemClick(int position) {

                             //跳转的是自己的详情页面
                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                             //传递pid
                             intent.putExtra("pid",homeBean.getTuijian().getList().get(position).getPid());
                             startActivity(intent);

                         }

                         @Override
                         public void onItemLongClick(int position) {

                         }
                     });


                 }
             }

     );

    }



    @Override
    public void onFenLeiDataSuccess(final FenLeiBean fenLeiBean) {
         getActivity().runOnUiThread(new Runnable() {
             @Override
             public void run() {
                 //在这里拿到了分类的数据


                 //如果要展示数据的话必须先设置布局管理器....上下文,,,表示几行或者几列,,,表示方向,水平或者竖直,,,是否反转布局展示
                 heng_xiang.setLayoutManager(new GridLayoutManager(getActivity(),2, OrientationHelper.HORIZONTAL,false));

                 HengXiangAdapter hengXiangAdapter = new HengXiangAdapter(getActivity(), fenLeiBean);
                 //设置适配器
                 heng_xiang.setAdapter(hengXiangAdapter);

                 //设置条目的点击事件...实际是适配器的点击事件
                 hengXiangAdapter.setOnItemListner(new OnItemListner() {
                     @Override
                     public void onItemClick(int position) {
                         Toast.makeText(getActivity(),"点击事件执行",Toast.LENGTH_SHORT).show();
                     }

                     @Override
                     public void onItemLongClick(int position) {
                         Toast.makeText(getActivity(),"长按事件执行",Toast.LENGTH_SHORT).show();

                     }
                 });

             }
         });


    }
}
