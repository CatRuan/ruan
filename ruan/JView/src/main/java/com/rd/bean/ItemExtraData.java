package com.rd.bean;

/**
 * Created by ruand on 2017/7/20.
 */

public class ItemExtraData {
    private String description;
    private String code;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ItemExtraData{" +
                "description='" + description + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
