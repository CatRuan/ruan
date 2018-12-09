package com.rd.utils;

import android.content.Context;

import java.io.InputStream;

/**
 * Created by ruand on 2017/7/11.
 *
 */

public class FileUtils {

    public static String readAssets(String fileName, Context context) {
        try {
            InputStream in = context.getResources().getAssets().open(fileName);
            int length = in.available();
            byte[] buffer = new byte[length];
            in.read(buffer);
            in.close();
            return new String(buffer, "utf8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
