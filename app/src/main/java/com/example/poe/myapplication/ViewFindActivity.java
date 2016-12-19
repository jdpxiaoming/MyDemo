package com.example.poe.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.poe.myapplication.adapters.BaseRecyclerAdapter;
import com.example.poe.myapplication.viewholders.BaseRecyclerViewHolder;
import com.example.poe.myapplication.viewholders.FindChildViewHolder;
import com.example.poe.myapplication.viewholders.VideoGridViewHolder;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 1.测试ViewHolder的层次
 * 2.测试ViewGroup的getChildView是否能过找准view
 * 结果可以：．．
 *
 * Created by poe on 16-7-20.
 */
public class ViewFindActivity extends AppCompatActivity implements Handler.Callback{
    public static final String TAG = "GridRecyclerViewActivity";
    @BindView(R.id.recycler_view)    RecyclerView mRecyclerView;
    public static int mCurrentIndex = 0 ;//default: 0
    private List<String> mVideoDetailsList = new ArrayList<>();
    private BaseRecyclerAdapter<String,FindChildViewHolder> mAdapter ;
    private Handler mHandler = new Handler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_find_child_view);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        setAdapter();
        testMock();
    }

    private void testMock() {
        mVideoDetailsList.add("11");
        mVideoDetailsList.add("22");
        mVideoDetailsList.add("33");
        mVideoDetailsList.add("44");
        mAdapter.setData(mVideoDetailsList);
    }

    private void setAdapter() {
        mAdapter = new BaseRecyclerAdapter<>(new BaseRecyclerAdapter.ViewCreator<FindChildViewHolder>() {
            @Override
            public FindChildViewHolder createViewHolder(ViewGroup parent, int viewType) {
                FindChildViewHolder viewHolder = new FindChildViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_childs_view,parent,false));
                viewHolder.setOnClickListener(new BaseRecyclerViewHolder.OnItemClickListener<Object>() {
                    @Override
                    public void onItemClicked(int position, Object data) {
                        mCurrentIndex = position;
                        mAdapter.notifyDataSetChanged();
                        mHandler.sendEmptyMessage(0);
                    }
                });

                return viewHolder;
            }

            @Override
            public int getItemViewType(int position) {
                return 0;
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what){
            case 0://选中一个选项
                Snackbar.make(mRecyclerView,"clicked : "+mCurrentIndex,Snackbar.LENGTH_SHORT).show();
                int i = 3/0;
                Log.i(TAG , i + " ");
                break;
        }
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
}
