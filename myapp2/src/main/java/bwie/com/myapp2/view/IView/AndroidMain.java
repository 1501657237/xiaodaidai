package bwie.com.myapp2.view.IView;



import okhttp3.ResponseBody;

/**
 * Created by ASUS on 2018/3/25.
 */

public interface AndroidMain {
    void AndroidMainSuccess(ResponseBody responseBody);
    void Error(Throwable throwable);
}
