package com.jjkopen.demo.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created on 2017/8/31.
 */

@Entity
public class UserName {
    @Id(autoincrement = true)
    private Long id;
    @Unique
    private String name;
    @Generated(hash = 807328499)
    public UserName(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 575979651)
    public UserName() {
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
