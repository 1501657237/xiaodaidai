package bwie.com.jingdong.Model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bwie.com.jingdong.Presenter.i_presenter.OrderListPresenterInter;
import bwie.com.jingdong.util.OkHttp3Util_03;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by LENOVO on 2018/3/25.
 */

public class OrderListModel {
    private OrderListPresenterInter orderListPresenterInter;

    public OrderListModel(OrderListPresenterInter orderListPresenterInter) {
        this.orderListPresenterInter = orderListPresenterInter;
    }

    public void getOrderData(String orderListUrl, String uid, int page) {

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("page", String.valueOf(page));

        OkHttp3Util_03.doPost(orderListUrl, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                    String json = response.body().string();

                    final OrderListBean orderListBean = new Gson().fromJson(json,OrderListBean.class);

                            orderListPresenterInter.onOrderDataSuccess(orderListBean);


                }
            }
        });

    }
}
