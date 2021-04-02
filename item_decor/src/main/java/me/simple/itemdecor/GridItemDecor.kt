package me.simple.itemdecor

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class GridItemDecor : AbsItemDecor() {

    var marginStart = 0
    var marginTop = 0
    var marginEnd = 0
    var marginBottom = 0

    var margin: Int = 0
        set(value) {
            marginStart = value
            marginTop = value
            marginEnd = value
            marginBottom = value
            field = value
        }

    //要过滤掉的Space
    private var filterBlock: ((position: Int) -> Boolean)? = null

    override fun filter(block: (position: Int) -> Boolean) {
        this.filterBlock = block
    }

    override fun filter(vararg filters: Int) {
        filter { position ->
            filters.contains(position)
        }
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

        outRect.set(marginStart, marginTop, marginEnd, marginBottom)
    }
}