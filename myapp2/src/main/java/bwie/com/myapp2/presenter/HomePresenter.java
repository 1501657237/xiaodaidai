package bwie.com.myapp2.presenter;



import java.util.Map;

import bwie.com.myapp2.model.HomeModel;
import bwie.com.myapp2.view.IView.AndroidMain;
import okhttp3.ResponseBody;

/**
 * Created by ASUS on 2018/3/24.
 */

public class HomePresenter implements Androidpre {

    private AndroidMain androidMain;
    private HomeModel homeModel;

    public HomePresenter(AndroidMain androidMain) {
        this.androidMain=androidMain;
        homeModel = new HomeModel(this);
    }


    public void getDate(String androidUrl, Map<String, String> map) {
        homeModel.getDateUrl(androidUrl,map);
    }

    @Override
    public void OnSuccess(ResponseBody responseBody) {
        androidMain.AndroidMainSuccess(responseBody);
    }

    @Override
    public void Error(Throwable throwable) {
        androidMain.Error(throwable);
    }
}
