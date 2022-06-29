package me.simple.itemdecor

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView

/**
 *
 */
fun RecyclerView.divider(
    color: Int = Color.LTGRAY,
    size: Int = 1,
    margin: Float = 0f
): LinearItemDecor {
    val itemDecor = LinearItemDecor().apply {
        this.color = color
        this.size = size
        this.margin = margin
    }
    this.addItemDecoration(itemDecor)
    return itemDecor
}

/**
 *
 */
fun RecyclerView.divider(
    color: Int = Color.LTGRAY,
    size: Int = 1,
    marginStart: Float = 0f,
    marginEnd: Float = 0f
): LinearItemDecor {
    val itemDecor = LinearItemDecor().apply {
        this.color = color
        this.size = size
        this.marginStart = marginStart
        this.marginEnd = marginEnd
    }
    this.addItemDecoration(itemDecor)
    return itemDecor
}

/**
 *
 */
fun RecyclerView.space(
    margin: Int
): GridItemDecor {
    val itemDecor = GridItemDecor.new(margin)
    this.addItemDecoration(itemDecor)
    return itemDecor
}

fun RecyclerView.space(
    marginStart: Int = 0,
    marginTop: Int = 0,
    marginEnd: Int = 0,
    marginBottom: Int = 0,
): GridItemDecor {
    val itemDecor = GridItemDecor.new(marginStart, marginTop, marginEnd, marginBottom)
    this.addItemDecoration(itemDecor)
    return itemDecor
}

/**
 *
 */
fun RecyclerView.multiType(
    linker: (position: Int) -> AbsItemDecor
): MultiTypeItemDecor {
    val multiTypeItemDecor = MultiTypeItemDecor(linker)
    this.addItemDecoration(multiTypeItemDecor)
    return multiTypeItemDecor
}