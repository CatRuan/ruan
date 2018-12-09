package com.cmrh.android.jviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.rd.JView;
import com.rd.mapping.ListTag;

public class View1Activity extends AppCompatActivity {

    @ListTag(key = "lvView1")
    public ListView mlvView1;
    private JView mJView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view1);
        mlvView1 = (ListView)findViewById(R.id.lvView1);
        loadView();
    }

    private void loadView() {
        mJView = new JView();
        mJView.drawIn(this).loadViewData("view1", "view1.json");

    }
}
