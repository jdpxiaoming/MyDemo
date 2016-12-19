package com.example.poe.myapplication.viewholders;

import android.support.v7.widget.AppCompatCheckedTextView;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckedTextView;

import com.example.poe.myapplication.GridRecyclerViewActivity;
import com.example.poe.myapplication.R;
import com.example.poe.myapplication.widgets.CheckableTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by poe on 16-7-19.
 */
public class VideoGridViewHolder extends BaseRecyclerViewHolder<Object> {

    @BindView(R.id.checked_text_view) CheckableTextView mCheckedTextView;

    public VideoGridViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mCheckedTextView.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    @Override
    public int obtainLayoutId() {
        return R.layout.item_video_gride_selector;
    }

    @Override
    public void setData(int position, Object data) {

        mCurrentPosition = position ;
        mData = data;
        //video name is the video index .
        mCheckedTextView.setGravity(Gravity.CENTER);
        mCheckedTextView.setText(data.toString());
        if(position == GridRecyclerViewActivity.mCurrentIndex){
            mCheckedTextView.setChecked(true);
            mCheckedTextView.setBackgroundResource(R.drawable.bg_filter_rectangle_green);
            mCheckedTextView.setTextColor(mCheckedTextView.getContext().getResources().getColor(R.color.white));
        }else{
            mCheckedTextView.setChecked(false);
            mCheckedTextView.setBackgroundResource(R.drawable.bg_filter_rectangle_white);
            mCheckedTextView.setTextColor(mCheckedTextView.getContext().getResources().getColor(R.color.black));
        }
    }

    @OnClick(R.id.checked_text_view)
    public void onClick() {
        if(null != mOnClickListener) mOnClickListener.onItemClicked(mCurrentPosition,mData);
    }
}
