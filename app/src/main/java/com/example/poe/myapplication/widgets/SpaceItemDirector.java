package com.example.poe.myapplication.widgets;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by poe on 12/19/16.
 */
public class SpaceItemDirector extends RecyclerView.ItemDecoration{

    private int space;

    public SpaceItemDirector(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = space;
        // Add top margin only for the first item to avoid double space between items
        int position = parent.getChildLayoutPosition(view);
        int count = ((GridLayoutManager)parent.getLayoutManager()).getSpanCount();
        if (position < count) {
            outRect.top = space;
        }  else{
            outRect.top = 0;
        }
        setSpace(outRect, position,count);
    }

    private void setSpace(Rect outRect, int position , int count) {
        int pos = position%count;
        Log.i("SpaceItemDirector"," pos : "+position +" real pos : "+pos + " count:"+count);
        //起始位置 .
        if(pos == 0){
            outRect.left = space;
            outRect.right = space/2;
        }else if(pos < (count-1)){//中间的部分
            outRect.left = space/2;
            outRect.right = space/2;
        }else{//最右侧
            outRect.left = space/2;
            outRect.right = space;
        }
    }
}
