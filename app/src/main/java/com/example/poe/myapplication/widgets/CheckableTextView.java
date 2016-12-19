package com.example.poe.myapplication.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.StateSet;
import android.view.Gravity;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.example.poe.myapplication.R;

/**
 *  fixed height equal to width {@link CheckedTextView}
 * Created by poe on 16-7-19.
 */
public class CheckableTextView extends TextView implements Checkable {
    private static final String TAG = "CheckableTextView";
    /**check state default:false */
    private boolean mCheckState = false;

    public CheckableTextView(Context context) {
        this(context,null);
    }

    public CheckableTextView(Context context, AttributeSet attrs) {
//        this(context, attrs, R.attr.checkedTextViewStyle);
        this(context,attrs,0);
    }

    public CheckableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
//        this(context, attrs, defStyleAttr, 0);
        super(context,attrs,defStyleAttr);
        setChecked(true);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CheckableTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
       /* final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.CheckedTextView, defStyleAttr, defStyleRes);

        final Drawable d = a.getDrawable(R.styleable.CheckedTextView_checkMark);
        if (d != null) {
            setCheckMarkDrawable(d);
        }

        if (a.hasValue(R.styleable.CheckedTextView_checkMarkTintMode)) {
            mCheckMarkTintMode = Drawable.parseTintMode(a.getInt(
                    R.styleable.CheckedTextView_checkMarkTintMode, -1), mCheckMarkTintMode);
            mHasCheckMarkTintMode = true;
        }

        if (a.hasValue(R.styleable.CheckedTextView_checkMarkTint)) {
            mCheckMarkTintList = a.getColorStateList(R.styleable.CheckedTextView_checkMarkTint);
            mHasCheckMarkTint = true;
        }

        mCheckMarkGravity = a.getInt(R.styleable.CheckedTextView_checkMarkGravity, Gravity.END);

        final boolean checked = a.getBoolean(R.styleable.CheckedTextView_checked, false);
        setChecked(checked);

        a.recycle();

        applyCheckMarkTint();*/
        setChecked(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void setChecked(boolean checked) {
        if(mCheckState != checked){
            mCheckState = checked;
            refreshDrawableState();
        }
    }

    @Override
    public boolean isChecked() {
        return mCheckState;
    }

    @Override
    public void toggle() {
        setChecked(!mCheckState);
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        ColorStateList list = getTextColors();
        int defaultColor = list.getDefaultColor();
        int selectColor = list.getColorForState(getDrawableState(),0);
        setTextColor(selectColor);
    }
}
