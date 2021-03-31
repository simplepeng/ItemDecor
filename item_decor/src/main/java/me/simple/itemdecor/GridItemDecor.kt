package me.simple.itemdecor;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;

public class GridItemDecor implements IFilter<GridItemDecor> {

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
    private FilterFun mFilterFun;
    private HashSet<Integer> mExcludes;

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

    @Override
    public GridItemDecor filter(FilterFun func) {
        this.mFilterFun = func;
        return this;
    }

    @Override
    public GridItemDecor filter(int... excludes) {
        mExcludes = new HashSet<>();
        for (int exclude : excludes) {
            mExcludes.add(exclude);
        }
        return this;
    }

    public AbsItemDecor build() {
        Utils.checkFilter(mFilterFun, mExcludes);
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

                if (mFilterFun != null && mFilterFun.exclude(position)) {
                    outRect.set(0, 0, 0, 0);
                    return;
                }

                if (mExcludes != null && mExcludes.contains(position)) {
                    outRect.set(0, 0, 0, 0);
                    return;
                }

                outRect.set(mMarginLeft, mMarginTop, mMarginRight, mMarginBottom);
            }
        };
    }
}
