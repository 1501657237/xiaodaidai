package bwie.com.myapp2.view.activity;

import android.content.Intent;
import android.graphics.drawable.*;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;

import bwie.com.myapp2.R;
import bwie.com.myapp2.application.DashApplication;
import bwie.com.myapp2.gen.HostroyBeanDao;
import bwie.com.myapp2.model.HostroyBean;
import bwie.com.myapp2.view.adapter.HistorySearchAdapter;
import bwie.com.myapp2.widget.EmptyRecyclerView;
import skin.support.SkinCompatManager;
import skin.support.app.SkinCompatDelegate;
import skin.support.content.res.SkinCompatResources;
import skin.support.observe.SkinObservable;
import skin.support.observe.SkinObserver;
import skin.support.widget.SkinCompatThemeUtils;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;
import static skin.support.widget.SkinCompatHelper.checkResourceId;

public class Select extends AppCompatActivity implements SkinObserver {

    private FlexboxLayout mFlexboxLayout;
    private ImageView mIvDeleteAll;
    private ImageView mIvBack;
    private TextView mTvHotSearch;
    private RelativeLayout mLayoutHistory;
    private LinearLayout mLayoutLoadMore;
    private EditText mEtSearch;
    private TextView mTvSearch;
    private List<String> mHotTitles = new ArrayList<String>();
    private List<String> mHistoryTitles = new ArrayList<String>();
    private String mKeywords;
    private HistorySearchAdapter mHistorySearchAdapter;
    private EmptyRecyclerView mRecyclerViewHistory;
    private final String HISTORY_SEARCH = "history_search";
    private HostroyBeanDao hostroyBeanDao;
    private SkinCompatDelegate mSkinDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        hostroyBeanDao = DashApplication.getInstances().getDaoSession().getHostroyBeanDao();
        mRecyclerViewHistory = (EmptyRecyclerView) findViewById(R.id.recyclerview_history);
        mFlexboxLayout = (FlexboxLayout) findViewById(R.id.flexbox_layout);
        mIvDeleteAll = (ImageView) findViewById(R.id.iv_deleteall);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvHotSearch = (TextView) findViewById(R.id.tv_hotsearch);
        mLayoutHistory = (RelativeLayout) findViewById(R.id.layout_history);
        mLayoutLoadMore = (LinearLayout) findViewById(R.id.layout_loadmore);
        mEtSearch = (EditText) findViewById(R.id.et_search);
        mTvSearch = (TextView) findViewById(R.id.tv_search);
        mIvDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hostroyBeanDao.deleteAll();
                setHistroyAdapter();
            }
        });
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mKeywords = mEtSearch.getText().toString().trim();
                if (mKeywords != null) {
                    mHistoryTitles.add(mKeywords);
                    HostroyBean hostroyBean = new HostroyBean();
                    hostroyBean.setName(mKeywords);
                    hostroyBeanDao.insert(hostroyBean);
                    Intent intent = new Intent(Select.this, Goods.class);
                    intent.putExtra("mKeywords", mKeywords);
                    startActivity(intent);
                } else {
                    Toast.makeText(Select.this, "搜索框不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });
        updateStatusBarColor();
        updateWindowBackground();
        initTag();
    }


    private void initTag() {
        mHotTitles.add("RxJava");
        mHotTitles.add("RxAndroid");
        mHotTitles.add("数据库");
        mHotTitles.add("自定义控件");
        mHotTitles.add("下拉刷新");
        mHotTitles.add("mvp");
        mHotTitles.add("直播");
        mHotTitles.add("权限管理");
        mHotTitles.add("Retrofit");
        mHotTitles.add("OkHttp");
        mHotTitles.add("WebWiew");
        mHotTitles.add("热修复");
        for (int i = 0; i < mHotTitles.size(); i++) {
            TextView textView = new TextView(this);
            textView.setBackgroundResource(R.drawable.flexbox_text_bg);
            textView.setText(mHotTitles.get(i));
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(30, 30, 30, 30);
            textView.setClickable(true);
            textView.setFocusable(true);
            textView.setTextColor(getResources().getColor(R.color.flexbox_text_color));
            mFlexboxLayout.addView(textView);
            //通过FlexboxLayout.LayoutParams 设置子元素支持的属性
            ViewGroup.LayoutParams params = textView.getLayoutParams();
            if (params instanceof FlexboxLayout.LayoutParams) {
                FlexboxLayout.LayoutParams layoutParams = (FlexboxLayout.LayoutParams) params;
                //layoutParams.setFlexBasisPercent(0.5f);
                layoutParams.setMargins(10, 10, 20, 10);
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //

                    TextView tv = (TextView) v;
                    mHistoryTitles.add(tv.getText().toString().trim());
                    mKeywords = tv.getText().toString().trim();

                    HostroyBean hostroyBean = new HostroyBean();
                    hostroyBean.setName(mKeywords);
                    hostroyBeanDao.insert(hostroyBean);

                    Intent intent = new Intent(Select.this, Goods.class);
                    intent.putExtra("mKeywords", mKeywords);


                    startActivity(intent);
                }
            });
        }
    }


    private void setHistroyAdapter() {
        final List<HostroyBean> hostroyBeen = hostroyBeanDao.loadAll();
        if (hostroyBeen != null) {

            mRecyclerViewHistory.setVisibility(View.VISIBLE);
            mHistorySearchAdapter = new HistorySearchAdapter(this, hostroyBeen);
            mRecyclerViewHistory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            mRecyclerViewHistory.setAdapter(mHistorySearchAdapter);

            mHistorySearchAdapter.setOnItemClickLsetener(new HistorySearchAdapter.OnItemClickLsetener() {
                @Override
                public void itemClick(int position) {
                    hostroyBeanDao.deleteByKey(hostroyBeen.get(position).getId());
                    setHistroyAdapter();
                }
            });

        } else {
            mRecyclerViewHistory.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SkinCompatManager.getInstance().deleteObserver(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setHistroyAdapter();
        SkinCompatManager.getInstance().addObserver(this);
    }
    @NonNull
    public SkinCompatDelegate getSkinDelegate() {
        if (mSkinDelegate == null) {
            mSkinDelegate = SkinCompatDelegate.create(this);
        }
        return mSkinDelegate;
    }



    /**
     * @return true: 打开5.0以上状态栏换肤, false: 关闭5.0以上状态栏换肤;
     */
    protected boolean skinStatusBarColorEnable() {
        return true;
    }

    protected void updateStatusBarColor() {
        if (skinStatusBarColorEnable() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int statusBarColorResId = SkinCompatThemeUtils.getStatusBarColorResId(this);
            int colorPrimaryDarkResId = SkinCompatThemeUtils.getColorPrimaryDarkResId(this);
            if (checkResourceId(statusBarColorResId) != INVALID_ID) {
                getWindow().setStatusBarColor(SkinCompatResources.getInstance().getColor(statusBarColorResId));
            } else if (checkResourceId(colorPrimaryDarkResId) != INVALID_ID) {
                getWindow().setStatusBarColor(SkinCompatResources.getInstance().getColor(colorPrimaryDarkResId));
            }
        }
    }

    protected void updateWindowBackground() {
        int windowBackgroundResId = SkinCompatThemeUtils.getWindowBackgroundResId(this);
        if (checkResourceId(windowBackgroundResId) != INVALID_ID) {
            String typeName = getResources().getResourceTypeName(windowBackgroundResId);
            if ("color".equals(typeName)) {
                android.graphics.drawable.Drawable drawable =  new ColorDrawable(SkinCompatResources.getInstance().getColor(windowBackgroundResId));
                getWindow().setBackgroundDrawable(drawable);
            } else if ("drawable".equals(typeName)) {
                android.graphics.drawable.Drawable drawable = SkinCompatResources.getInstance().getDrawable(windowBackgroundResId);
                getWindow().setBackgroundDrawable(drawable);
            } else if ("mipmap".equals(typeName)) {
                android.graphics.drawable.Drawable drawable = SkinCompatResources.getInstance().getMipmap(windowBackgroundResId);
                getWindow().setBackgroundDrawable(drawable);
            }
        }
    }

    @Override
    public void updateSkin(SkinObservable observable, Object o) {
        updateStatusBarColor();
        updateWindowBackground();
        getSkinDelegate().applySkin();
    }


}
