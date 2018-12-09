package com.rd.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by ruand on 2017/4/20.
 * dialog工具类
 */

public class DialogUtils {

    public static DatePickerDialog showCalendarDialog(Context context
            , DatePickerDialog.OnDateSetListener onDateSetListener) {
        Calendar c = Calendar.getInstance();
//        c.addType2HolderMapping(Calendar.DAY_OF_MONTH, +1);//默认 后一天
        DatePickerDialog datePickerDialog = new DatePickerDialog(context
                , onDateSetListener
                , c.get(Calendar.YEAR)
                , c.get(Calendar.MONTH)
                , c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
        return datePickerDialog;
    }


    public static DatePickerDialog showCalendarDialog(Context context
            , final TextView textView) {
        Calendar c = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(context
                , new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                textView.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        }
                , c.get(Calendar.YEAR)
                , c.get(Calendar.MONTH)
                , c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
        return datePickerDialog;
    }

    public static void cancelDialog(Dialog dialog) {
        if (null != dialog) {
            dialog.dismiss();
            dialog = null;
        }

    }
}
