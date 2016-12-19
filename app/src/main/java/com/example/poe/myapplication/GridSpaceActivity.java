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
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.poe.myapplication.adapters.BaseRecyclerAdapter;
import com.example.poe.myapplication.viewholders.BaseRecyclerViewHolder;
import com.example.poe.myapplication.viewholders.TextViewHolder;
import com.example.poe.myapplication.viewholders.VideoGridViewHolder;
import com.example.poe.myapplication.widgets.SpaceItemDirector;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecyclerView 实现GridView的效果
 * Created by poe on 16-7-20.
 */
public class GridSpaceActivity extends AppCompatActivity implements Handler.Callback{
    public static final String TAG = "GridRecyclerViewActivity";
    @BindView(R.id.recycler_view)    RecyclerView mRecyclerView;
    private List<String> mVideoDetailsList = new ArrayList<>();
    private BaseRecyclerAdapter<String,TextViewHolder> mAdapter ;
    private Handler mHandler = new Handler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recycler_view);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        GridLayoutManager manager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new SpaceItemDirector(10));
        setAdapter();
        testMock();
    }

    private void testMock() {
        for(int i = 0 ;i<30;i++){
            mVideoDetailsList.add("gridItem "+i);
        }
        mAdapter.setData(mVideoDetailsList);
    }

    private void setAdapter() {
        mAdapter = new BaseRecyclerAdapter<>(new BaseRecyclerAdapter.ViewCreator<TextViewHolder>() {
            @Override
            public TextViewHolder createViewHolder(ViewGroup parent, int viewType) {
                TextViewHolder viewHolder = new TextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_text,parent,false));
                viewHolder.setOnClickListener((position, data) -> {
                    mAdapter.notifyDataSetChanged();
                    mHandler.sendEmptyMessage(0);
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
                break;
        }
        return false;
    }
}
