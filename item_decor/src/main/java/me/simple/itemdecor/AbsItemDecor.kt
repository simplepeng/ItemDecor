package me.simple.itemdecor;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class AbsItemDecor extends RecyclerView.ItemDecoration {

    protected Rect mBounds = new Rect();

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (parent.getLayoutManager() == null || parent.getAdapter() == null
                || parent.getAdapter().getItemCount() == 0) return;

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View itemView = parent.getChildAt(i);
            int adapterPosition = parent.getChildAdapterPosition(itemView);
            parent.getDecoratedBoundsWithMargins(itemView, mBounds);

            onDraw(c, adapterPosition, mBounds, itemView, parent, state);
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (parent.getLayoutManager() == null || parent.getAdapter() == null
                || parent.getAdapter().getItemCount() == 0) return;

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View itemView = parent.getChildAt(i);
            int adapterPosition = parent.getChildAdapterPosition(itemView);
            parent.getDecoratedBoundsWithMargins(itemView, mBounds);

            onDrawOver(c, adapterPosition, mBounds, itemView, parent, state);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getLayoutManager() == null || parent.getAdapter() == null
                || parent.getAdapter().getItemCount() == 0) return;

        int position = parent.getChildAdapterPosition(view);
        setOutRect(outRect, position, view, parent, state);
    }

    public abstract void onDraw(Canvas canvas, int position, Rect bounds,
                                View itemView, RecyclerView parent, RecyclerView.State state);

    public abstract void onDrawOver(Canvas canvas, int position, Rect bounds,
                                View itemView, RecyclerView parent, RecyclerView.State state);

    public abstract void setOutRect(Rect outRect, int position,
                                    View itemView, RecyclerView parent, RecyclerView.State state);
}
