package com.rd.draw;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.rd.bean.ItemData;
import com.rd.mapping.TypeMapping;
import com.zhy.autolayout.utils.AutoUtils;

import java.lang.reflect.Constructor;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ruand on 2017/7/18.
 * the common adapter of order flows
 */

public class TypeAdapter extends BaseAdapter {

    private Activity mContext;
    private List<ItemData> mItems;

    public TypeAdapter(Activity context, List<ItemData> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, viewId(position), null);
            AutoUtils.autoSize(convertView);
            initViewHolder(convertView, position);
        }
        return convertView;
    }

    private void initViewHolder(final View convertView, final int position) {
        Observable.create(new ObservableOnSubscribe<ItemHolder>() {
            @Override
            public void subscribe(ObservableEmitter<ItemHolder> emitter) throws Exception {
                try {
                    Class clazz = TypeMapping.type2ClassMap.get(mItems.get(position).getType());
                    Constructor c = clazz.getConstructor(View.class);
                    emitter.onNext((ItemHolder) c.newInstance(convertView));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ItemHolder>() {
                    @Override
                    public void accept(ItemHolder itemHolder) throws Exception {
                        convertView.setTag(itemHolder);
                        itemHolder.drawView(itemHolder, mItems.get(position), mContext);
                        itemHolder.listView(mListView);
                    }
                });
    }

    private int viewId(int position) {
        return TypeMapping.type2ViewId.get(mItems.get(position).getType());
    }


    private ListView mListView;

    public void setListView(ListView listView) {
        mListView = listView;
    }
}
