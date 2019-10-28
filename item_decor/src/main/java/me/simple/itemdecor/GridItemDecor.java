package me.simple.itemdecor;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class GridItemDecor implements IFilter<GridItemDecor> {

    private int mSpanCount;
    /**
     *
     */
    private int mMarginLeft;
    private int mMarginTop;
    private int mMarginRight;
    private int mMarginBottom;
    /**
     *
     */
    private FilterFunc mFilterFunc;

    public GridItemDecor(int spanCount) {
        this.mSpanCount = spanCount;
    }

    public GridItemDecor setMargin(int margin) {
        this.mMarginLeft = margin;
        this.mMarginTop = margin;
        this.mMarginRight = margin;
        this.mMarginBottom = margin;
        return this;
    }

    public GridItemDecor setMarginLeft(int margin) {
        this.mMarginLeft = margin;
        return this;
    }

    public GridItemDecor setMarginTop(int margin) {
        this.mMarginTop = margin;
        return this;
    }

    public GridItemDecor setMarginRight(int margin) {
        this.mMarginRight = margin;
        return this;
    }

    public GridItemDecor setMarginBottom(int margin) {
        this.mMarginBottom = margin;
        return this;
    }

    public void setSpanCount(int spanCount) {
        this.mSpanCount = spanCount;
    }

    @Override
    public GridItemDecor filter(FilterFunc func) {
        this.mFilterFunc = func;
        return this;
    }

    public AbsItemDecor build() {
        return new AbsItemDecor() {
            @Override
            public void onDraw(Canvas canvas, int position, Rect bounds, View itemView,
                               RecyclerView parent, RecyclerView.State state) {

            }

            @Override
            public void onDrawOver(Canvas canvas, int position, Rect bounds, View itemView,
                                   RecyclerView parent, RecyclerView.State state) {

            }

            @Override
            public void setOutRect(Rect outRect, int position, View itemView,
                                   RecyclerView parent, RecyclerView.State state) {
                if (mSpanCount < 1) {
                    throw new IllegalArgumentException("Span count should be at least 1. Provided " + mSpanCount);
                }

                if (mFilterFunc != null && mFilterFunc.exclude(position)) {
                    outRect.set(0, 0, 0, 0);
                    return;
                }

                if (mSpanCount == 1) {
                    outRect.set(mMarginLeft, mMarginTop, mMarginRight, mMarginBottom);
                    return;
                }

                parent.setPadding(mMarginLeft / 2, mMarginTop / 2, mMarginRight / 2, mMarginBottom / 2);
                outRect.set(mMarginLeft / 2, mMarginTop / 2, mMarginRight / 2, mMarginBottom / 2);
            }
        };
    }
}
