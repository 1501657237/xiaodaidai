package bwie.com.jingdong.Model.bean;

import java.io.Serializable;

/**
 * Created by LENOVO on 2018/3/24.
 */

public class ProvinceBean implements Serializable {
    private int regionid;
    private String name;

    public ProvinceBean(int regionid, String name) {
        this.regionid = regionid;
        this.name = name;
    }

    public int getRegionid() {
        return regionid;
    }

    public void setRegionid(int regionid) {
        this.regionid = regionid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
