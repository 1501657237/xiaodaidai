package bwie.com.myapp2.util;

import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

import java.lang.reflect.Field;

/**
 * Created by ASUS on 2018/3/23.
 */

public class BottomNavigationViewHelper {
    public static void disableShiftMode(BottomNavigationView navigationView){
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigationView.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            try {
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);
                for (int i=0; i<menuView.getChildCount(); i++){
                    BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
                    itemView.setShiftingMode(false);
                    itemView.setChecked(itemView.getItemData().isChecked());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
