package com.github.tablayout;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class DefaultTabAdapter<T> implements TabAdapter<T> {

    private Context context;

    public DefaultTabAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewGroup onCreateTabView() {
        LinearLayout tabView = new LinearLayout(context);
        int padding = ViewUtils.dip2px(context, 12);
        tabView.setPadding(padding, 0, padding, 0);
        tabView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        tabView.setOrientation(LinearLayout.HORIZONTAL);
        return tabView;
    }

    @Override
    public TabItemView onCreateTabItemView(String title, TabItem<T> tabItem, int position) {
        TabItemView itemView = new TabItemView(context);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        int margin = ViewUtils.dip2px(context, 37);
        if (position > 0) {
            layoutParams.setMargins(margin, 0, 0, 0);
        }
        itemView.setLayoutParams(layoutParams);
        itemView.setText(title);
        return itemView;
    }

    @Override
    public View onCreateTabIndicator() {
        View tabIndicator = new View(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewUtils.dip2px(context, 64),
                ViewUtils.dip2px(context, 2)
        );
        layoutParams.gravity = Gravity.BOTTOM;
        tabIndicator.setBackgroundColor(Color.parseColor("#00b888"));
        tabIndicator.setLayoutParams(layoutParams);
        return tabIndicator;
    }

    @Override
    public void onSelectTab(View tabItemView, boolean isSelect) {
        ((TabItemView) tabItemView).select(isSelect);
    }

    @Override
    public int getTabItemViewWidth(View tabItemView) {
        TabItemView tabItemTextView = (TabItemView) tabItemView;
        //返回文字宽度而不是view的宽度
        return (int) tabItemTextView.getPaint().measureText(
                tabItemTextView.getText().toString());
    }
}
