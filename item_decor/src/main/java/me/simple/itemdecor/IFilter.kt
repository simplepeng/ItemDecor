package me.simple.itemdecor

interface IFilter<T> {
    fun filter(func: FilterFun): T
    fun filter(vararg excludes: Int): T
}