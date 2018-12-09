package com.rd.draw;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.rd.R;
import com.rd.bean.ItemData;
import com.rd.utils.DialogUtils;


/**
 * Created by ruand on 2017/7/21.
 * view holder of orderListAdapter
 */

abstract public class ItemHolder {

    abstract public void drawView(ItemHolder holder
            , ItemData itemData
            , Activity ctx);

    abstract public String getType();

    abstract public String getKey();

    abstract public Object getValue();

    abstract public void setValue(Object object);

    protected ListView mListView;

    protected void listView(ListView listView) {
        mListView = listView;
    }

    public static class ButtonHolder extends ItemHolder {
        Button button;
        String key;
        String type;
        String value;

        public ButtonHolder(View view) {
            button = (Button) view.findViewById(R.id.button);
        }

        @Override
        public void drawView(ItemHolder holder
                , ItemData itemData
                , Activity ctx) {
            type = itemData.getType();
            key = itemData.getKey();
            ((ButtonHolder) holder).button.setText(itemData.getDesc());
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public void setValue(Object object) {
            button.setText(object.toString());
        }

    }

    public static class DatePickerHolder extends ItemHolder {
        TextView tvDesc;
        LinearLayout llDatePicker;
        TextView tvDate;
        String key;
        String type;

        public DatePickerHolder(View view) {
            tvDesc = (TextView) view.findViewById(R.id.tvDesc);
            llDatePicker = (LinearLayout) view.findViewById(R.id.llDatePicker);
            tvDate = (TextView) view.findViewById(R.id.tvDate);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void drawView(final ItemHolder holder
                , ItemData itemData
                , final Activity ctx) {
            type = itemData.getType();
            key = itemData.getKey();

            ((DatePickerHolder) holder).tvDesc.setText(itemData.getDesc());
            ((DatePickerHolder) holder).llDatePicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtils.showCalendarDialog(ctx, ((DatePickerHolder) holder).tvDate);
                }
            });
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return tvDate.getText();
        }

