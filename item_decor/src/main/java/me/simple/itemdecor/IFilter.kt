package me.simple.itemdecor

interface IFilter {
    fun filter(block: (position: Int) -> Boolean)
    fun filter(vararg filters: Int)
}