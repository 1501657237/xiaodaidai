package bwie.com.jingdong.View.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import bwie.com.jingdong.R;
import bwie.com.jingdong.View.fragment.FragmentProvince;

/**
 * Created by LENOVO on 2018/3/23.
 */

public class ChooseDistinctActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_distinct);

        //已进入这个activity显示的是省的数据...用省的fragment进行替换
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new FragmentProvince()).commit();
    }
}