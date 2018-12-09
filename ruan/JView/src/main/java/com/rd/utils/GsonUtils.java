package com.rd.utils;

import android.util.Log;

import com.google.gson.Gson;


/**
 * Created by ruand on 2017/7/19.
 */

public class GsonUtils {

    public static String objectToJson(Object obj) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(obj);
        Log.i("GsonUtils", "jsonStr->" + jsonStr);
        return jsonStr;
    }
}
