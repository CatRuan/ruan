package com.cmrh.android.jviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.rd.JView;
import com.rd.mapping.ListTag;

public class View2Activity extends AppCompatActivity {

    @ListTag(key = "lvView2")
    public ListView mlvView2;
    private JView mJView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view2);
        mlvView2 = (ListView)findViewById(R.id.lvView2);
        loadView();
    }

    private void loadView() {
        mJView = new JView();
        mJView.drawIn(this).loadViewData("view2", "view2.json");

    }
}
