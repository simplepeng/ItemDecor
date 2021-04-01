package me.simple.itemdecor

import android.graphics.*
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.math.roundToInt

class LinearItemDecor : AbsItemDecor(), IFilter<LinearItemDecor> {

    companion object {
        const val HORIZONTAL = 0
        const val VERTICAL = 1
    }

    private val mLineRect = RectF()

    //默认的paint，可以自定义替换掉
    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    //LinearLayoutManager的方向
    var orientation = VERTICAL

    //divider颜色
    var color = Color.GRAY

    //divider的大小
    var size: Int = 1

    //
    var margin = 0f
        set(value) {
            marginStart = margin
            marginEnd = margin
            field = value
        }

    var marginStart = 0f
    var marginEnd = 0f

    //
    private var mFilterFun: FilterFun? = null
    private var mExcludes: HashSet<Int>? = null

    //是否保存最后一个divider
    var retainLast = false

    override fun filter(func: FilterFun): LinearItemDecor {
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

    override fun onDraw(
        canvas: Canvas,
        position: Int,
        bounds: Rect,
        itemView: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (mFilterFun != null && mFilterFun!!.exclude(position)) {
            return
        }
        if (mExcludes != null && mExcludes!!.contains(position)) {
            return
        }
        if (!retainLast && parent.adapter != null && position == parent.adapter!!
                .itemCount - 1
        ) {
            return
        }
        if (orientation == VERTICAL) {
            drawVertical(canvas, itemView, bounds, parent)
        } else {
            drawHorizontal(canvas, itemView, bounds, parent)
        }
    }

    override fun setOutRect(
        outRect: Rect,
        position: Int,
        itemView: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (mFilterFun != null && mFilterFun!!.exclude(position)) {
            outRect[0, 0, 0] = 0
            return
        }
        if (mExcludes != null && mExcludes!!.contains(position)) {
            outRect[0, 0, 0] = 0
            return
        }
        if (!retainLast && parent.adapter != null && position == parent.adapter!!
                .itemCount - 1
        ) {
            outRect[0, 0, 0] = 0
            return
        }
        if (orientation == VERTICAL) {
            outRect.set(0, 0, 0, size)
            outRect[0, 0, 0] = size
        } else {
            outRect[0, 0, size] = 0
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
        left += marginStart.toInt()
        right -= marginEnd.toInt()
        val bottom = bounds.bottom + itemView.translationY.roundToInt()
        val top = bottom - size
        mLineRect[left.toFloat(), top.toFloat(), right.toFloat()] = bottom.toFloat()
        canvas.drawRect(mLineRect, paint)
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
        top += marginStart.toInt()
        bottom -= marginEnd.toInt()
        val right = bounds.right + itemView.translationX.roundToInt()
        val left = right - size
        mLineRect[left.toFloat(), top.toFloat(), right.toFloat()] = bottom.toFloat()
        canvas.drawRect(mLineRect, paint)
        canvas.restore()
    }
}