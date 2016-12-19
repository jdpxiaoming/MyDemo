package com.example.poe.myapplication.viewholders;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.poe.myapplication.GridRecyclerViewActivity;
import com.example.poe.myapplication.R;
import com.example.poe.myapplication.widgets.CheckableTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by poe on 16-7-19.
 */
public class TextViewHolder extends BaseRecyclerViewHolder<Object> {

    @BindView(R.id.tv_simple_text)    TextView mCheckedTextView;

    public TextViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public int obtainLayoutId() {
        return R.layout.item_simple_text;
    }

    @Override
    public void setData(int position, Object data) {
        mCurrentPosition = position ;
        mData = data;
        //video name is the video index .
        mCheckedTextView.setText(data.toString());
    }

    @OnClick(R.id.tv_simple_text)
    public void onClick() {
        if(null != mOnClickListener) mOnClickListener.onItemClicked(mCurrentPosition,mData);
    }
}
