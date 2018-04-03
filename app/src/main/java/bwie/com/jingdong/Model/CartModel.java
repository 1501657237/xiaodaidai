package bwie.com.jingdong.Model;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



import bwie.com.jingdong.Model.bean.CartBean;

import bwie.com.jingdong.Presenter.i_presenter.CartPresenterInter;
import bwie.com.jingdong.util.OkHttp3Util_03;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by LENOVO on 2018/3/21.
 */

public class CartModel {
    private CartPresenterInter cartPresenterInter;

    public CartModel(CartPresenterInter cartPresenterInter) {
        this.cartPresenterInter = cartPresenterInter;
    }
    public void getCartData(String selectCart, String uid) {

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);

        OkHttp3Util_03.doPost(selectCart, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("cartModel",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String json = response.body().string();


                            if ("null".equals(json)) {//购物车为空
                                cartPresenterInter.getCartDataNull();
                            }else {
                                //解析
                                CartBean cartBean = new Gson().fromJson(json,CartBean.class);
                                cartPresenterInter.getCartDataSuccess(cartBean);
                            }




                }
            }
        });

    }
}
