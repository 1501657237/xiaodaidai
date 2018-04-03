package bwie.com.jingdong.View.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import bwie.com.jingdong.Model.bean.FenLeiBean;
import bwie.com.jingdong.Model.bean.HomeBean;
import bwie.com.jingdong.Presenter.FragmentHomeP;
import bwie.com.jingdong.R;
import bwie.com.jingdong.View.Adapter.FenLeiAdapter;
import bwie.com.jingdong.View.Iview.InterFragmentHome;
import bwie.com.jingdong.View.activity.SouSuoActivity;
import bwie.com.jingdong.util.ApiUtil;

/**
 * Created by LENOVO on 2018/3/12.
 */

public class FragmentFenLei extends Fragment  implements InterFragmentHome {

    private ListView fen_lei_list_view;
    private FrameLayout fen_lei_frame;
    private FragmentHomeP fragmentHomeP;
    private FenLeiAdapter fenLeiAdapter;
    private RelativeLayout edit_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fen_lei_layout,container,false);

        fen_lei_list_view = view.findViewById(R.id.fen_lei_list_view);
        fen_lei_frame = view.findViewById(R.id.fen_lei_frame);
        edit_view = (RelativeLayout) view.findViewById(R.id.edit_view);
        edit_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SouSuoActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //获取左边listView展示的数据
        fragmentHomeP = new FragmentHomeP(this);

        fragmentHomeP.getFenLeiData(ApiUtil.FEN_LEI_URL);

    }
    /**
     * 这个回调在fragment分类中没有用
     * @param homeBean
     */
    @Override
    public void onSuccess(HomeBean homeBean) {

    }

    @Override
    public void onFenLeiDataSuccess(final FenLeiBean fenLeiBean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //给listView设置适配器
                fenLeiAdapter = new FenLeiAdapter(getActivity(), fenLeiBean);
                fen_lei_list_view.setAdapter(fenLeiAdapter);
                //默认显示京东超市右边对应的数据
                FragmentFenLeiRight fragmentFenLeiRight = FragmentFenLeiRight.getInstance(fenLeiBean.getData().get(0).getCid());

                getChildFragmentManager().beginTransaction().replace(R.id.fen_lei_frame,fragmentFenLeiRight).commit();



                //条目点击事件
                fen_lei_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        //设置适配器当前位置的方法
                        fenLeiAdapter.setCurPositon(i);
                        //刷新适配器...getView重新执行
                        fenLeiAdapter.notifyDataSetChanged();
                        //滚动到指定的位置,,,第一个参数是滚动哪一个条目,,,滚动到哪个位置/偏移量
                        fen_lei_list_view.smoothScrollToPositionFromTop(i,(adapterView.getHeight()-view.getHeight())/2);

                        //使用fragment替换右边frameLayout....fragment之间的传值
                        FragmentFenLeiRight fragmentFenLeiRight = FragmentFenLeiRight.getInstance(fenLeiBean.getData().get(i).getCid());


                        getChildFragmentManager().beginTransaction().replace(R.id.fen_lei_frame,fragmentFenLeiRight).commit();


                    }
                });
            }
        });

    }
}
