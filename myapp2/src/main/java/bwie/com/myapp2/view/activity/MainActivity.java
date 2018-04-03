package bwie.com.myapp2.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.transition.Fade;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;


import java.util.ArrayList;

import bwie.com.myapp2.R;
import bwie.com.myapp2.myviewpager.NoScrollViewPager;
import bwie.com.myapp2.util.BottomNavigationViewHelper;
import bwie.com.myapp2.util.Constant;
import bwie.com.myapp2.view.adapter.HomeAdapter;
import bwie.com.myapp2.view.fragment.Fragment_Home;
import bwie.com.myapp2.view.fragment.Fragment_Me;
import bwie.com.myapp2.view.fragment.Fragment_Read;
import skin.support.SkinCompatManager;
import skin.support.app.SkinCompatDelegate;
import skin.support.content.res.SkinCompatResources;
import skin.support.observe.SkinObservable;
import skin.support.observe.SkinObserver;
import skin.support.widget.SkinCompatThemeUtils;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;
import static skin.support.widget.SkinCompatHelper.checkResourceId;

public class MainActivity extends AppCompatActivity implements SkinObserver ,NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawlayout;
    private ArrayList<Fragment> mList;
    private NavigationView mNavigationView;
    private BottomNavigationView mBottomNavigation;
    private NoScrollViewPager vp;
    private MenuItem menuItem;
    private Menu mMenu;
    private SkinCompatDelegate mSkinDelegate;
    private RelativeLayout mBtnCheckNetWork;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == -1){
                mBtnCheckNetWork.setVisibility(View.VISIBLE);
                mBtnCheckNetWork.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent("android.settings.WIRELESS_SETTINGS");
                        startActivity(intent);
                    }
                });
            }else{
                mBtnCheckNetWork.setVisibility(View.GONE);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
        initData();
        initToolBar();
        startThread();
        mBtnCheckNetWork = (RelativeLayout) findViewById(R.id.btn_network_status);
        mBottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_bar);
        drawlayout = (DrawerLayout) findViewById(R.id.drawlayout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        vp = (NoScrollViewPager) findViewById(R.id.vp);
        vp.setAdapter(new HomeAdapter(getSupportFragmentManager(),mList));
        mList=new ArrayList<>();
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigation);
        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.navigation_read:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.navigation_me:
                        vp.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    if (menuItem!=null){
                        menuItem.setChecked(false);
                    }else {
                        mBottomNavigation.getMenu().getItem(0).setChecked(false);
                    }
                    menuItem=mBottomNavigation.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void initData() {
        mList = new ArrayList<>();
        mList.add(new Fragment_Home());
        mList.add(new Fragment_Read());
        mList.add(new Fragment_Me());
    }

    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(10000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(fade);
        }
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar, menu);
        menu.findItem(R.id.action_edit).setVisible(true);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            startActivity(new Intent(this, Select.class));
        }
        if (item.getItemId() == R.id.action_edit) {
            startActivity(new Intent(this, BianJi.class));
        }
        return true;
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        String categroy = Constant.CATEGORY_ALL;
        switch (id) {
            case R.id.menu_draw_client:
                categroy = Constant.CATEGORY_CLIENT;
                Intent intent = new Intent(this, Drawable.class);
                intent.putExtra("categroy",categroy);
                startActivity(intent);
                break;
            case R.id.menu_draw_recommend:
                categroy = Constant.CATEGROY_RECOMMEND;
                intent = new Intent(this, Drawable.class);
                intent.putExtra("categroy",categroy);
                startActivity(intent);
                break;
            case R.id.menu_draw_app:
                categroy = Constant.CATEGORY_APP;
                intent = new Intent(this, Drawable.class);
                intent.putExtra("categroy",categroy);
                startActivity(intent);
                break;
            case R.id.menu_draw_expandresource:
                categroy = Constant.CATEGORY_EXPANDRESOURCE;
                intent = new Intent(this, Drawable.class);
                intent.putExtra("categroy",categroy);
                startActivity(intent);
                break;
            case R.id.menu_draw_video:
                categroy = Constant.CATEGORY_VIDEO;
                intent = new Intent(this, Drawable.class);
                intent.putExtra("categroy",categroy);
                startActivity(intent);
                break;
            case R.id.menu_draw_theme:
                intent = new Intent(this, ThemeActivity.class);
                startActivity(intent);
                break;
        }
        item.setCheckable(true);
        drawlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawlayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @NonNull
    public SkinCompatDelegate getSkinDelegate() {
        if (mSkinDelegate == null) {
            mSkinDelegate = SkinCompatDelegate.create(this);
        }
        return mSkinDelegate;
    }

    @Override
    protected void onResume() {
        super.onResume();
        SkinCompatManager.getInstance().addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinCompatManager.getInstance().deleteObserver(this);
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
                android.graphics.drawable.Drawable drawable = new ColorDrawable(SkinCompatResources.getInstance().getColor(windowBackgroundResId));
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

    public int getNetState(){
        int netState = -1;
        // 获取android系统提供的服务, 转换成链接管理类,这个类专门处理链接相关的东西
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(this.CONNECTIVITY_SERVICE);
        // NetworkInfo封装了网络链接的信息
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        if(activeNetworkInfo==null){
            return netState;
        }
        // 获取网络类型 返回值int
        int type = activeNetworkInfo.getType();
        if(type == ConnectivityManager.TYPE_MOBILE){ // TYPE_MOBILE为蜂窝网络
            netState = 0;

        }else if(type == ConnectivityManager.TYPE_WIFI){ // TYPE_WIFI为wifi网络
            netState = 1;
        }
        return netState;
    }

    
    public void startThread(){

        new Thread( new Runnable(){
            @Override
            public void run() {
                while (true){
                    SystemClock.sleep(2000);
                    handler.sendEmptyMessage(getNetState());
                }
            }
        }).start();

    }

}



