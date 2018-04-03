package bwie.com.myapp2.model;



import java.util.Map;

import bwie.com.myapp2.presenter.Androidpre;
import bwie.com.myapp2.util.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by ASUS on 2018/4/1.
 */

public class DrawModel {
    private  Androidpre drawPre;

    public DrawModel(Androidpre drawPre) {
        this.drawPre=drawPre;

    }

    public void getDate(String drawUrl, Map<String, String> map) {
        RetrofitUtil.getService().doGet(drawUrl,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                            drawPre.OnSuccess(responseBody);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                            drawPre.Error(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
