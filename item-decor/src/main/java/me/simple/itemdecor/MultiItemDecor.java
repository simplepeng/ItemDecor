package me.simple.itemdecor;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.SparseArray;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class MultiItemDecor implements IFilter<MultiItemDecor> {

    private SparseArray<AbsItemDecor> mItemDecors = new SparseArray<>();
    private Linker mLinker;
    private FilterFunc mFilterFunc;

    public <T extends AbsItemDecor> MultiItemDecor register(T itemDecoration) {
        mItemDecors.put(itemDecoration.hashCode(), itemDecoration);
        return this;
    }

    public MultiItemDecor withLinker(Linker linker) {
        this.mLinker = linker;
        return this;
    }

    @Override
    public MultiItemDecor filter(FilterFunc func) {
        this.mFilterFunc = func;
        return this;
    }

    public RecyclerView.ItemDecoration build() {
        return new AbsItemDecor() {
            @Override
            public void onDraw(Canvas canvas, int position, Rect bounds, View itemView,
                               RecyclerView parent, RecyclerView.State state) {
                if (mFilterFunc != null && mFilterFunc.exclude(position)) return;

                getItemDecoration(position).onDraw(canvas, position, bounds, itemView, parent, state);
            }

            @Override
            public void onDrawOver(Canvas canvas, int position, Rect bounds, View itemView,
                                   RecyclerView parent, RecyclerView.State state) {
                if (mFilterFunc != null && mFilterFunc.exclude(position)) return;

                getItemDecoration(position).onDrawOver(canvas, position, bounds, itemView, parent, state);
            }

            @Override
            public void setOutRect(Rect outRect, int position, View itemView,
                                   RecyclerView parent, RecyclerView.State state) {
                if (mFilterFunc != null && mFilterFunc.exclude(position)) {
                    outRect.set(0, 0, 0, 0);
                    return;
                }

                getItemDecoration(position).setOutRect(outRect, position, itemView, parent, state);
            }
        };
    }

    private AbsItemDecor getItemDecoration(int position) {
        AbsItemDecor itemDecoration;
        if (mItemDecors.size() == 0) {
            throw new NullPointerException("Do You Call Register Method ?");
        }
        if (mLinker == null) {
            throw new NullPointerException("Do You Call withLinker Method ?");
        }

        int hashCode = mLinker.bind(position).hashCode();
        itemDecoration = mItemDecors.get(hashCode);

        if (itemDecoration == null) {
            throw new NullPointerException("Do You Call register or withLinker Method ?");
        }
        return itemDecoration;
    }

}
