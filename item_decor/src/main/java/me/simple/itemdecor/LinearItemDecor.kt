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

        fun new(
            color: Int = Color.LTGRAY,
            size: Int = 1,
            margin: Float = 0f,
            retainLast: Boolean = false,
            orientation: Int = VERTICAL
        ) = LinearItemDecor().apply {
            this.color = color
            this.size = size
            this.margin = margin
            this.retainLast = retainLast
            this.orientation = orientation
        }

        fun new(
            color: Int = Color.LTGRAY,
            size: Int = 1,
            marginStart: Float = 0f,
            marginEnd: Float = 0f,
            retainLast: Boolean = false,
            orientation: Int = VERTICAL
        ) = LinearItemDecor().apply {
            this.color = color
            this.size = size
            this.marginStart = marginStart
            this.marginEnd = marginEnd
            this.retainLast = retainLast
            this.orientation = orientation
        }
    }

    private val mLineRect = RectF()

    //默认的paint，可以自定义替换掉
    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    //LinearLayoutManager的方向
    var orientation = VERTICAL

    //divider颜色
    var color = Color.LTGRAY
        set(value) {
            paint.color = value
            field = value
        }

    //divider的大小
    var size: Int = 1

    //
    var marginStart = 0f
    var marginEnd = 0f

    var margin = 0f
        set(value) {
            this.marginStart = value
            this.marginEnd = value
            field = value
        }


    //是否保存最后一个divider
    var retainLast = false

    //要过滤掉的divider
    private var filterBlock: ((position: Int) -> Boolean)? = null

    override fun filter(block: (position: Int) -> Boolean): LinearItemDecor {
        this.filterBlock = block
        return this
    }

    override fun filter(vararg filters: Int) {
        filter { position ->
            filters.contains(position)
        }
    }

    override fun onDraw(
        canvas: Canvas,
        position: Int,
        bounds: Rect,
        itemView: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        //
        if (filterBlock?.invoke(position) == true) {
            return
        }
        //
        if (!retainLast && position == parent.adapter!!.itemCount - 1) {
            return
        }
        //
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
        //
        if (filterBlock?.invoke(position) == true) {
            outRect.set(0, 0, 0, 0)
            return
        }
        //
        if (!retainLast && position == parent.adapter!!.itemCount - 1) {
            outRect.set(0, 0, 0, 0)
            return
        }
        //
        if (orientation == VERTICAL) {
            outRect.set(0, 0, 0, size)
            outRect[0, 0, 0] = size
        } else {
            outRect.set(0, 0, size, 0)
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