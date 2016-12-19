package com.example.poe.myapplication.viewholders;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.poe.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 加载更多...在recyclerView的底部工多使用 .
 * Created by poe on 16-1-19.
 */
public class LoadMoreViewHolder<VH> extends BaseRecyclerViewHolder<VH>{

    @BindView(R.id.tv_title) TextView mTitleTextView;
    @BindView(R.id.pro_loading)ProgressBar mProgressBar;

    public LoadMoreViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public int obtainLayoutId() {
        return R.layout.item_view_load_more;
    }

    @Override
    public void setData(int position,VH data) {
        mCurrentPosition = position;
        mData = data;
    }

    public void show(){
        mTitleTextView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void dismiss(){
        mTitleTextView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
    }
}
