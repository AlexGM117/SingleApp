package com.softhink.single;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.util.List;

public class SingleHeaderAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> list;
    private int layoutId;
    private Holder holder;
    public View view;

    public SingleHeaderAdapter(Context context, int textViewResourceId,
                               List<String> list) {
        super(context, textViewResourceId, list);
        this.context = context;
        this.list = list;
        layoutId = textViewResourceId;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        RelativeLayout layout;
        String status = getItem(position);
        if (convertView == null) {

            layout = (RelativeLayout) View.inflate(context, layoutId, null);

            holder = new Holder();
            holder.ivStatus = layout.findViewById(R.id.single_mini);
            holder.ivStatus.setImageResource(R.drawable.avatar);
            layout.setTag(holder);

        } else {
            layout = (RelativeLayout) convertView;
            view = layout;
            holder = (Holder) layout.getTag();
            holder.ivStatus.setImageResource(R.drawable.avatar);
        }
        layout.setId(position);
        return layout;
    }

    private class Holder {
        public ImageView ivStatus;
    }
}
