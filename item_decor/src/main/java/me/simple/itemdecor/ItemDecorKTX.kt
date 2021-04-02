package me.simple.itemdecor

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.divider(
    color: Int = Color.LTGRAY,
    size: Int = 1,
    margin: Float = 0f
) {
    val itemDecor = LinearItemDecor().apply {
        this.color = color
        this.size = size
        this.margin = margin
    }
    this.addItemDecoration(itemDecor)
}

fun RecyclerView.divider(
    color: Int = Color.LTGRAY,
    size: Int = 1,
    marginStart: Float = 0f,
    marginEnd: Float = 0f
) {
    val itemDecor = LinearItemDecor().apply {
        this.color = color
        this.size = size
        this.marginStart = marginStart
        this.marginEnd = marginEnd
    }
    this.addItemDecoration(itemDecor)
}

fun RecyclerView.space(
    space: Int
) {

}