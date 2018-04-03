package bwie.com.myapp2.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ASUS on 2018/3/30.
 */

public class WangLuoUtil {
    public int getNetState(Context context){
        int netState = -1;
        // 获取android系统提供的服务, 转换成链接管理类,这个类专门处理链接相关的东西
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
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
}
