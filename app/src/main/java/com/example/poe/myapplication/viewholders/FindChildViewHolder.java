package com.example.poe.myapplication.viewholders;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.poe.myapplication.R;
import com.example.poe.myapplication.widgets.CheckableTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by poe on 16-7-19.
 */
public class FindChildViewHolder extends BaseRecyclerViewHolder<Object> {
    private final String TAG = "FindChildViewHolder";
    @BindView(R.id.checked_tv)
    CheckableTextView mCheckedTv;
    @BindView(R.id.test_tv)
    TextView mTestTv;

    public FindChildViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public int obtainLayoutId() {
        return R.layout.item_childs_view;
    }

    @Override
    public void setData(int position, Object data) {

        mCurrentPosition = position;
        mData = data;
        //video name is the video index .
        mCheckedTv.setText(data.toString());
    }

   /* @OnClick({R.id.checked_tv, R.id.test_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checked_tv:
                Log.i(TAG , "view [0] " +view);
                showTips("checked text clicked !");
                break;
            case R.id.test_tv:
                Log.i(TAG , "view [1] " +view);
                showTips("second text clicked !");
                break;
        }
    }*/

    private void showTips(String tip){
        Snackbar.make(mCheckedTv,tip,Snackbar.LENGTH_SHORT).show();
    }
}
