package com.example.poe.myapplication.adapters;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.poe.myapplication.R;
import com.example.poe.myapplication.viewholders.BaseRecyclerViewHolder;
import com.example.poe.myapplication.viewholders.LoadMoreViewHolder;
import java.util.List;

/**
 * Created by poe on 15-8-10.
 * 基本抽象的Recycler adapter ...
 */
public  class BaseRecyclerAdapter<T,VH extends BaseRecyclerViewHolder> extends RecyclerView.Adapter{
    private static final String TAG = "BaseRecyclerAdapter";
    public static final int TYPE_FOOTER = Integer.MIN_VALUE;

    private boolean mHasFooter;//设置是否显示Footer
    private boolean mHasMoreData;//设置是否可以继续加载数据

    private List<T> mData;
    private ViewCreator<VH> mCreator;

    public BaseRecyclerAdapter(ViewCreator<VH> mCreator) {
        this.mCreator = mCreator;
    }
    public void setData(List<T> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        if (mData != null) {
            mData.addAll(data);
        } else {
            setData( data);
        }

        this.notifyDataSetChanged();
    }

    public void clearData() {
        if (mData != null)
            mData.clear();
    }

    public boolean isHasFooter() {
        return mHasFooter;
    }

    public void setHasFooter(boolean hasFooter) {
        this.mHasFooter = hasFooter;
    }

    public boolean isHasMoreData() {
        return mHasMoreData;
    }

    public void setHasMoreData(boolean hasMoreData) {
        this.mHasMoreData = hasMoreData;
    }

    private int getBasicItemCount() {
        return mData == null ?0 : mData.size();
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {//底部 加载view
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_load_more,null);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(params);
            return new LoadMoreViewHolder(view);
        } else if(null != mCreator){
            BaseRecyclerViewHolder viewHolder = mCreator.createViewHolder(parent,viewType);
            initClickListener(viewHolder);
            return viewHolder;
        }else{
            //give an default view or throws exception
            throw new IllegalArgumentException("ViewCreator can not NULL , it must be implemented！！！");
        }
    }

    private void initClickListener(BaseRecyclerViewHolder viewHolder) {
        viewHolder.itemView.setOnClickListener(mClickListener);
        setChildListener(viewHolder.itemView);
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Snackbar.make(v,"clicked ! "+v.getId(),Snackbar.LENGTH_SHORT).show();
            int i = 3/0;
        }
    };

    private void setChildListener(View view) {
        if(view instanceof ViewGroup ){
            Log.i(TAG , " itemView is an ViewGroup !");
            ViewGroup viewGroup = (ViewGroup) view;
            Log.i(TAG,"item view count is : " + viewGroup.getChildCount() );
            if(viewGroup.getChildCount()==0) return;
            for(int i = 0 ; i < viewGroup.getChildCount() ; i++ ){
                final View v = viewGroup.getChildAt(i);
                Log.i(TAG , " view [ "+i+" ] id :@"+v);
                if(v instanceof ViewGroup ){
                    setChildListener(v);
                    break;
                }
                v.setOnClickListener(mClickListener);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(null != mCreator){
            if (position == getBasicItemCount() && mHasFooter) {
                return TYPE_FOOTER;
            }
            //USER INTERFACE
            return  mCreator.getItemViewType(position);
        }else{
            return super.getItemViewType(position);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof LoadMoreViewHolder){
            if(mHasMoreData){
                ((LoadMoreViewHolder)holder).show();
            }else{
                ((LoadMoreViewHolder)holder).dismiss();
            }
        }else if(holder instanceof BaseRecyclerViewHolder){
            BaseRecyclerViewHolder<T> bvh = (BaseRecyclerViewHolder<T>) holder;
            bvh.setData(position , mData.get(position));
        }else{
            throw new IllegalArgumentException("ViewHolder not extends BaseRecyclerViewHolder<T> ～！！！");
        }
    }

    @Override
    public int getItemCount() {
        return getBasicItemCount() + (mHasFooter ? 1 : 0);
    }

    public interface  ViewCreator<VH>{
        /**
         * create the viewHolder
         * @param parent
         * @param viewType
         * @return
         */
        VH createViewHolder(ViewGroup parent, int viewType);

        /**
         * return the view type .
         * @param position
         * @return
         */
        int getItemViewType(int position);

    }
}
