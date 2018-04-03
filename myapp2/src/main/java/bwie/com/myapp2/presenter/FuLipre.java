package bwie.com.myapp2.presenter;



import okhttp3.ResponseBody;

/**
 * Created by ASUS on 2018/3/25.
 */

public interface FuLipre {
    void OnSuccess(ResponseBody responseBody);
    void Error(Throwable throwable);
}
