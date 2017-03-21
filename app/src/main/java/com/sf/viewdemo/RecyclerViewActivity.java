package com.sf.viewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by form
 * on 2017/2/23.
 * Desc
 */

public class RecyclerViewActivity extends Activity implements View.OnClickListener{

    private TextView tv_add; //添加数据
    private TextView btn_back; //返回
    private RecyclerView recyclerView;
    private ImageView iv_empty_view; //ListView为空时显示的视图

    private List<ListData> mData; //数据源
    private  RecyclerViewAdapter recyclerViewAdapter;  //适配器

    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        initView();

        //添加数据
        for(int i = 1;i <= 20;i++){
            mData.add(new ListData(""+i,false));
        }

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //添加适配器
        recyclerView.setAdapter(recyclerViewAdapter);

        //调用接口里面Button的点击事件
        recyclerViewAdapter.setInterfaceOnClick(new RecyclerViewAdapter.InterfaceOnClick() {
            @Override
            public void onClick(int position) {
//                Log.i("btn_to_SecondActivity", "onClick: "+position);
                Intent intent = new Intent(RecyclerViewActivity.this,SecondActivity.class);
                intent.putExtra("key1",position);
                startActivityForResult(intent,3);
            }
        });
    }

    /**
     * 初始化控件
     */
    private void initView() {
        tv_add = (TextView) findViewById(R.id.tv_add);
        tv_add.setVisibility(View.VISIBLE);  //设为可见
        btn_back = (TextView) findViewById(R.id.btn_back);
        btn_back.setVisibility(View.VISIBLE);//设为可见
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        iv_empty_view = (ImageView) findViewById(R.id.iv_empty_view3);

        mData = new ArrayList<ListData>();
        recyclerViewAdapter  = new RecyclerViewAdapter(this,mData);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 3 && resultCode == 2){
            //接收传回的数据
            String name = data.getStringExtra("key2");
            int position = data.getIntExtra("key3",0);

            //将传回的数据显示在相应的控件
            mData.set(position,new ListData(name,false));
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back: //返回
                finish();
                break;

            case R.id.tv_add:  //添加数据
                mData.add(new ListData("new",false));
                break;
            default:
                break;
        }
    }
}
