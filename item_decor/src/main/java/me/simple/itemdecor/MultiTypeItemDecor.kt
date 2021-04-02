package me.simple.itemdecor

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class MultiTypeItemDecor(
    private val linker: (position: Int) -> AbsItemDecor
) : AbsItemDecor(), IFilter {

    //要过滤掉的ItemDecor
    private var filterBlock: ((position: Int) -> Boolean)? = null

    override fun filter(block: (position: Int) -> Boolean) {
        this.filterBlock = block
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
        super.onDraw(canvas, position, bounds, itemView, parent, state)
        if (filterBlock?.invoke(position) == true) {
            return
        }
        linker.invoke(position).onDraw(canvas, position, bounds, itemView, parent, state)
    }

    override fun onDrawOver(
        canvas: Canvas,
        position: Int,
        bounds: Rect,
        itemView: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.onDrawOver(canvas, position, bounds, itemView, parent, state)
        if (filterBlock?.invoke(position) == true) {
            return
        }
        linker.invoke(position).onDrawOver(canvas, position, bounds, itemView, parent, state)
    }

    override fun setOutRect(
        outRect: Rect,
        position: Int,
        itemView: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.setOutRect(outRect, position, itemView, parent, state)
        if (filterBlock?.invoke(position) == true) {
            outRect.set(0, 0, 0, 0)
            return
        }
        linker.invoke(position).setOutRect(outRect, position, itemView, parent, state)
    }
}