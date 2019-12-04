package me.simple.itemdecor;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class MultiTypeItemDecor implements IFilter<MultiTypeItemDecor> {

    private Linker mLinker;
    private FilterFun mFilterFun;

    public MultiTypeItemDecor withLinker(Linker linker) {
        this.mLinker = linker;
        return this;
    }

    @Override
    public MultiTypeItemDecor filter(FilterFun func) {
        this.mFilterFun = func;
        return this;
    }

    @Override
    public MultiTypeItemDecor filter(int... excludes) {
        return this;
    }

    public RecyclerView.ItemDecoration build() {
        return new AbsItemDecor() {
            @Override
            public void onDraw(Canvas canvas, int position, Rect bounds, View itemView,
                               RecyclerView parent, RecyclerView.State state) {
                if (mFilterFun != null && mFilterFun.exclude(position)) return;

                getItemDecoration(position).onDraw(canvas, position, bounds, itemView, parent, state);
            }

            @Override
            public void onDrawOver(Canvas canvas, int position, Rect bounds, View itemView,
                                   RecyclerView parent, RecyclerView.State state) {
                if (mFilterFun != null && mFilterFun.exclude(position)) return;

                getItemDecoration(position).onDrawOver(canvas, position, bounds, itemView, parent, state);
            }

            @Override
            public void setOutRect(Rect outRect, int position, View itemView,
                                   RecyclerView parent, RecyclerView.State state) {
                if (mFilterFun != null && mFilterFun.exclude(position)) {
                    outRect.set(0, 0, 0, 0);
                    return;
                }

                getItemDecoration(position).setOutRect(outRect, position, itemView, parent, state);
            }
        };
    }

    private AbsItemDecor getItemDecoration(int position) {
        AbsItemDecor itemDecor;

        if (mLinker == null) {
            throw new NullPointerException("Do You Call withLinker Method ?");
        }

        itemDecor = mLinker.bind(position);

        if (itemDecor == null) {
            throw new NullPointerException("Do You Call register or withLinker Method ?");
        }
        return itemDecor;
    }

}
