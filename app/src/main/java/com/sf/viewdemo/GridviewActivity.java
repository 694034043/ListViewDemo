package com.sf.viewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by form
 * on 2017/2/23.
 * Desc
 */

public class GridviewActivity extends Activity {
    private TextView tv_add; //添加数据
    private TextView btn_back; //返回
    private GridView gridView;
    private ImageView iv_empty_view; //ListView为空时显示的视图

    private List<ListData> mData; //数据源
    //    private MyAdapter myAdapter; //适配器
    private GridviewActivity.GridViewAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gridview);

        initView();

        //实例化数据源
        mData = new ArrayList<ListData>();
        for (int i = 1; i <= 20; i++) {
            ListData listData = new ListData("" + i, false);
            mData.add(listData);
        }

        gridView = (GridView) findViewById(R.id.gridview);

        //当ListView设置空数据是要显示的布局
//        listView.setEmptyView(findViewById(R.id.iv_empty_view));
        myAdapter = new GridviewActivity.GridViewAdapter(mData);
        gridView.setAdapter(myAdapter);

        //返回监听事件
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //动态添加数据
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.add(new ListData("new", false));
                myAdapter.notifyDataSetChanged();
                gridView.setSelection(mData.size() - 1);
            }
        });

        //列表滚动监听
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem == 0) {  //滚动到第一行
                    Toast.makeText(GridviewActivity.this, "已滚动列表第一行", Toast.LENGTH_SHORT).show();
                } else if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) { //滚动到最后一行
                    Toast.makeText(GridviewActivity.this, "已滚动到列表最后一行", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //列表点击事件监听
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(GridviewActivity.this, "当前点击的是第" + position + "条", Toast.LENGTH_SHORT).show();

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
        gridView = (GridView) findViewById(R.id.gridview);
        iv_empty_view = (ImageView) findViewById(R.id.iv_empty_view2);
    }

    public class GridViewAdapter extends BaseAdapter {

        private List<ListData> mData;

        public GridViewAdapter(List<ListData> mData) {
            this.mData = mData;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder3 viewHolder = null;
            if (convertView == null) {
                viewHolder = new GridviewActivity.GridViewAdapter.ViewHolder3();
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_gridview_data, null);
                viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name2);
                viewHolder.cb_milk = (CheckBox) convertView.findViewById(R.id.cb_milk2);
                viewHolder.btn_to_SecondActivity = (Button) convertView.findViewById(R.id.btn_to_SecondActivity2);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (GridviewActivity.GridViewAdapter.ViewHolder3) convertView.getTag();
            }

            viewHolder.tv_name.setText(mData.get(position).getName());

            // item子项中的CheckBox监听事件
            viewHolder.cb_milk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    mData.get(position).setCheckboxFlag(isChecked);
                }
            });

            viewHolder.cb_milk.setChecked(mData.get(position).isCheckboxFlag());

            //列表item子项Button的监听事件
            viewHolder.btn_to_SecondActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GridviewActivity.this, SecondActivity.class);
                    intent.putExtra("key1", position);
                    startActivityForResult(intent, 1);  //自定义请求码为0001
                }
            });

            return convertView;
        }

        private final class ViewHolder3 {
            TextView tv_name; //名字
            CheckBox cb_milk; //牛奶
            Button btn_to_SecondActivity; //item里面按钮，跳转到第二个界面
        }
    }

    /**
     * 获取由SecondActivity传回来的数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 2) {
            String name = data.getStringExtra("key2");
            int position = data.getIntExtra("key3", 0);
            mData.set(position, new ListData(name, false));
            myAdapter.notifyDataSetChanged();
            gridView.setSelection(position);
        }
    }
}
