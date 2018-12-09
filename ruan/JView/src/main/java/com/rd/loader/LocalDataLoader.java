package com.rd.loader;

import android.app.Activity;

import com.google.gson.Gson;
import com.rd.bean.ItemBase;
import com.rd.utils.FileUtils;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ruand on 2017/7/14.
 */

public class LocalDataLoader {

    private static LocalDataLoader instance;

    private LocalDataLoader() {
        LocalDataHolder.init();
    }

    public static synchronized LocalDataLoader init() {
        if (instance == null) {
            instance = new LocalDataLoader();
        }
        return instance;
    }

    public static synchronized LocalDataLoader getInstance() {
        if (instance == null) {
            throw new RuntimeException("have you invoked init() before this ?");
        }
        return instance;
    }

    public Disposable loadData(final Activity ctx
            , final String dataKey
            , final Type type
            , final String fileName
            , Consumer<ItemBase> consumer) {
        Disposable disposable = Observable.create(new ObservableOnSubscribe<ItemBase>() {
            @Override
            public void subscribe(ObservableEmitter<ItemBase> e) throws Exception {
                String jsonStr = FileUtils.readAssets(fileName, ctx);
                Gson gson = new Gson();
                ItemBase baseData = gson.fromJson(jsonStr, type);
                LocalDataHolder.getInstance().putData(dataKey, baseData);
                e.onNext(baseData);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
        return disposable;
    }

    public Disposable loadData(final Activity ctx
            , final Type type
            , final String fileName
            , Consumer<ItemBase> consumer) {
        Disposable disposable = Observable.create(new ObservableOnSubscribe<ItemBase>() {
            @Override
            public void subscribe(ObservableEmitter<ItemBase> e) throws Exception {
                String jsonStr = FileUtils.readAssets(fileName, ctx);
                Gson gson = new Gson();
                ItemBase baseData = gson.fromJson(jsonStr, type);
                e.onNext(baseData);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
        return disposable;
    }

}
