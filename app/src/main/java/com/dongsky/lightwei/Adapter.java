package com.dongsky.lightwei;

import java.util.List;
import java.util.Map;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ListView;

/**
 * Created by Administrator on 2014/10/5.
 */
public class Adapter extends BaseAdapter {
    public class ListItemView{
        private ImageView Avatar;
        public TextView Id;
        private TextView WeiContent;

    }

                     //运行上下文
    private List<Map<String, Object>> listItems;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
