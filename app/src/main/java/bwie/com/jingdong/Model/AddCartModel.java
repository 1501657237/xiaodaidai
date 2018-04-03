package bwie.com.jingdong.Model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bwie.com.jingdong.Model.bean.AddCartBean;
import bwie.com.jingdong.Presenter.AddCartPresenter;
import bwie.com.jingdong.Presenter.i_presenter.AddCartPresenterInter;
import bwie.com.jingdong.util.OkHttp3Util_03;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by LENOVO on 2018/3/21.
 */

public class AddCartModel {
    private AddCartPresenterInter addCartPresenterInter;

    public AddCartModel(AddCartPresenterInter addCartPresenterInter) {
        this.addCartPresenterInter = addCartPresenterInter;
    }

    public void addToCart(String addCart, String uid, int pid) {

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("pid", String.valueOf(pid));

        OkHttp3Util_03.doPost(addCart, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();

                    final AddCartBean addCartBean = new Gson().fromJson(json,AddCartBean.class);


                            addCartPresenterInter.onCartAddSuccess(addCartBean);


                }
            }
        });

    }
}
