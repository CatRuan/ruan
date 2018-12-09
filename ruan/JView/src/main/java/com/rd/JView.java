package com.rd;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.ArrayMap;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.rd.bean.ItemBase;
import com.rd.bean.ItemData;
import com.rd.draw.ItemHolder;
import com.rd.draw.TypeAdapter;
import com.rd.loader.LocalDataHolder;
import com.rd.loader.LocalDataLoader;
import com.rd.mapping.ListTag;
import com.rd.mapping.TypeMapping;
import com.rd.utils.ListViewUtil;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by ruand on 2017/7/21.
 * a util to load data for flow
 */

public class JView {
    private ItemBase baseItem;
    private boolean viewDataLoaded = true;
    private boolean extraDataLoaded = true;
    private Activity mAct;
    private Fragment mFrag;
    private Disposable loadDisposable = null;
    private Disposable extraLoadDisposable = null;
    private Disposable preLoadDisposable = null;

    public JView() {
        LocalDataLoader.init();
    }

    public JView drawIn(Activity act) {
        mAct = act;
        return this;
    }

    public JView drawIn(Fragment act) {
        mFrag = act;
        return this;
    }

    public JView newItem(String viewType, int viewId, Class<? extends ItemHolder> viewHolder) {
        TypeMapping.type2ViewId.put(viewType, viewId);
        TypeMapping.type2ClassMap.put(viewType, viewHolder);
        return this;
    }

    public JView replayItem(String viewType, Class<? extends ItemHolder> viewHolder) {
        TypeMapping.type2ClassMap.put(viewType, viewHolder);
        return this;
    }

    public JView loadViewData(String dataKey, String fileName) {
        viewDataLoaded = false;
        baseItem = (ItemBase) LocalDataHolder.getInstance().getData(dataKey);
        if (null == baseItem) {
            Activity ctx = mAct != null ? mAct : mFrag.getActivity();
            loadDisposable = LocalDataLoader.getInstance().loadData(ctx
                    , dataKey
                    , new TypeToken<ItemBase>() {
                    }.getType()
                    , fileName
                    , new Consumer<ItemBase>() {
                        @Override
                        public void accept(ItemBase itemBase) throws Exception {
                            baseItem = itemBase;
                            viewDataLoaded = true;
                            draw();
                        }
                    });
        } else {
            viewDataLoaded = true;
            draw();
        }
        return this;
    }

    public JView loadExtraData(String dataKey, String fileName) {
        extraDataLoaded = false;
        if (null == LocalDataHolder.getInstance().getData(dataKey)) {
            Activity ctx = mAct != null ? mAct : mFrag.getActivity();
            extraLoadDisposable = LocalDataLoader.getInstance().loadData(ctx
                    , dataKey, new TypeToken<ItemBase>() {
                    }.getType()
                    , fileName, new Consumer<ItemBase>() {
                        @Override
                        public void accept(ItemBase itemBase) throws Exception {
                            extraDataLoaded = true;
                            draw();
                        }
                    });
        } else {
            extraDataLoaded = true;
            draw();
        }
        return this;
    }

    public JView preLoadData(String dataKey, String fileName) {
        ItemBase baseItem = (ItemBase) LocalDataHolder.getInstance().getData(dataKey);
        if (null == baseItem) {
            Activity ctx = mAct != null ? mAct : mFrag.getActivity();
            preLoadDisposable = LocalDataLoader.getInstance().loadData(ctx
                    , dataKey
                    , new TypeToken<ItemBase>() {
                    }.getType()
                    , fileName
                    , new Consumer<ItemBase>() {
                        @Override
                        public void accept(ItemBase itemBase) throws Exception {
                        }
                    });
        }
        return this;
    }

    private void draw() {
        if (extraDataLoaded && viewDataLoaded) {
            if (mAct != null)
                showList(mAct, baseItem.getListBody());
            else
                showList(mFrag, baseItem.getListBody());
        }
    }

    private void showList(Activity act, Map<String, List<ItemData>> listBody) {
        try {
            Field[] fields = act.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getType() == ListView.class) {
                    ListTag tag = field.getAnnotation(ListTag.class);
                    ListView listView = (ListView) field.get(act);
                    TypeAdapter adapter = new TypeAdapter(act, listBody.get(tag.key()));
                    adapter.setListView(listView);
                    listView.setAdapter(adapter);
                    ListViewUtil.setListViewHeightBasedOnChildren(listView);
                }
                if (field.getType() == GridView.class) {
                    ListTag tag = field.getAnnotation(ListTag.class);
                    GridView listView = (GridView) field.get(act);
                    TypeAdapter adapter = new TypeAdapter(act, listBody.get(tag.key()));
                    listView.setAdapter(adapter);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void showList(Fragment fg, Map<String, List<ItemData>> listBody) {
        try {
            Field[] fields = fg.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getType() == ListView.class) {
                    ListTag tag = field.getAnnotation(ListTag.class);
                    ListView listView = (ListView) field.get(fg);
                    TypeAdapter adapter = new TypeAdapter(fg.getActivity(), listBody.get(tag.key()));
                    adapter.setListView(listView);
                    listView.setAdapter(adapter);
                    ListViewUtil.setListViewHeightBasedOnChildren(listView);
                }
                if (field.getType() == GridView.class) {
                    ListTag tag = field.getAnnotation(ListTag.class);
                    GridView listView = (GridView) field.get(fg);
                    TypeAdapter adapter = new TypeAdapter(fg.getActivity(), listBody.get(tag.key()));
                    listView.setAdapter(adapter);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void clearDataInCache(String... key) {
        for (int i = 0; i < key.length; i++) {
            LocalDataHolder.getInstance().deleteData(key[i]);
        }
    }

    public void cancelLoad() {
        if (loadDisposable != null
                && !loadDisposable.isDisposed()) {
            loadDisposable.dispose();
        }
        if (preLoadDisposable != null
                && !preLoadDisposable.isDisposed()) {
            preLoadDisposable.dispose();
        }
        if (extraLoadDisposable != null
                && !extraLoadDisposable.isDisposed()) {
            extraLoadDisposable.dispose();
        }
    }

    public static Map<String, Object> getViewData(ListView list) {
        Map<String, Object> viewDataMap = new ArrayMap<>();
        for (int i = 0; i < list.getCount(); i++) {
            ItemHolder holder = (ItemHolder) list.getChildAt(i).getTag();
            viewDataMap.put(holder.getKey(), holder.getValue());
        }
        return viewDataMap;
    }

    public static void putViewData(ListView list, Map<String, Object> viewData) {
        for (int i = 0; i < list.getCount(); i++) {
            ItemHolder holder = (ItemHolder) list.getChildAt(i).getTag();
            for (String key : viewData.keySet()) {
                if (key.equals(holder.getKey()))
                    holder.setValue(viewData.get(key));
            }
        }
    }

    public static void show(final View v, int height) {
        ValueAnimator animator = ValueAnimator.ofInt(0, height);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (Integer) animation.getAnimatedValue();
                v.getLayoutParams().height = value;
                v.setLayoutParams(v.getLayoutParams());
            }
        });
        animator.start();
    }
}
