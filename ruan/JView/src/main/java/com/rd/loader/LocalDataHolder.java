package com.rd.loader;

import android.util.ArrayMap;

import java.util.Map;

/**
 * Created by ruand on 2017/7/20.
 * hold the local data ,forbidden to load the same data again
 */

public class LocalDataHolder {
    public Map<String, Object> DATA;
    private static LocalDataHolder instance;

    private LocalDataHolder() {
        DATA = new ArrayMap<>();
    }

    public static synchronized LocalDataHolder init() {
        if (instance == null) {
            instance = new LocalDataHolder();
        }
        return instance;
    }

    public static synchronized LocalDataHolder getInstance() {
        if (instance == null) {
            throw new RuntimeException("have you invoked init() before this ?");
        }
        return instance;
    }

    public void putData(String key, Object value) {
        DATA.put(key, value);
    }

    public void deleteData(String key) {
        DATA.remove(key);
    }

    public Object getData(String key) {
        return DATA.get(key);
    }

    public void clearHolder() {
        DATA.clear();
    }
}
