package com.sf.viewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by form
 * on 2017/2/24.
 * Desc
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private LayoutInflater layoutInflater;
    private List<ListData> mData;

    private  InterfaceOnClick interfaceOnClick;

    public interface InterfaceOnClick{//item子项点击接口
        void onClick(int position);//item子项Button点击监听方法
    }

    //对外公布接口
    public void setInterfaceOnClick(InterfaceOnClick interfaceOnClick) {
        this.interfaceOnClick = interfaceOnClick;
    }

    /**
     * 构造方法
     * @param context
     * @param mData
     */
    public RecyclerViewAdapter(Context context, List<ListData> mData) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mData = mData;
    }

    /**
     * 创建View
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //获取item的View 并传给ViewHolder
        View view = layoutInflater.inflate(R.layout.item_data,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tv_name.setText(mData.get(position).getName());

        //CheckBox监听事件
        holder.cb_milk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mData.get(position).setCheckboxFlag(isChecked);
            }
        });
        holder.cb_milk.setChecked(mData.get(position).isCheckboxFlag());

        //item里面的Button监听事件
        holder.btn_to_SecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(interfaceOnClick != null){
                    interfaceOnClick.onClick(position); //回调接口的点击方法
                }
            }
        });
    }

    /**
     * item个数
     * @return
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     *ViewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name;
        CheckBox cb_milk;
        Button btn_to_SecondActivity;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            cb_milk = (CheckBox) itemView.findViewById(R.id.cb_milk);
            btn_to_SecondActivity = (Button) itemView.findViewById(R.id.btn_to_SecondActivity);
        }
    }
}
