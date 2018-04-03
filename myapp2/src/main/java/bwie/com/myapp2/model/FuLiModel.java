package bwie.com.myapp2.model;

import android.util.Log;


import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bwie.com.myapp2.presenter.FuLipre;
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
 * Created by ASUS on 2018/3/25.
 */

public class FuLiModel {
    private FuLipre fuLipre;

    public FuLiModel(FuLipre fuLipre) {
        this.fuLipre = fuLipre;

    }


    public void getDateUrl(String fuliUrl, Map<String, String> map) {
        RetrofitUtil.getService().doGet(fuliUrl,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                            fuLipre.OnSuccess(responseBody);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                            fuLipre.Error(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
