package com.example.poe.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.poe.myapplication.utils.StringUtils;

public class MainActivity extends AppCompatActivity {

    private TextView mTitleTextView;
    private TextView mHtmlTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        mTitleTextView = (TextView) findViewById(R.id.tv_title);
        mTitleTextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        //尝试转移字符
        mHtmlTextView   = (TextView) findViewById(R.id.tv_html);
        String str = "~!@#$%^&*()_+{}|:&quot;<>~!@#$%^&*()_+{}|:\"<>? ?,./;&apos;[]\\-";
        str = StringUtils.filterCharacter(str);
        mHtmlTextView.setText(Html.fromHtml(str));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
