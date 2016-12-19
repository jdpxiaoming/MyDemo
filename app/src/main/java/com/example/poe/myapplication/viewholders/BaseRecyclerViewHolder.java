package com.example.poe.myapplication.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 基本的ViewHolder
 * Created by poe on 15-8-10.
 */
public abstract class BaseRecyclerViewHolder<T>  extends RecyclerView.ViewHolder{
    /**
     * 缓存数据，跳转使用
     */
    protected T mData ;
    /**
     * 当前viewHolder在整个数据中的位置
     */
    protected int mCurrentPosition = -1;

    protected OnItemClickListener<T> mOnClickListener;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * 仅方便查看view
     * @return
     */
    public abstract int obtainLayoutId();
    /**
     * onBindViewHolder
     * 如果需要使用data应当缓存 mData = data
     * @param data
     */
    // TODO: 16-2-16 填充数据，
    public abstract void setData(int position ,T data);

    public void setOnClickListener(OnItemClickListener<T> mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    public interface OnItemClickListener<T>{
        /**
         * 单个item 点击事件
         * @param data
         */
        void onItemClicked(int position, T data);
    }
}
