package me.simple.itemdecor

import android.graphics.*
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class LinearItemDecor : IFilter<LinearItemDecor> {

    @JvmOverloads
    constructor(paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)) {
        paint.color = mColor
        mPaint = paint
    }

    @JvmOverloads
    constructor(mOrientation: Int, paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)) {
        this.mOrientation = mOrientation
        paint.color = mColor
        mPaint = paint
    }

    private var mPaint: Paint
    private val mLineRect = RectF()
    private var mOrientation = VERTICAL

    /**
     * ItemDecoration的颜色
     */
    private var mColor = Color.GRAY

    /**
     *
     */
    private var mHeight = 1
    private var mWidth = 1

    /**
     *
     */
    private var mMarginLeft = 0f
    private var mMarginTop = 0f
    private var mMarginRight = 0f
    private var mMarginBottom = 0f

    /**
     *
     */
    private var mFilterFun: FilterFun? = null
    private var mExcludes: HashSet<Int>? = null

    /**
     * 是否保存最后一个ItemDecoration
     */
    private var mRetainLast = false

    fun setOrientation(orientation: Int): LinearItemDecor {
        mOrientation = orientation
        return this
    }

    fun setColor(color: Int): LinearItemDecor {
        mColor = color
        mPaint.color = mColor
        return this
    }

    fun setHeight(height: Int): LinearItemDecor {
        mHeight = height
        return this
    }

    fun setWidth(width: Int): LinearItemDecor {
        mWidth = width
        return this
    }

    //    public LinearItemDecor setMargin(float margin) {
    //        this.mMarginLeft = margin;
    //        this.mMarginTop = margin;
    //        this.mMarginRight = margin;
    //        this.mMarginBottom = margin;
    //        return this;
    //    }
    fun setMarginLeft(marginLeft: Float): LinearItemDecor {
        mMarginLeft = marginLeft
        return this
    }

    fun setMarginTop(marginTop: Float): LinearItemDecor {
        mMarginTop = marginTop
        return this
    }

    fun setMarginRight(marginRight: Float): LinearItemDecor {
        mMarginRight = marginRight
        return this
    }

    fun setMarginBottom(marginBottom: Float): LinearItemDecor {
        mMarginBottom = marginBottom
        return this
    }

    fun setMarginHorizontal(margin: Float): LinearItemDecor {
        mMarginLeft = margin
        mMarginRight = margin
        return this
    }

    fun setMarginVertical(margin: Float): LinearItemDecor {
        mMarginTop = margin
        mMarginBottom = margin
        return this
    }

    override fun filter(func: FilterFun?): LinearItemDecor {
        mFilterFun = func
        return this
    }

    override fun filter(vararg excludes: Int): LinearItemDecor {
        mExcludes = HashSet()
        for (exclude in excludes) {
            mExcludes!!.add(exclude)
        }
        return this
    }

    /**
     * 保留最后一个ItemDecoration，默认不保留
     */
    fun retainLast(): LinearItemDecor {
        mRetainLast = true
        return this
    }

    fun build(): AbsItemDecor {
        Utils.checkFilter(mFilterFun, mExcludes)
        return object : AbsItemDecor() {
            override fun onDraw(
                canvas: Canvas, position: Int, bounds: Rect, itemView: View,
                parent: RecyclerView, state: RecyclerView.State?
            ) {
                if (mFilterFun != null && mFilterFun!!.exclude(position)) {
                    return
                }
                if (mExcludes != null && mExcludes!!.contains(position)) {
                    return
                }
                if (!mRetainLast && parent.adapter != null && position == parent.adapter!!
                        .itemCount - 1
                ) {
                    return
                }
                if (mOrientation == VERTICAL) {
                    drawVertical(canvas, itemView, bounds, parent)
                } else {
                    drawHorizontal(canvas, itemView, bounds, parent)
                }
            }

            override fun onDrawOver(
                canvas: Canvas?, position: Int, bounds: Rect?, itemView: View?,
                parent: RecyclerView?, state: RecyclerView.State?
            ) {
            }

            override fun setOutRect(
                outRect: Rect, position: Int, itemView: View?,
                parent: RecyclerView, state: RecyclerView.State?
            ) {
                if (mFilterFun != null && mFilterFun!!.exclude(position)) {
                    outRect[0, 0, 0] = 0
                    return
                }
                if (mExcludes != null && mExcludes!!.contains(position)) {
                    outRect[0, 0, 0] = 0
                    return
                }
                if (!mRetainLast && parent.adapter != null && position == parent.adapter!!
                        .itemCount - 1
                ) {
                    outRect[0, 0, 0] = 0
                    return
                }
                if (mOrientation == VERTICAL) {
                    outRect[0, 0, 0] = mHeight
                } else {
                    outRect[0, 0, mWidth] = 0
                }
            }
        }
    }

    private fun drawVertical(canvas: Canvas, itemView: View, bounds: Rect, parent: RecyclerView) {
        canvas.save()
        var left: Int
        var right: Int
        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            canvas.clipRect(left, parent.paddingTop, right, parent.height - parent.paddingBottom)
        } else {
            left = 0
            right = parent.width
        }
        left += mMarginLeft.toInt()
        right -= mMarginRight.toInt()
        val bottom = bounds.bottom + Math.round(itemView.translationY)
        val top = bottom - mHeight
        mLineRect[left.toFloat(), top.toFloat(), right.toFloat()] = bottom.toFloat()
        canvas.drawRect(mLineRect, mPaint)
        canvas.restore()
    }

    private fun drawHorizontal(canvas: Canvas, itemView: View, bounds: Rect, parent: RecyclerView) {
        canvas.save()
        var top: Int
        var bottom: Int
        if (parent.clipToPadding) {
            top = parent.paddingTop
            bottom = parent.height - parent.paddingBottom
            canvas.clipRect(parent.paddingLeft, top, parent.width - parent.paddingRight, bottom)
        } else {
            top = 0
            bottom = parent.height
        }
        top += mMarginTop.toInt()
        bottom -= mMarginBottom.toInt()
        val right = bounds.right + Math.round(itemView.translationX)
        val left = right - mWidth
        mLineRect[left.toFloat(), top.toFloat(), right.toFloat()] = bottom.toFloat()
        canvas.drawRect(mLineRect, mPaint)
        canvas.restore()
    }

    companion object {
        const val HORIZONTAL = 0
        const val VERTICAL = 1
    }
}