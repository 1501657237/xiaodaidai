package bwie.com.myapp2.model;


import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import bwie.com.myapp2.presenter.Androidpre;
import bwie.com.myapp2.util.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by ASUS on 2018/3/24.
 */

public class HomeModel {
    private Androidpre androidpre;

    public HomeModel(Androidpre androidpre) {
        this.androidpre=androidpre;

    }


    public void getDateUrl(String androidUrl, Map<String, String> map) {
        RetrofitUtil.getService().doGet(androidUrl,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                            androidpre.OnSuccess(responseBody);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                            androidpre.Error(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
