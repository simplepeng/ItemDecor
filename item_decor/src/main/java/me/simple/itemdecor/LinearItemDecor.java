package me.simple.itemdecor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class LinearItemDecor implements IFilter<LinearItemDecor> {

    private Paint mPaint;
    private RectF mLineRect = new RectF();

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private int mOrientation = VERTICAL;

    /**
     * ItemDecoration的颜色
     */
    private int mColor = Color.GRAY;
    /**
     *
     */
    private int mHeight = 1;
    private int mWidth = 1;
    /**
     *
     */
    private float mMarginLeft;
    private float mMarginTop;
    private float mMarginRight;
    private float mMarginBottom;

    /**
     *
     */
    private FilterFunc mFilterFunc;
    /**
     * 是否保存最后一个ItemDecoration
     */
    private boolean mRetainLast = false;

    public LinearItemDecor() {
        this(new Paint(Paint.ANTI_ALIAS_FLAG));
    }

    public LinearItemDecor(Paint paint) {
        paint.setColor(mColor);
        this.mPaint = paint;
    }

    public LinearItemDecor(int mOrientation) {
        this(mOrientation, new Paint(Paint.ANTI_ALIAS_FLAG));
    }

    public LinearItemDecor(int mOrientation, Paint paint) {
        this.mOrientation = mOrientation;
        paint.setColor(mColor);
        this.mPaint = paint;
    }

    public LinearItemDecor setOrientation(int orientation) {
        this.mOrientation = orientation;
        return this;
    }

    public LinearItemDecor setColor(int color) {
        this.mColor = color;
        mPaint.setColor(mColor);
        return this;
    }

    public LinearItemDecor setHeight(int height) {
        this.mHeight = height;
        return this;
    }


    public LinearItemDecor setWidth(int width) {
        this.mWidth = width;
        return this;
    }

//    public LinearItemDecor setMargin(float margin) {
//        this.mMarginLeft = margin;
//        this.mMarginTop = margin;
//        this.mMarginRight = margin;
//        this.mMarginBottom = margin;
//        return this;
//    }

    public LinearItemDecor setMarginLeft(float marginLeft) {
        this.mMarginLeft = marginLeft;
        return this;
    }

    public LinearItemDecor setMarginTop(float marginTop) {
        this.mMarginTop = marginTop;
        return this;
    }

    public LinearItemDecor setMarginRight(float marginRight) {
        this.mMarginRight = marginRight;
        return this;
    }

    public LinearItemDecor setMarginBottom(float marginBottom) {
        this.mMarginBottom = marginBottom;
        return this;
    }

    public LinearItemDecor setMarginHorizontal(float margin) {
        this.mMarginLeft = margin;
        this.mMarginRight = margin;
        return this;
    }

    public LinearItemDecor setMarginVertical(float margin) {
        this.mMarginTop = margin;
        this.mMarginBottom = margin;
        return this;
    }

    @Override
    public LinearItemDecor filter(FilterFunc func) {
        this.mFilterFunc = func;
        return this;
    }

    /**
     * 保留最后一个ItemDecoration，默认不保留
     */
    public LinearItemDecor retainLast() {
        this.mRetainLast = true;
        return this;
    }

    public AbsItemDecor build() {
        return new AbsItemDecor() {
            @Override
            public void onDraw(Canvas canvas, int position, Rect bounds, View itemView,
                               RecyclerView parent, RecyclerView.State state) {
                if (mFilterFunc != null && mFilterFunc.exclude(position)) {
                    return;
                }

                if (!mRetainLast && parent.getAdapter() != null && position == parent.getAdapter().getItemCount() - 1) {
                    return;
                }

                if (mOrientation == VERTICAL) {
                    drawVertical(canvas, itemView, bounds, parent);
                } else {
                    drawHorizontal(canvas, itemView, bounds, parent);
                }
            }

            @Override
            public void onDrawOver(Canvas canvas, int position, Rect bounds, View itemView,
                                   RecyclerView parent, RecyclerView.State state) {

            }

            @Override
            public void setOutRect(Rect outRect, int position, View itemView,
                                   RecyclerView parent, RecyclerView.State state) {
                if (mFilterFunc != null && mFilterFunc.exclude(position)) {
                    outRect.set(0, 0, 0, 0);
                    return;
                }

                if (!mRetainLast && parent.getAdapter() != null && position == parent.getAdapter().getItemCount() - 1) {
                    outRect.set(0, 0, 0, 0);
                    return;
                }

                if (mOrientation == VERTICAL) {
                    outRect.set(0, 0, 0, mHeight);
                } else {
                    outRect.set(0, 0, mWidth, 0);
                }
            }
        };
    }

    private void drawVertical(Canvas canvas, View itemView, Rect bounds, RecyclerView parent) {
        canvas.save();
        int left;
        int right;
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right, parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }
        left += mMarginLeft;
        right -= mMarginRight;
        int bottom = bounds.bottom + Math.round(itemView.getTranslationY());
        int top = bottom - mHeight;
        mLineRect.set(left, top, right, bottom);
        canvas.drawRect(mLineRect, mPaint);
        canvas.restore();
    }

    private void drawHorizontal(Canvas canvas, View itemView, Rect bounds, RecyclerView parent) {
        canvas.save();
        int top;
        int bottom;
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top, parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }
        top += mMarginTop;
        bottom -= mMarginBottom;
        int right = bounds.right + Math.round(itemView.getTranslationX());
        int left = right - mWidth;
        mLineRect.set(left, top, right, bottom);
        canvas.drawRect(mLineRect, mPaint);
        canvas.restore();
    }

}
