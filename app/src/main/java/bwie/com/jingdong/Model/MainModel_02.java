package bwie.com.jingdong.Model;

import com.google.gson.Gson;

import java.io.IOException;

import bwie.com.jingdong.Model.bean.Pruducts;
import bwie.com.jingdong.Presenter.MainPresenter_SouSuo;
import bwie.com.jingdong.Presenter.i_presenter.IMain_Presenter02;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by LENOVO on 2018/3/22.
 */

public class MainModel_02 {
    private IMain_Presenter02 iMain_presenter02;

    public MainModel_02(IMain_Presenter02 iMain_presenter02) {
        this.iMain_presenter02 = iMain_presenter02;
    }
    //真正获取数据的是model里面的方法
    public void getLoginInModel(String url_select,String keywords){

        OkHttpClient httpClient = new OkHttpClient();
        //创建request对象01
        Request request = new Request.Builder().url(url_select+"?keywords="+keywords+"&page=1&source=android")
                //.post(formBody)//post方式传递请求的参数对象
                .build();

        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){

                    String json = response.body().string();

                    //解析
                    Gson gson = new Gson();
                    Pruducts pruducts = gson.fromJson(json, Pruducts.class);
                    iMain_presenter02.onSuccess(pruducts);

                }


            }
        });



    }
}
