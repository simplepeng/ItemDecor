package me.simple.itemdecor

import androidx.recyclerview.widget.RecyclerView

interface IFilter<T : RecyclerView.ItemDecoration> {
    fun filter(block: (position: Int) -> Boolean): T
    fun filter(vararg filters: Int)
}