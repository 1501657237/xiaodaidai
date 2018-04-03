package bwie.com.myapp2.presenter;



import java.util.Map;

import bwie.com.myapp2.model.DrawModel;
import bwie.com.myapp2.view.IView.AndroidMain;
import okhttp3.ResponseBody;

/**
 * Created by ASUS on 2018/4/1.
 */

public class DrawPresenter implements Androidpre {

    private DrawModel drawModel;
    private AndroidMain drawMain;
    public DrawPresenter(AndroidMain drawMain) {
        this.drawMain=drawMain;
        drawModel = new DrawModel(this);
    }

    public void getDate(String drawUrl, Map<String, String> map) {
        drawModel.getDate(drawUrl,map);
    }

    @Override
    public void OnSuccess(ResponseBody responseBody) {
        drawMain.AndroidMainSuccess(responseBody);
    }

    @Override
    public void Error(Throwable throwable) {
        drawMain.Error(throwable);
    }
}
