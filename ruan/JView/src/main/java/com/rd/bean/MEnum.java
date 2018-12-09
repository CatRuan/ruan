package com.rd.bean;

/**
 * Created by ruand on 2017/7/18.
 */

public class MEnum {
    private String menum;
    private String desc;
    private String key;

    public String getMenum() {
        return menum;
    }

    public void setMenum(String menum) {
        this.menum = menum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "MEnum{" +
                "menum='" + menum + '\'' +
                ", desc='" + desc + '\'' +
                ", getKey='" + key + '\'' +
                '}';
    }
}
