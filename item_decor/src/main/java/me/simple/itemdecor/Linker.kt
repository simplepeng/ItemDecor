package me.simple.itemdecor

interface Linker {
    fun bind(position: Int): AbsItemDecor?
}