package bwie.com.jingdong.Application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.socialize.PlatformConfig;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;


/**
 * Created by LENOVO on 2018/3/12.
 */

public class App extends Application {
    private static int myTid;
    private static Handler handler;
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        ZXingLibrary.initDisplayOpinion(this);


        myTid = Process.myTid();
        handler = new Handler();
        context = getApplicationContext();
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
    {

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad",         "http://sns.whalecloud.com");
    }
}
