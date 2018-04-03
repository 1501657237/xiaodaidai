package bwie.com.jingdong.Model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bwie.com.jingdong.Model.bean.DefaultAddrBean;
import bwie.com.jingdong.Presenter.i_presenter.GetDefaultAddrPresenterInter;
import bwie.com.jingdong.util.CommonUtils;
import bwie.com.jingdong.util.OkHttp3Util_03;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by LENOVO on 2018/3/25.
 */

public class GetDefaultAddrModel {
    private GetDefaultAddrPresenterInter getDefaultAddrPresenterInter;

    public GetDefaultAddrModel(GetDefaultAddrPresenterInter getDefaultAddrPresenterInter) {
        this.getDefaultAddrPresenterInter = getDefaultAddrPresenterInter;
    }

    public void getDefaultAddr(String getDefaultAddrUrl, String uid) {

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);

        OkHttp3Util_03.doPost(getDefaultAddrUrl, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String json = response.body().string();//1M


                            if ("{}".equals(json)) {//没有地址数据
                                getDefaultAddrPresenterInter.onGetDefaultAddrEmpty();

                            }else {
                                DefaultAddrBean defaultAddrBean = new Gson().fromJson(json,DefaultAddrBean.class);

                                getDefaultAddrPresenterInter.onGetDefaultAddrSuccess(defaultAddrBean);

                            }




                }
            }
        });

    }
}
