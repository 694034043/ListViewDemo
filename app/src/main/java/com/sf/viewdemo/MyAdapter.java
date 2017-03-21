package com.sf.viewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 *
 */
public class MyAdapter extends BaseAdapter {

    private List<ListData> mData;
    private LayoutInflater mInflater;
    private Context context;

    public interface btn_onClickListener {
        void onClick(int pos);
    }

    ;
    public btn_onClickListener onClickBtn;

    public void setOnClickBtn(btn_onClickListener onClickBtn) {  //对外提供的方法
        this.onClickBtn = onClickBtn;
    }

    public MyAdapter(List<ListData> mData, Context context) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
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

        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_data, null);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.cb_milk = (CheckBox) convertView.findViewById(R.id.cb_milk);
            viewHolder.btn_to_SecondActivity = (Button) convertView.findViewById(R.id.btn_to_SecondActivity);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_name.setText(mData.get(position).getName());

        // //item子项中的CheckBox监听事件
        viewHolder.cb_milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mData.get(position).isCheckboxFlag()) {
                    mData.get(position).setCheckboxFlag(true);
                } else {
                    mData.get(position).setCheckboxFlag(false);
                }
            }
        });
        viewHolder.cb_milk.setChecked(mData.get(position).isCheckboxFlag());

        //item子项中的按钮监听事件
        viewHolder.btn_to_SecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickBtn != null) {
                    onClickBtn.onClick(position);
                }
            }
        });

        return convertView;
    }

    private final class ViewHolder {
        TextView tv_name; //名字
        CheckBox cb_milk; //牛奶
        Button btn_to_SecondActivity; //item里面按钮，跳转到第二个界面
    }
}