        @Override
        public void setValue(Object object) {
            tvDate.setText((String) object);
        }


    }

    public static class SearchHolder extends ItemHolder {
        TextView tvDesc;
        LinearLayout llQuery;
        TextView tvSearched;
        String key;
        String type;

        public SearchHolder(View view) {
            tvDesc = (TextView) view.findViewById(R.id.tvDesc);
            llQuery = (LinearLayout) view.findViewById(R.id.llQuery);
            tvSearched = (TextView) view.findViewById(R.id.tvSearched);
        }

        @Override
        public void drawView(ItemHolder holder
                , ItemData itemData
                , final Activity ctx) {
            type = itemData.getType();
            key = itemData.getKey();
            ((SearchHolder) holder).tvDesc.setText(itemData.getDesc());
            ((SearchHolder) holder).tvSearched.setText(itemData.getValue());
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return tvSearched.getText();
        }

        @Override
        public void setValue(Object object) {
            tvSearched.setText((String) object);
        }
    }

    public static class ExpireDayHolder extends ItemHolder {
        TextView tvDesc;
        TextView tvExpireDay;
        CheckBox cbLongTerm;
        String key;
        String type;

        public ExpireDayHolder(View view) {
            tvDesc = (TextView) view.findViewById(R.id.tvDesc);
            tvExpireDay = (TextView) view.findViewById(R.id.tvExpireDay);
            cbLongTerm = (CheckBox) view.findViewById(R.id.cbLongTerm);
        }

        @Override
        public void drawView(final ItemHolder holder
                , ItemData itemData
                , final Activity ctx) {
            type = itemData.getType();
            key = itemData.getKey();
            ((ExpireDayHolder) holder).tvDesc.setText(itemData.getDesc());
            ((ExpireDayHolder) holder).tvExpireDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtils.showCalendarDialog(ctx, ((ExpireDayHolder) holder).tvExpireDay);
                    ((ExpireDayHolder) holder).cbLongTerm.setChecked(false);
                }
            });
            ((ExpireDayHolder) holder).cbLongTerm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((ExpireDayHolder) holder).cbLongTerm.isChecked())
                        ((ExpireDayHolder) holder).tvExpireDay.setText("9999-99-99");
                }
            });
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return tvExpireDay.getText();
        }

        @Override
        public void setValue(Object object) {
            tvExpireDay.setText((String) object);
        }
    }

    public static class SegmentHolder extends ItemHolder {
        TextView tvDesc;
        RadioButton rbLeft;
        RadioButton rbRight;
        String key;
        String type;
        String value;

        public SegmentHolder(View view) {
            tvDesc = (TextView) view.findViewById(R.id.tvDesc);
            rbLeft = (RadioButton) view.findViewById(R.id.rbLeft);
            rbRight = (RadioButton) view.findViewById(R.id.rbRight);
        }

        @Override
        public void drawView(ItemHolder holder, ItemData itemData, Activity ctx) {
            type = itemData.getType();
            key = itemData.getKey();
            ((SegmentHolder) holder).tvDesc.setText(itemData.getDesc());
            ((SegmentHolder) holder).rbLeft.setText(itemData.getChoice().get(0).getDesc());
            ((SegmentHolder) holder).rbRight.setText(itemData.getChoice().get(1).getDesc());
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public void setValue(Object object) {

        }
    }

    public static class TelephoneHolder extends ItemHolder {
        TextView tvDesc;
        EditText etAreaCode;
        EditText etTelephone;
        String key;
        String type;

        public TelephoneHolder(View view) {
            tvDesc = (TextView) view.findViewById(R.id.tvDesc);
            etAreaCode = (EditText) view.findViewById(R.id.etAreaCode);
            etTelephone = (EditText) view.findViewById(R.id.etTelephone);
        }

        @Override
        public void drawView(ItemHolder holder, ItemData itemData, Activity ctx) {
            type = itemData.getType();
            key = itemData.getKey();
            ((TelephoneHolder) holder).tvDesc.setText(itemData.getDesc());
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return etAreaCode.getText() + "-" + etTelephone.getText();
        }

        @Override
        public void setValue(Object object) {

        }
    }

    public static class TextHolder extends ItemHolder {
        TextView tvDesc;
        EditText etText;
        String key;
        String type;

        public TextHolder(View view) {
            tvDesc = (TextView) view.findViewById(R.id.tvDesc);
            etText = (EditText) view.findViewById(R.id.etText);
        }

        @Override
        public void drawView(ItemHolder holder, ItemData itemData, Activity ctx) {
            type = itemData.getType();
            key = itemData.getKey();
            ((TextHolder) holder).tvDesc.setText(itemData.getDesc());
            ((TextHolder) holder).etText.setHint("请输入" + itemData.getDesc());
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return etText.getText();
        }

        @Override
        public void setValue(Object object) {
            etText.setText((String) object);
        }

    }

    public static class TitleHolder extends ItemHolder {
        TextView tvTitle;
        String key;
        String type;
        String value;

        public TitleHolder(View view) {
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        }

        @Override
        public void drawView(ItemHolder holder
                , ItemData itemData, Activity ctx) {
            type = itemData.getType();
            key = itemData.getKey();
            ((TitleHolder) holder).tvTitle.setText(itemData.getDesc());
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public void setValue(Object object) {
            tvTitle.setText((String) object);
        }
    }

    public static class IconHolder extends ItemHolder {
        ImageView ivIcon;
        TextView tvTitle;
        String key;
        String type;
        String value;

        public IconHolder(View view) {
            ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        }

        @Override
        public void drawView(ItemHolder holder
                , ItemData itemData
                , Activity act) {
            type = itemData.getType();
            key = itemData.getKey();
            ((IconHolder) holder).tvTitle.setText(itemData.getDesc());
            loadIcon(act, ((IconHolder) holder).ivIcon, itemData.getValue());
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public void setValue(Object object) {

        }

        public void loadIcon(Activity act, ImageView icon, String name) {
            int resId = act.getResources().getIdentifier(name, "mipmap", act.getPackageName());
            icon.setImageResource(resId);

        }
    }

    public static class DisplayHolder extends ItemHolder {
        TextView tvDesc;
        TextView tvText;
        String key;
        String type;
        String value;

        public DisplayHolder(View view) {
            tvDesc = (TextView) view.findViewById(R.id.tvDesc);
            tvText = (TextView) view.findViewById(R.id.tvText);
        }

        @Override
        public void drawView(ItemHolder holder
                , ItemData itemData
                , Activity act) {
            type = itemData.getType();
            key = itemData.getKey();
            ((DisplayHolder) holder).tvDesc.setText(itemData.getDesc());
            ((DisplayHolder) holder).tvText.setText(itemData.getValue());
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return tvText.getText();
        }

        @Override
        public void setValue(Object object) {
            tvText.setText((String) object);
        }
    }
}
