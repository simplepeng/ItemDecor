package me.simple.itemdecor

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

open class AbsItemDecor : ItemDecoration() {

    private var bounds = Rect()

    override fun onDraw(
        c: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.onDraw(c, parent, state)
        if (parent.layoutManager == null || parent.adapter == null || parent.adapter!!.itemCount == 0) return

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val itemView = parent.getChildAt(i)
            val adapterPosition = parent.getChildAdapterPosition(itemView)
            parent.getDecoratedBoundsWithMargins(itemView, bounds)
            onDraw(c, adapterPosition, bounds, itemView, parent, state)
        }
    }

    override fun onDrawOver(
        c: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.onDrawOver(c, parent, state)
        if (parent.layoutManager == null || parent.adapter == null || parent.adapter!!.itemCount == 0) return

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val itemView = parent.getChildAt(i)
            val adapterPosition = parent.getChildAdapterPosition(itemView)
            parent.getDecoratedBoundsWithMargins(itemView, bounds)
            onDrawOver(c, adapterPosition, bounds, itemView, parent, state)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.layoutManager == null || parent.adapter == null || parent.adapter!!.itemCount == 0) return

        val position = parent.getChildAdapterPosition(view)
        setOutRect(outRect, position, view, parent, state)
    }

    open fun onDraw(
        canvas: Canvas,
        position: Int,
        bounds: Rect,
        itemView: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

    }

    open fun onDrawOver(
        canvas: Canvas,
        position: Int,
        bounds: Rect,
        itemView: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

    }

    open fun setOutRect(
        outRect: Rect,
        position: Int,
        itemView: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

    }
}