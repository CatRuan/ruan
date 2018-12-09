package com.rd.bean;

import java.util.List;

/**
 * Created by ruand on 2017/7/18.
 */

public class ItemData {
    private String applicability;
    private String value;
    private List<MEnum> choice;
    private String key;
    private String type;
    private String desc;

    public String getApplicability() {
        return applicability;
    }

    public void setApplicability(String applicability) {
        this.applicability = applicability;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<MEnum> getChoice() {
        return choice;
    }

    public void setChoice(List<MEnum> choice) {
        this.choice = choice;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ItemData{" +
                "applicability='" + applicability + '\'' +
                ", getValue='" + value + '\'' +
                ", choice=" + choice +
                ", getKey='" + key + '\'' +
                ", getType='" + type + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
