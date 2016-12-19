package com.example.poe.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页选择
 */
public class MainListActivity extends AppCompatActivity {


    @BindView(R.id.marquee_tv)    TextView mMarqueeTv;
    @BindView(R.id.test_grid_tv)    TextView mTestTv;
    @BindView(R.id.test_view_id_tv)    TextView mTestViewIdTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.marquee_tv, R.id.test_grid_tv,R.id.tv_grid_recycler_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.marquee_tv:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.test_grid_tv:
                startActivity(new Intent(this, GridRecyclerViewActivity.class));
                break;
            case R.id.tv_grid_recycler_view:
                startActivity(new Intent(this, GridSpaceActivity.class));
                break;
        }
    }

    @OnClick(R.id.test_view_id_tv)
    public void onClick() {
        startActivity(new Intent(this, ViewFindActivity.class));
    }
}
