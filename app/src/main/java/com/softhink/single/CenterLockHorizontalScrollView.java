package com.softhink.single;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import java.util.List;

public class CenterLockHorizontalScrollView extends HorizontalScrollView {
    Context context;
    int prevIndex = 0;
    List<String> status;

    public CenterLockHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setSmoothScrollingEnabled(true);

    }

    public void setAdapter(SingleHeaderAdapter mAdapter, List<String> status) {
        this.status = status;
        try {
            fillViewWithAdapter(mAdapter);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void fillViewWithAdapter(SingleHeaderAdapter mAdapter)
            throws Exception {
        if (getChildCount() == 0) {
            throw new Exception(
                    "CenterLockHorizontalScrollView must have one child");
        }
        if (getChildCount() == 0 || mAdapter == null)
            return;

        ViewGroup parent = (ViewGroup) getChildAt(0);
        parent.removeAllViews();
        for (int i = 0; i < mAdapter.getCount(); i++) {
            parent.addView(mAdapter.getView(i, null, parent));
        }
    }

    public void setCenter(int index, SingleHeaderAdapter adapter) {
        ViewGroup parent = (ViewGroup) getChildAt(0);
        View preView = parent.getChildAt(prevIndex);
        ImageView iv = preView.findViewById(R.id.single_mini);
        iv.setImageResource(R.drawable.avatar);

        View view = parent.getChildAt(index);
        ImageView ivCurrent = view.findViewById(R.id.single_mini);
        ivCurrent.setImageResource(R.drawable.avatar);

        int screenWidth = ((Activity) context).getWindowManager()
                .getDefaultDisplay().getWidth();

        int scrollX = (view.getLeft() - (screenWidth / 2))
                + (view.getWidth() / 2);
        this.smoothScrollTo(scrollX, 0);
        prevIndex = index;
    }
}
