package com.sf.viewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by form
 * on 2017/2/23.
 * Desc
 */

public class FirstActivity extends Activity implements View.OnClickListener {
    //控件的声明
    //lv
    private Button btn_listview;
    //gv
    private Button btn_gridview;
    //rv
    private Button btn_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();

    }

    private void initView() {

        btn_listview = (Button) findViewById(R.id.btn_listview);
        btn_gridview = (Button) findViewById(R.id.btn_gridview);
        btn_recyclerview = (Button) findViewById(R.id.btn_recyclerview);

        btn_listview.setOnClickListener(this);
        btn_gridview.setOnClickListener(this);
        btn_recyclerview.setOnClickListener(this);
    }

    /**
     * 按钮监听事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {
            case R.id.btn_listview:
                intent = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_gridview:
                intent = new Intent(FirstActivity.this, GridviewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recyclerview:
                intent = new Intent(FirstActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
