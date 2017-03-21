package com.sf.viewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by form
 * on 2017/2/23.
 * Desc
 */

public class SecondActivity extends Activity implements View.OnClickListener {

    private EditText et_name;
    private TextView btn_back;  //返回按钮
    private Button btn_ok;  //OK按钮
    private int itemPosition; //当前页对应的Item位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Log.i(" SecondActivity ==", "onCreate");
        initView();

        Intent intent = getIntent();
        itemPosition = intent.getIntExtra("key1", 0);

        btn_back.setOnClickListener(this);
        btn_ok.setOnClickListener(this);

    }

    /**
     * 初始化控件
     */
    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        btn_back = (TextView) findViewById(R.id.btn_back);
        btn_back.setVisibility(View.VISIBLE);
        btn_ok = (Button) findViewById(R.id.btn_ok);
    }

    /**
     * 监听事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_back:  //返回按钮
                finish();
                break;

            case R.id.btn_ok:  //确定按钮
                Intent intent = new Intent();
                String name = et_name.getText().toString();
                intent.putExtra("key2", name);
                intent.putExtra("key3", itemPosition);
                setResult(2, intent); //结果码
                finish();
                break;
            default:
                break;

        }
    }
}
