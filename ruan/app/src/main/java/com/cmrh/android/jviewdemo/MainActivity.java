package com.cmrh.android.jviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.rd.JView;
import com.rd.draw.ItemHolder;
import com.rd.mapping.ListTag;


public class MainActivity extends AppCompatActivity {

    @ListTag(key = "mainView")
    public GridView mGvHome;
    private JView mJView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGvHome = (GridView)findViewById(R.id.gvHome);
        loadView();
        clickEvent();
    }

    private void loadView() {
        mJView = new JView();
        mJView.drawIn(this).loadViewData("mainView", "mainView.json");
    }

    private void clickEvent() {
        mGvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemHolder.IconHolder holder = (ItemHolder.IconHolder) view.getTag();
                if (null != holder && null != holder.getKey()) {
                    String key = holder.getKey();
                    switch (key) {
                        case "view1":
                            MainActivity.this.startActivity(new Intent(
                                    MainActivity.this,View1Activity.class));
                            break;
                        case "view2":
                            MainActivity.this.startActivity(new Intent(
                                    MainActivity.this,View2Activity.class));
                            break;
                        case "view3":
                            MainActivity.this.startActivity(new Intent(
                                    MainActivity.this,View3Activity.class));
                            break;
                        default:
                    }
                }
            }
        });
    }

}
