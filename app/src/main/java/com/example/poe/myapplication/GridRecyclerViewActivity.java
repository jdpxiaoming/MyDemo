package com.example.poe.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.poe.myapplication.adapters.BaseRecyclerAdapter;
import com.example.poe.myapplication.utils.UiUtils;
import com.example.poe.myapplication.viewholders.BaseRecyclerViewHolder;
import com.example.poe.myapplication.viewholders.VideoGridViewHolder;
import com.example.poe.myapplication.widgets.CheckableTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecyclerView 实现GridView的效果
 * Created by poe on 16-7-20.
 */
public class GridRecyclerViewActivity extends AppCompatActivity implements Handler.Callback{
    public static final String TAG = "GridRecyclerViewActivity";
    @BindView(R.id.recycler_view)    RecyclerView mRecyclerView;
    public static int mCurrentIndex = 0 ;//default: 0
    private List<String> mVideoDetailsList = new ArrayList<>();
    private BaseRecyclerAdapter<String,VideoGridViewHolder> mAdapter ;
    private Handler mHandler = new Handler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_grid);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
//        GridLayoutManager manager = new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false);
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
        mAdapter = new BaseRecyclerAdapter<>(new BaseRecyclerAdapter.ViewCreator<VideoGridViewHolder>() {
            @Override
            public VideoGridViewHolder createViewHolder(ViewGroup parent, int viewType) {
                VideoGridViewHolder viewHolder = new VideoGridViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_gride_selector,parent,false));
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
                break;
        }
        return false;
    }
}
