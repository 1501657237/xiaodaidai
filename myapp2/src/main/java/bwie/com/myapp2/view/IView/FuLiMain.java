package bwie.com.myapp2.view.IView;



import okhttp3.ResponseBody;

/**
 * Created by ASUS on 2018/3/25.
 */

public interface FuLiMain {
    void FuLiMainSuccess(ResponseBody responseBody);
    void Error(Throwable throwable);
}
