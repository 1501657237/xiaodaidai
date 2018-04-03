package bwie.com.myapp2.presenter;

import android.util.Log;



import java.util.Map;

import bwie.com.myapp2.model.FuLiModel;
import bwie.com.myapp2.view.IView.FuLiMain;
import okhttp3.ResponseBody;

/**
 * Created by ASUS on 2018/3/25.
 */

public class FuliPresenter implements FuLipre {

    private FuLiMain fuLiMain;
    private FuLiModel fuLiModel;

    public FuliPresenter(FuLiMain fuLiMain) {
        this.fuLiMain=fuLiMain;
        fuLiModel = new FuLiModel(this);
    }


    public void getDate(String fuliUrl, Map<String, String> map) {
        fuLiModel.getDateUrl(fuliUrl,map);
    }

    @Override
    public void OnSuccess(ResponseBody responseBody) {
        fuLiMain.FuLiMainSuccess(responseBody);
    }

    @Override
    public void Error(Throwable throwable) {
        fuLiMain.Error(throwable);
    }
}
