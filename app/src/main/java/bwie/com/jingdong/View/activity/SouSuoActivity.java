package bwie.com.jingdong.View.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bwie.com.jingdong.Model.bean.FenLeiBean;
import bwie.com.jingdong.Model.bean.HomeBean;
import bwie.com.jingdong.Model.bean.Pruducts;
import bwie.com.jingdong.Presenter.FragmentHomeP;
import bwie.com.jingdong.Presenter.MainPresenter_SouSuo;
import bwie.com.jingdong.R;
import bwie.com.jingdong.View.Adapter.SouSuo_fenlei_adapter;
import bwie.com.jingdong.View.Adapter.listAdapter;
import bwie.com.jingdong.View.Iview.IMain_Activity_02;
import bwie.com.jingdong.View.Iview.InterFragmentHome;
import bwie.com.jingdong.View.SQL.SqlDao;
import bwie.com.jingdong.util.ApiUtil;

/**
 * Created by LENOVO on 2018/3/22.
 */

public class SouSuoActivity extends AppCompatActivity  implements IMain_Activity_02,InterFragmentHome {
    private TextView btn_fan;
    private RecyclerView recycler_resou;
    private ListView listView;

    private EditText ed_sousuo;
    private SqlDao sqlDao;
    private listAdapter adapter;
    private List<String> list=new ArrayList<>();
   // private MainPresenter_02 mainPresenter_02;
    private String name;
    private Button button;
    private FragmentHomeP fragmentHomeP;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_suo);

        btn_fan = (TextView) findViewById(R.id.btn_fan);
        recycler_resou = (RecyclerView) findViewById(R.id.recycler_resou);
        listView = (ListView) findViewById(R.id.list_view);
        ed_sousuo = (EditText) findViewById(R.id.ed_sousuo1);
        button = (Button) findViewById(R.id.btn_san);

        fragmentHomeP = new FragmentHomeP(this);
        fragmentHomeP.getFenLeiData(ApiUtil.FEN_LEI_URL);

        //数据库
        sqlDao = new SqlDao(SouSuoActivity.this);

        select();

        //点击返回
        btn_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
    public void btn_Click(View view) {
        name = ed_sousuo.getText().toString();

        sqlDao.add1(name);
        select();
        //获取数据....MVP
        MainPresenter_SouSuo mainPresenter_02 = new MainPresenter_SouSuo(this);
        mainPresenter_02.getLoginData(ApiUtil.selectProductUrl,name);





    }

    private void select() {
        list = sqlDao.select();

        //查询遍历
        if(adapter==null){

            if(list!=null){

                //    Toast.makeText(SouSuoActivity.this,list.toString(),Toast.LENGTH_SHORT).show();
                adapter = new listAdapter(list, SouSuoActivity.this);

                listView.setAdapter(adapter);

            }

        }else {

            adapter.notifyDataSetChanged();

        }

    }
    public void btn_clone(View view) {

        sqlDao.delete();
        list.clear();
        select();
        //按钮隐藏
        button.setVisibility(View.GONE);


    }

    @Override
    public void onSuccess(final Pruducts pruducts) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(pruducts.getMsg().equals("查询成功")){
                    //   Toast.makeText(SouSuoActivity.this,"查询成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SouSuoActivity.this,MainActivity_02.class);
                    intent.putExtra("name",name);
                    startActivity(intent);
                    overridePendingTransition(R.anim.rightin, R.anim.leftout);
                    finish();

                }else{
                    Toast.makeText(SouSuoActivity.this,"查询失败，无参数",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    /*没用*/
    @Override
    public void onSuccess(HomeBean homeBean) {

    }

    @Override
    public void onFenLeiDataSuccess(final FenLeiBean fenLeiBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<FenLeiBean.DataBean> list = fenLeiBean.getData();
                recycler_resou.setLayoutManager(new GridLayoutManager(SouSuoActivity.this,1, OrientationHelper.HORIZONTAL,false));
                recycler_resou.setAdapter(new SouSuo_fenlei_adapter(SouSuoActivity.this,list));


            }
        });


    }
}
