package com.cmrh.android.jviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.rd.JView;
import com.rd.mapping.ListTag;

public class View3Activity extends AppCompatActivity {
    @ListTag(key = "lvView3")
    public ListView mlvView3;
    private JView mJView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view3);
        mlvView3= (ListView)findViewById(R.id.lvView3);
        loadView();
    }

    private void loadView() {
        mJView = new JView();
        mJView.drawIn(this).loadViewData("view3", "view3.json");

    }
}
