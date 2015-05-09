package com.sw.library.widget.friendrefreshview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class MainActivity extends ActionBarActivity {

    private FriendRefreshView mWrapListView;
    private BaseAdapter mListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        mListAdapter = new ListAdapter();
        mWrapListView = (FriendRefreshView) findViewById(R.id.wrapview);
        mWrapListView.setAdapter(mListAdapter);
        mWrapListView.setOnRefreshListener(new FriendRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mWrapListView.stopRefresh();
                    }
                }, 2000);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_start_refresh) {
            mWrapListView.startRefresh();
            return true;
        }else if (id == R.id.action_end_refresh) {
            mWrapListView.stopRefresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ListAdapter extends BaseAdapter {

        public ListAdapter() {

        }

        @Override
        public int getCount() {
            return 33;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_cell_layout, null);
            }
            return convertView;
        }
    }
}
