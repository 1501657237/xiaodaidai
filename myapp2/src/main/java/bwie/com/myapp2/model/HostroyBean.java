package bwie.com.myapp2.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ASUS on 2018/3/28.
 */
@Entity
public class HostroyBean {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    @Generated(hash = 189235989)
    public HostroyBean(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1769876508)
    public HostroyBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
