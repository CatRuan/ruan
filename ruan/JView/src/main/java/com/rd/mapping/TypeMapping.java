package com.rd.mapping;

import android.util.ArrayMap;

import com.rd.R;
import com.rd.draw.ItemHolder;

import java.util.Map;

/**
 * Created by ruand on 2017/7/27.
 * this class for mapping view id to getType and view holder to getType
 */

public class TypeMapping {

    private static final String TYPE_TITLE = "title";
    private static final String TYPE_TEXT = "text";
    private static final String TYPE_SEGMENT = "segment";
    private static final String TYPE_CHOICE = "choice";
    private static final String TYPE_DATE_PICKER = "datePicker";
    private static final String TYPE_ID_EXPIRE_DAY = "idExpireDay";
    private static final String TYPE_TELEPHONE = "telephone";
    private static final String TYPE_BUTTON = "button";
    private static final String TYPE_SEARCH = "search";
    public static final String TYPE_ICON = "icon";
    public static final String TYPE_DISPLAY = "display";


    public static Map<String, Class<? extends ItemHolder>> type2ClassMap = new ArrayMap<>();

    static {
        type2ClassMap.put(TYPE_TITLE, ItemHolder.TitleHolder.class);
        type2ClassMap.put(TYPE_TEXT, ItemHolder.TextHolder.class);
        type2ClassMap.put(TYPE_SEGMENT, ItemHolder.SegmentHolder.class);
        type2ClassMap.put(TYPE_DATE_PICKER, ItemHolder.DatePickerHolder.class);
        type2ClassMap.put(TYPE_ID_EXPIRE_DAY, ItemHolder.ExpireDayHolder.class);
        type2ClassMap.put(TYPE_TELEPHONE, ItemHolder.TelephoneHolder.class);
        type2ClassMap.put(TYPE_BUTTON, ItemHolder.ButtonHolder.class);
        type2ClassMap.put(TYPE_SEARCH, ItemHolder.SearchHolder.class);
        type2ClassMap.put(TYPE_ICON, ItemHolder.IconHolder.class);
        type2ClassMap.put(TYPE_DISPLAY, ItemHolder.DisplayHolder.class);
    }

    public static Map<String, Integer> type2ViewId = new ArrayMap<>();

    static {
        type2ViewId.put(TYPE_TITLE, R.layout.item_order_title);
        type2ViewId.put(TYPE_TEXT, R.layout.item_order_text);
        type2ViewId.put(TYPE_SEGMENT, R.layout.item_order_segment);
        type2ViewId.put(TYPE_DATE_PICKER, R.layout.item_order_datepicker);
        type2ViewId.put(TYPE_ID_EXPIRE_DAY, R.layout.item_order_idexpireday);
        type2ViewId.put(TYPE_TELEPHONE, R.layout.item_order_telephone);
        type2ViewId.put(TYPE_BUTTON, R.layout.item_order_button);
        type2ViewId.put(TYPE_SEARCH, R.layout.item_order_search);
        type2ViewId.put(TYPE_ICON, R.layout.item_icon_text);
        type2ViewId.put(TYPE_DISPLAY, R.layout.item_order_display);
    }

    public final static String EXTRA_DATA_KEY = "choice_extra";

}
