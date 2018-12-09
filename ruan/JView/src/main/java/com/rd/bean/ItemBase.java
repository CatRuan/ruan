package com.rd.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by ruand on 2017/7/11.
 * base data of view data
 */

public class ItemBase {

    private String moduleName;
    private String version;
    private String desc;
    private Map<String, List<ItemData>> listBody;
    private Map<String, List<ItemExtraData>> listExtra;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public Map<String, List<ItemData>> getListBody() {
        return listBody;
    }

    public void setListBody(Map<String, List<ItemData>> listBody) {
        this.listBody = listBody;
    }

    public Map<String, List<ItemExtraData>> getListExtra() {
        return listExtra;
    }

    public void setListExtra(Map<String, List<ItemExtraData>> listExtra) {
        this.listExtra = listExtra;
    }

    @Override
    public String toString() {
        return "ItemBase{" +
                "moduleName='" + moduleName + '\'' +
                ", version='" + version + '\'' +
                ", desc='" + desc + '\'' +
                ", listBody=" + listBody +
                ", listExtra=" + listExtra +
                '}';
    }
}
