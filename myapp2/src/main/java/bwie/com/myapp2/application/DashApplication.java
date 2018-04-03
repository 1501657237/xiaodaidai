package bwie.com.myapp2.application;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Process;


import com.facebook.drawee.backends.pipeline.Fresco;

import bwie.com.myapp2.gen.DaoMaster;
import bwie.com.myapp2.gen.DaoSession;
import skin.support.SkinCompatManager;
import skin.support.design.app.SkinMaterialViewInflater;


/**
 * Created by Dash on 2018/1/23.
 */
public class DashApplication extends Application {

    private static int myTid;
    private static Handler handler;
    private static Context context;

    private static DashApplication instances;//整个应用的上下文
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        myTid = Process.myTid();
        handler = new Handler();
        context = getApplicationContext();
        Fresco.initialize(this);
        setDatabase();
        SkinCompatManager.init(this)                         // 基础控件换肤初始化
                .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
                .loadSkin();

    }


    //获取主线程的id
    public static int getMainThreadId() {

        return myTid;
    }

    //获取handler
    public static Handler getAppHanler() {
        return handler;
    }

    public static Context getAppContext() {
        return context;
    }

    private void setDatabase() {

        mHelper = new DaoMaster.DevOpenHelper(this, "bawei", null);//string便是数据库的名字
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();

    }

    /**
     * 对外提供获取seesion的方法
     *
     * @return
     */
    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    /**
     * 对外提供获取数据库的方法
     *
     * @return
     */
    public SQLiteDatabase getDb() {
        return db;
    }


    /**
     * 对外地宫返回上下文的方法
     *
     * @return
     */
    public static DashApplication getInstances() {
        return instances;
    }
}
