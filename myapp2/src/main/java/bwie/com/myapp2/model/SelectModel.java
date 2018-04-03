package bwie.com.myapp2.model;


import com.google.gson.Gson;

import java.io.IOException;

import bwie.com.myapp2.presenter.Selectpre;
import bwie.com.myapp2.util.OkHttp3Util_03;
import bwie.com.myapp2.view.bean.GoodsBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ASUS on 2018/3/27.
 */

public class SelectModel {
    private Selectpre selectpre;

    public SelectModel(Selectpre selectpre) {
        this.selectpre=selectpre;

    }

    public void getDateUrl(String url) {
        OkHttp3Util_03.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()){
                        String json = response.body().string();
                        Gson gson = new Gson();
                        GoodsBean goodsBean = gson.fromJson(json, GoodsBean.class);
                        selectpre.OnSuccess(goodsBean);
                    }
            }
        });
    }
}
