package bwie.com.myapp2.util;

import android.util.Log;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dash on 2018/3/13.
 */
public class RetrofitUtil {

    private static RetrofitUtil retrofitUtil = null;
    private Retrofit retrofit;

    private RetrofitUtil(String baseUrl) {


        //创建retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//添加转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//设置支持rxjava 注意是2
                .build();
    }

    /**
     * 单例模式
     * @return
     */
    public static RetrofitUtil getInstance(String baseUrl) {
        if (retrofitUtil == null) {
            synchronized (RetrofitUtil.class) {
                if (retrofitUtil == null) {
                    retrofitUtil = new RetrofitUtil(baseUrl);
                }
            }
        }

        return retrofitUtil;
    }

    public <T> T createService(Class<T> service) {
        return retrofit.create(service);
    }

    public static ApiService getService() {
        return RetrofitUtil.getInstance(JieKou.BASE_URL).createService(ApiService.class);
    }
}
